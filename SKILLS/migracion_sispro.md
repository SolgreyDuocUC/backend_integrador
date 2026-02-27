# SKILL TECNICA — Integracion y Migracion Segura SISPRO–ERP

---

> **Nombre formal:** Skill de Integracion y Migracion Segura SISPRO–ERP
> **Nombre interno:** `sispro_erp_middleware`
> **Clasificacion:** Documentacion Tecnica Corporativa — Uso Interno Restringido
> **Version:** 1.0
> **Fecha:** 2026-02-26
> **Fundamento de origen:** Analisis arquitectonico `SISPRO_MENU_COSTOS.md` + Esquema real `BD_SISPRO_INFO.md`
> **Estandares de referencia:** IEEE 42010:2011 — ISO/IEC 25010:2011 — ISO/IEC 27001 — OWASP Data Security

---

## Declaracion de Rol

Esta skill define el comportamiento de un **backend intermedio de mision critica** cuyo unico proposito es leer datos desde la base de datos legacy `bdsispro` (MySQL, servidor `192.168.2.3:3306`) y transferirlos de forma segura, validada e integra hacia la base de datos destino del ERP web (PostgreSQL normalizado).

**Restriccion absoluta e irrevocable:** Esta skill no modifica codigo existente, no refactoriza el sistema legacy, no altera estructuras actuales de base de datos origen, no propone cambios en los ejecutables Sispro, no interfiere con las operaciones activas de los usuarios del sistema legacy.

---

## Tabla de Contenidos

1. [Definicion Arquitectonica Formal](#1-definicion-arquitectonica-formal)
2. [Principios Obligatorios de Diseno](#2-principios-obligatorios-de-diseno)
3. [Modelo de Integracion de Datos](#3-modelo-de-integracion-de-datos)
4. [Motor de Transformacion](#4-motor-de-transformacion)
5. [Control de Integridad](#5-control-de-integridad)
6. [Gestion de Errores](#6-gestion-de-errores)
7. [Seguridad de Datos](#7-seguridad-de-datos)
8. [Observabilidad](#8-observabilidad)
9. [Estrategia de Ejecucion](#9-estrategia-de-ejecucion)
10. [Rendimiento y Escalabilidad](#10-rendimiento-y-escalabilidad)
11. [Riesgos Reales y Mitigaciones](#11-riesgos-reales-y-mitigaciones)
12. [Contexto del Sistema Origen](#12-contexto-del-sistema-origen)

---

## 1. Definicion Arquitectonica Formal

### 1.1 Rol dentro del Ecosistema

El backend intermedio ocupa la posicion de **capa de integracion desacoplada** entre dos sistemas heterogeneos:

```
[bdsispro MySQL 192.168.2.3:3306]
          |
          | lectura de solo consulta (SELECT)
          v
[MIDDLEWARE sispro_erp_middleware]
          |
          | escritura validada y atomica
          v
[ERP PostgreSQL — base destino normalizada]
```

El middleware no es visible para los usuarios del sistema legacy. Opera como proceso independiente, sin interferir con las conexiones activas de los ejecutables Sispro.

### 1.2 Responsabilidades Exactas

- Leer datos desde `bdsispro` en modo **solo lectura** (SELECT exclusivamente).
- Transformar datos desde el modelo desnormalizado legacy hacia el modelo normalizado del ERP.
- Validar estructural y semanticamente cada registro antes de su insercion.
- Insertar datos en la base PostgreSQL destino con garantias de atomicidad.
- Registrar en log auditado cada operacion realizada, exitosa o fallida.
- Detectar y gestionar duplicados, conflictos y registros inconsistentes.
- Exponer metricas de ejecucion a herramientas de monitoreo.
- Garantizar la reprocesabilidad de cualquier lote fallido sin riesgo de duplicacion.

### 1.3 Limites Estrictos de Accion

| Accion | Estado |
|---|---|
| SELECT sobre tablas de `bdsispro` | PERMITIDO |
| INSERT / UPDATE / DELETE sobre `bdsispro` | PROHIBIDO ABSOLUTO |
| Modificacion de archivos `.exe` o `.ini` del sistema legacy | PROHIBIDO ABSOLUTO |
| Modificacion del esquema MySQL de `bdsispro` | PROHIBIDO ABSOLUTO |
| Insercion en tablas destino PostgreSQL | PERMITIDO — solo tras validacion completa |
| Lectura de configuracion desde variables de entorno cifradas | PERMITIDO |
| Exposicion de credenciales en logs | PROHIBIDO ABSOLUTO |

### 1.4 Interacciones Permitidas

- Conexion de lectura al servidor MySQL `192.168.2.3:3306` con usuario de solo lectura (distinto al usuario `sispro` de produccion).
- Escritura en la base PostgreSQL del ERP con usuario de escritura restringido por esquema.
- Escritura en sistema de logs estructurados (archivo + sistema centralizado si disponible).
- Exposicion de endpoint de metricas internas (Prometheus-compatible).
- Lectura de configuracion desde archivo de entorno cifrado o vault de secretos.

### 1.5 Interacciones Prohibidas

- Conexion directa a la base de datos con las credenciales de produccion del sistema legacy (`usr=sispro`, `Pass=sispro`), las cuales no deben utilizarse en ningun componente del middleware.
- Comunicacion directa con los ejecutables `.exe` del sistema Sispro.
- Acceso a los archivos `.ini`, `.rpt`, `.xls` del sistema legacy con permisos de escritura.
- Bypass de las validaciones de integridad ante cualquier circunstancia operativa.

---

## 2. Principios Obligatorios de Diseno

### 2.1 Inmutabilidad del Origen

El sistema origen `bdsispro` es inmutable desde la perspectiva del middleware. Ningun proceso del middleware debe alterar datos, esquemas, indices, vistas ni configuraciones en la base de datos legacy. Toda consulta debe ejecutarse con una cuenta de base de datos que tenga permisos GRANT SELECT unicamente.

### 2.2 Idempotencia

Cada operacion de carga debe ser idempotente. La ejecucion repetida del mismo lote de datos no debe producir registros duplicados ni estados inconsistentes en el destino. La idempotencia se garantiza mediante:

- Control de claves naturales unicas derivadas del sistema origen.
- Verificacion de existencia previa antes de cada INSERT.
- Uso de operaciones UPSERT con control de version en el destino.
- Registro de identificadores procesados en tabla de control de migracion.

### 2.3 Atomicidad Controlada

Cada unidad de procesamiento logico (ej. un encabezado de documento mas sus lineas de detalle, tallas y pagos asociados) debe insertarse como una transaccion atomica en el destino. Si cualquier parte del conjunto falla, la transaccion completa se revierte. No se permiten inserciones parciales de entidades compuestas.

Ejemplo de unidad atomica para Orden de Compra:
- `enc_ocompra` (encabezado)
- `det_ocompra` (lineas)
- `tal_ocompra` (tallas por linea)
- `desrec_ocompra` (descuentos/recargos)
- `pago_ocompra` (condiciones de pago)

### 2.4 Consistencia Eventual Controlada

El sistema no garantiza sincronizacion en tiempo real. Garantiza que, tras la ejecucion de cada ciclo de procesamiento, los datos del destino sean consistentes con el origen al momento de la lectura. El desfase temporal aceptable se define en la Estrategia de Ejecucion (seccion 9).

### 2.5 Tolerancia a Fallos

El middleware debe continuar operando ante fallos parciales. Un registro fallido no debe detener el procesamiento del lote completo. Los registros fallidos se derivan a una cola de errores con todos los datos necesarios para su reprocesamiento posterior.

### 2.6 Reprocesamiento Seguro

Todo lote o registro fallido debe poder ser reprocesado de forma segura en cualquier momento posterior, sin riesgo de duplicacion, sin requerir reinicio completo del proceso y sin intervension manual sobre la base de datos destino.

### 2.7 Trazabilidad Completa

Cada registro migrado debe tener asociado un identificador de trazabilidad que permita determinar: su origen exacto en `bdsispro` (tabla, PK), el lote que lo proceso, el timestamp de procesamiento, el resultado (exito/fallo), el usuario tecnico que ejecuto el proceso y la version del middleware utilizada.

---

## 3. Modelo de Integracion de Datos

### 3.1 Estrategia de Lectura

**Modalidad:** Lectura por paginacion con cursor sobre clave primaria compuesta.

Dado que las tablas de `bdsispro` utilizan claves primarias compuestas (siempre iniciando con `Empresa varchar(3)`) y que los identificadores de documentos son de tipo `double`, la estrategia de lectura debe:

1. Iterar por empresa (`empresas.CODIGO`: maximo 5 empresas activas identificadas).
2. Ordenar por la clave primaria compuesta de cada tabla.
3. Leer en bloques de tamano configurable (default: 500 registros por consulta).
4. Mantener un cursor de posicion por tabla y empresa para reanudar en caso de interrupcion.
5. Utilizar una cuenta MySQL de solo lectura, distinta a la cuenta de produccion.

**Tablas de alta prioridad de lectura** (por volumen de datos criticos):

| Tabla | Filas | Prioridad |
|---|---|---|
| `enc_solicosto` | 19,960 | Critica |
| `det_solicosto` | 45,189 | Critica |
| `acc_solicosto` | 71,776 | Critica |
| `enc_ocompra` | 12,291 | Critica |
| `det_ocompra` | 34,789 | Critica |
| `enc_solicompra` | 8,180 | Alta |
| `det_solicompra` | 53,988 | Alta |
| `enc_otrabajo` | 4,248 | Alta |
| `det_otrabajo` | 32,361 | Alta |
| `articulos` | 15,649 | Alta |
| `clientes` | 9,352 | Alta |
| `proveedores` | 2,008 | Alta |
| `moviconta` | 204,889 | Media (modulo contable) |
| `det_invfisico` | 389,018 | Media (inventario fisico) |

### 3.2 Estrategia de Transformacion

Cada registro leido del origen pasa por un pipeline de transformacion secuencial:

```
[REGISTRO ORIGEN]
      |
      v
[PASO 1] Normalizacion de tipos de dato
      |
      v
[PASO 2] Resolucion de referencias implicitas
      |
      v
[PASO 3] Deduplicacion
      |
      v
[PASO 4] Validacion estructural
      |
      v
[PASO 5] Validacion semantica
      |
      v
[PASO 6] Mapeo al modelo destino
      |
      v
[REGISTRO DESTINO — listo para insercion]
```

### 3.3 Estrategia de Carga

**Patron:** Upsert con control de version.

Para cada entidad en el destino:
1. Verificar existencia mediante clave natural derivada del origen (Empresa + identificador original).
2. Si no existe: INSERT con timestamp de creacion y version = 1.
3. Si existe y los datos cambiaron: UPDATE con timestamp de modificacion y version incremental.
4. Si existe y los datos son identicos: omitir, registrar en log como "sin cambio".
5. Nunca ejecutar DELETE en el destino durante la migracion inicial.

### 3.4 Validacion Estructural

Verificar para cada registro:
- Presencia de campos obligatorios segun la tabla origen.
- Ausencia de nulos en campos PK.
- Longitud de campos varchar dentro de los limites definidos.
- Tipos de dato correctos tras la conversion (double → bigint/decimal, bit → boolean).
- Fechas en rango valido (el campo `double` usado como identificador numerico de fecha no debe tener valores negativos ni superiores a la fecha actual + 5 anos).

### 3.5 Validacion Semantica

Verificar para cada registro:
- El valor de `Empresa` existe en la tabla `empresas` del origen.
- Los identificadores de clientes, proveedores, articulos referenciados existen en sus respectivas tablas maestro.
- Las cantidades nunca son negativas salvo en tablas de devolucion o correccion explicitamente identificadas.
- Las fechas de cierre no son anteriores a las fechas de apertura del mismo documento.
- Los estados numericos estan dentro de los valores validos conocidos para cada flujo.

### 3.6 Reconciliacion de Datos

Al finalizar cada lote, ejecutar verificacion de reconciliacion:
- Contar registros leidos desde el origen.
- Contar registros insertados/actualizados en el destino.
- Contar registros rechazados por error.
- La suma de insertados + actualizados + rechazados debe ser igual al total leido.
- Cualquier discrepancia debe generar una alerta critica y suspender el lote siguiente.

---

## 4. Motor de Transformacion

### 4.1 Conversion de Tipos de Dato

| Tipo origen MySQL | Tipo destino PostgreSQL | Regla de conversion |
|---|---|---|
| `double` (identificador) | `BIGINT` | Truncar parte decimal. Rechazar si tiene decimales distintos de .0 |
| `double` (monetario) | `NUMERIC(18,4)` | Conservar 4 decimales maximos |
| `double` (cantidad) | `NUMERIC(14,4)` | Conservar 4 decimales maximos |
| `varchar(N)` | `VARCHAR(N)` | Trim de espacios en ambos extremos |
| `bit(1)` | `BOOLEAN` | 1 → true, 0 → false, NULL → false con advertencia |
| `datetime` | `TIMESTAMP WITH TIME ZONE` | Asumir zona horaria del servidor origen (America/Santiago por contexto) |
| `int` | `INTEGER` | Conversion directa |
| `tinyint(1)` | `BOOLEAN` | 1 → true, 0 → false |
| `longtext` | `TEXT` | Conservar contenido integro. Registrar longitud en metadata |

### 4.2 Normalizacion

**Tablas de tallas (Talla01..Talla30):** El patron de columnas fijas debe normalizarse al modelo:

```
(id_documento, linea, codigo_talla, cantidad)
```

El codigo de talla se deriva del indice de columna (Talla01 = posicion 1). Los registros con valor 0 o NULL en todas las columnas de talla se omiten. Solo se generan registros para las posiciones con valor mayor a 0.

**Tabla `accesos` (200+ columnas bit):** El modelo de permisos se normaliza a:

```
(empresa, usuario, sistema, menu, numero_opcion, habilitado)
```

Se generan registros para cada combinacion Menu_XX / M_XX_OpYY con valor true.

**Tabla `parametros` (catastro generico):** Se normaliza a tablas especificas por tipo en el destino. El campo `TIPO` determina la tabla de destino:

| TIPO en parametros | Tabla destino en ERP |
|---|---|
| Modelos | erp.modelos |
| Telas | erp.telas |
| Generos | erp.generos |
| Colores | erp.colores |
| Composiciones | erp.composiciones |
| Aplicaciones | erp.aplicaciones |
| *(otros tipos)* | erp.parametros_generales |

**Tablas temporales (tmp_*):** No deben migrarse. Su contenido es volatil y dependiente de sesion de usuario. Se excluyen del proceso de migracion.

### 4.3 Deduplicacion

La base `bdsispro` no declara FOREIGN KEYs. Como consecuencia, pueden existir registros huerfanos (detalles sin encabezado). El proceso de deduplicacion debe:

1. Antes de migrar tablas de detalle, verificar que el encabezado correspondiente existe en el destino.
2. Registrar los detalles sin encabezado en la cola de errores como "huerfano referencial".
3. Nunca insertar un detalle sin su encabezado previo en el destino.

Para registros duplicados dentro de la misma tabla origen (posible en tablas sin PK formal como `moviconta`, `det_invfisico`, `invvalorizado`, `alarmas`): conservar el primer registro encontrado y descartar duplicados con advertencia en log.

### 4.4 Resolucion de Inconsistencias

| Inconsistencia detectada | Accion del middleware |
|---|---|
| Fecha con valor 0 o nulo | Registrar como NULL en destino + advertencia en log |
| Campo `double` de ID con valor decimal (ej. 1001.5) | Rechazar registro + error en cola |
| Campo varchar con encoding corrupto (caracteres ?) | Sanitizar a UTF-8 con replacement character + advertencia |
| Cantidad negativa en tabla no de devolucion | Rechazar + error en cola |
| Referencia a empresa inexistente | Rechazar + error en cola |
| Estado fuera de rango conocido | Registrar como NULL + advertencia |
| Fecha de cierre anterior a fecha de apertura | Rechazar + error en cola |

### 4.5 Mapeo de Relaciones Implicitas

Las relaciones implicitas entre tablas del origen (sin FK declarada) deben ser resueltas explicitamente en el motor de transformacion. Las relaciones criticas identificadas son:

| Tabla hijo | Campo de enlace | Tabla padre | Accion si padre no existe |
|---|---|---|---|
| `det_solicosto` | Empresa + Nro_Solicitud | `enc_solicosto` | Rechazar |
| `acc_solicosto` | Empresa + Nro_Solicitud | `enc_solicosto` | Rechazar |
| `pla_solicosto` | Empresa + NroCosto | `enc_solicosto` | Rechazar |
| `tal_solicosto` | Empresa + Nro_Solicitud | `enc_solicosto` | Rechazar |
| `det_ocompra` | Empresa + Nro_OCompra | `enc_ocompra` | Rechazar |
| `tal_ocompra` | Empresa + Nro_OCompra | `enc_ocompra` | Rechazar |
| `pago_ocompra` | Empresa + Nro_OCompra | `enc_ocompra` | Rechazar |
| `det_solicompra` | Empresa + Nro_Sol | `enc_solicompra` | Rechazar |
| `det_otrabajo` | Empresa + Nro_OTrabajo | `enc_otrabajo` | Rechazar |
| `det_occliente` | Empresa + Nro_OCCli | `enc_occliente` | Rechazar |
| `mat_oservicios` | Empresa + Nro_OServicio | `enc_oservicios` | Rechazar |
| `documento_inv` | Empresa + Tipo_Documento + Numero_Doc | `enc_inventario` | Advertencia (puede ser historico) |
| `moviconta` | Empresa + NUMCOMP + TIPOCOMP + ANN_COMP | `foliocomprobantes` | Advertencia |

---

## 5. Control de Integridad

### 5.1 Verificacion Previa a la Insercion

Antes de ejecutar cualquier INSERT en el destino, el middleware debe verificar:

1. Que la conexion a la base destino esta activa y la transaccion esta abierta.
2. Que el registro no existe previamente (control de idempotencia).
3. Que todos los registros padre de la cadena referencial existen en el destino.
4. Que el registro paso todas las validaciones estructurales y semanticas.
5. Que el tamano del campo TEXT no supera el limite operativo definido (default: 1 MB por campo).

### 5.2 Validacion Post Insercion

Inmediatamente despues de cada INSERT o UPDATE exitoso:

1. Ejecutar SELECT de verificacion sobre la PK del registro insertado.
2. Comparar checksum logico del registro en origen vs. el registro recuperado del destino.
3. Si el checksum no coincide: revertir la transaccion, registrar error critico, derivar a cola de errores.

### 5.3 Checksum Logico

El checksum logico se calcula como un hash (SHA-256) del conjunto de campos relevantes del registro, excluyendo timestamps de auditoria del middleware. Se almacena junto al registro en el destino como columna `sispro_checksum`. Permite detectar modificaciones no autorizadas y verificar integridad en reprocesos.

### 5.4 Validacion Referencial Post Carga

Al finalizar la migracion de cada dominio funcional (ej. todas las Ordenes de Compra), ejecutar:

1. Conteo de registros en origen vs. destino por tabla.
2. Verificacion de que todos los encabezados tienen al menos un registro de detalle (cuando corresponde segun el negocio).
3. Verificacion de que todas las referencias cruzadas entre tablas del destino son resolubles.
4. Exportar reporte de reconciliacion por dominio con detalle de discrepancias.

### 5.5 Auditoria de Cambios

El destino PostgreSQL debe mantener una tabla de auditoria de migracion con la siguiente estructura conceptual:

```
migracion_auditoria (
  id_auditoria       BIGSERIAL PRIMARY KEY,
  tabla_origen       VARCHAR(60),
  pk_origen          VARCHAR(255),   -- PK serializada como JSON
  tabla_destino      VARCHAR(60),
  pk_destino         BIGINT,
  accion             VARCHAR(10),    -- INSERT | UPDATE | SKIP | ERROR
  lote_id            UUID,
  timestamp_proceso  TIMESTAMP WITH TIME ZONE,
  version_middleware VARCHAR(20),
  checksum_origen    CHAR(64),
  checksum_destino   CHAR(64),
  detalle_error      TEXT
)
```

---

## 6. Gestion de Errores

### 6.1 Clasificacion de Errores

| Codigo | Categoria | Descripcion | Accion |
|---|---|---|---|
| E-CON | Conectividad | Fallo de conexion a origen o destino | Reintentar con backoff exponencial (max 3 intentos), luego pausar lote |
| E-VAL-EST | Validacion estructural | Campo obligatorio nulo, tipo incorrecto, longitud excedida | Rechazar registro, derivar a cola, continuar lote |
| E-VAL-SEM | Validacion semantica | Referencia inexistente, fecha invalida, cantidad fuera de rango | Rechazar registro, derivar a cola, continuar lote |
| E-HUE | Huerfano referencial | Detalle sin encabezado padre | Rechazar registro, derivar a cola de huerfanos, continuar lote |
| E-DUP | Duplicado en origen | Registro duplicado en tabla sin PK formal | Conservar primero, descartar resto con advertencia |
| E-TRX | Transaccional | Error durante INSERT/UPDATE en destino | Revertir transaccion completa del lote, derivar lote a cola de error |
| E-CHK | Checksum | Discrepancia checksum post insercion | Revertir transaccion, registrar error critico, alertar |
| E-REC | Reconciliacion | Discrepancia en conteos finales | Suspender siguiente lote, alertar, esperar intervencion |

### 6.2 Politica de Reintentos

- Errores de conectividad (E-CON): reintentar 3 veces con espera progresiva (5s, 15s, 45s). Si el tercer intento falla, suspender el lote actual y registrar como fallido completo.
- Errores de validacion (E-VAL-*): sin reintento. El registro falla definitivamente hasta correccion manual o nueva extraccion del origen.
- Errores transaccionales (E-TRX): reintento unico del lote completo tras 60 segundos. Si el segundo intento falla, el lote queda en cola de error.

### 6.3 Cola de Errores

Estructura de almacenamiento de registros fallidos:

```
migracion_cola_errores (
  id_error          BIGSERIAL PRIMARY KEY,
  lote_id           UUID,
  tabla_origen      VARCHAR(60),
  pk_origen         VARCHAR(255),
  datos_origen      JSONB,          -- snapshot completo del registro origen
  codigo_error      VARCHAR(10),
  descripcion_error TEXT,
  timestamp_fallo   TIMESTAMP WITH TIME ZONE,
  intentos          INTEGER DEFAULT 0,
  estado            VARCHAR(20),    -- PENDIENTE | REPROCESANDO | RESUELTO | DESCARTADO
  timestamp_resol   TIMESTAMP WITH TIME ZONE
)
```

### 6.4 Estrategia de Recuperacion

1. Los registros en cola de errores pueden ser reprocesados manualmente o en ciclo automatico nocturno.
2. Antes de reprocesar, el operador tecnico debe revisar el motivo del error y determinar si el dato en origen es valido o corrupto.
3. Los registros marcados como DESCARTADO deben tener una justificacion tecnica documentada en el campo `descripcion_error`.
4. El reprocesamiento es idempotente: no genera duplicados aunque el registro haya sido parcialmente procesado.

---

## 7. Seguridad de Datos

### 7.1 Gestion de Credenciales

**Problema identificado en el origen:** Las credenciales de `bdsispro` (`usr=sispro`, `Pass=sispro`) estan en texto plano en `MenuCostos.ini`. El middleware **no debe usar estas credenciales**.

**Requisito obligatorio:** Se debe crear un usuario MySQL de solo lectura dedicado al middleware, con permisos GRANT SELECT exclusivamente sobre `bdsispro`. Las credenciales de este usuario deben almacenarse en:

- Variable de entorno cifrada en el servidor donde corre el middleware, o
- Vault de secretos (HashiCorp Vault, AWS Secrets Manager, o equivalente), o
- Archivo de configuracion cifrado con clave externa al repositorio.

Nunca en texto plano. Nunca en logs. Nunca en repositorios de control de versiones.

### 7.2 Prevencion de Corrupcion

- Todas las escrituras en el destino se realizan dentro de transacciones explicitas.
- Se utiliza nivel de aislamiento `SERIALIZABLE` para operaciones criticas de reconciliacion.
- Se utiliza nivel `READ COMMITTED` para lecturas del origen (no modificar el nivel de aislamiento del servidor MySQL de produccion).
- Ningun proceso del middleware ejecuta DDL (CREATE, ALTER, DROP) en ninguna de las dos bases de datos.

### 7.3 Proteccion contra Duplicados

El middleware mantiene en PostgreSQL una tabla de control de identidad migrada:

```
migracion_identidad (
  empresa         VARCHAR(3),
  tabla_origen    VARCHAR(60),
  pk_origen       VARCHAR(255),
  id_destino      BIGINT,
  timestamp_carga TIMESTAMP WITH TIME ZONE,
  PRIMARY KEY (empresa, tabla_origen, pk_origen)
)
```

Antes de intentar insertar cualquier registro, se verifica su existencia en esta tabla. Si existe, se ejecuta logica de actualizacion o se omite segun corresponda.

### 7.4 Control de Versiones de Registros

Cada entidad en el destino debe incluir columnas de version:

- `sispro_version INTEGER`: incrementa en cada actualizacion.
- `sispro_origen_pk VARCHAR(255)`: PK serializada del registro origen.
- `sispro_empresa VARCHAR(3)`: empresa del registro en el sistema legacy.
- `sispro_checksum CHAR(64)`: hash SHA-256 del registro en el momento de la ultima sincronizacion.
- `sispro_migrado_en TIMESTAMP WITH TIME ZONE`: timestamp de la ultima operacion del middleware.

### 7.5 Logs Auditables

- Los logs del middleware no deben contener valores de campos sensibles (claves, contrasenas, RUTs completos).
- Cada entrada de log incluye: timestamp ISO 8601, nivel (INFO/WARN/ERROR/CRITICAL), lote_id, tabla, pk_origen, accion, duracion_ms.
- Los logs se almacenan en formato estructurado JSON Lines para ingestion por herramientas de analisis.
- Rotacion de logs: diaria, retencion minima 90 dias.

---

## 8. Observabilidad

### 8.1 Metricas Obligatorias

El middleware debe exponer las siguientes metricas en formato Prometheus (o equivalente):

| Metrica | Tipo | Descripcion |
|---|---|---|
| `mw_registros_leidos_total` | Counter | Total de registros leidos desde el origen |
| `mw_registros_insertados_total` | Counter | Total de registros insertados con exito |
| `mw_registros_actualizados_total` | Counter | Total de registros actualizados |
| `mw_registros_omitidos_total` | Counter | Total de registros omitidos (sin cambio) |
| `mw_registros_rechazados_total` | Counter | Total de registros fallidos derivados a cola |
| `mw_lote_duracion_segundos` | Histogram | Duracion de cada lote de procesamiento |
| `mw_conexion_origen_estado` | Gauge | Estado de la conexion MySQL (1=OK, 0=error) |
| `mw_conexion_destino_estado` | Gauge | Estado de la conexion PostgreSQL (1=OK, 0=error) |
| `mw_cola_errores_pendientes` | Gauge | Tamano actual de la cola de errores pendientes |
| `mw_checksum_fallos_total` | Counter | Total de fallos de checksum post insercion |
| `mw_reconciliacion_discrepancias` | Gauge | Discrepancias detectadas en ultimo ciclo de reconciliacion |

### 8.2 Logs Estructurados

Cada evento de procesamiento genera una entrada en formato JSON Lines:

```json
{
  "timestamp": "2026-02-26T15:00:00-03:00",
  "nivel": "INFO",
  "lote_id": "uuid-v4",
  "tabla_origen": "enc_solicosto",
  "empresa": "ANT",
  "pk_origen": "{\"Empresa\":\"ANT\",\"Nro_Solicitud\":12345}",
  "accion": "INSERT",
  "duracion_ms": 12,
  "checksum": "sha256hex",
  "version_middleware": "1.0.0"
}
```

### 8.3 Eventos Auditables

Los siguientes eventos deben generar entradas en la tabla `migracion_auditoria` ademas del log:

- Inicio y fin de cada lote.
- Cada INSERT, UPDATE y SKIP exitoso.
- Cada ERROR con su clasificacion.
- Cada operacion de reconciliacion con resultado.
- Inicio y fin del proceso completo de migracion historica.
- Cada reprocesamiento de la cola de errores.

### 8.4 Alertas Criticas

Las siguientes condiciones deben generar alertas inmediatas al equipo tecnico:

| Condicion | Nivel | Accion sugerida |
|---|---|---|
| Fallo de conexion al origen por mas de 3 minutos | CRITICO | Verificar disponibilidad del servidor `192.168.2.3` |
| Fallo de checksum post insercion | CRITICO | Suspender lote, investigar corrupcion |
| Discrepancia en reconciliacion | CRITICO | Suspender siguiente lote, revisar manualmente |
| Cola de errores supera 1,000 registros | ALTO | Revisar causas sistematicas de rechazo |
| Lote con tasa de rechazo superior al 5% | ALTO | Revisar calidad de datos en el rango procesado |
| Duracion de lote supera 10 minutos | ADVERTENCIA | Revisar volumen de datos y performance |

---

## 9. Estrategia de Ejecucion

### 9.1 Modalidad Seleccionada: Hibrida (Batch historico + Near Real Time incremental)

**Justificacion tecnica:**

El sistema origen `bdsispro` no dispone de API, webhooks, CDC (Change Data Capture) ni mecanismo de eventos. El acceso es unicamente via consulta SQL directa. Por lo tanto:

1. **Fase 1 — Migracion historica inicial (Batch):** Procesamiento secuencial completo de todos los registros historicos acumulados. Se ejecuta fuera del horario operativo del sistema legacy (ventana nocturna o fin de semana). Prioridad de carga segun dependencias referenciales (datos maestros primero, luego transacciones).

2. **Fase 2 — Sincronizacion incremental (Near Real Time):** Una vez completada la migracion historica, el middleware ejecuta ciclos de sincronizacion periodicos (frecuencia configurable, default: cada 15 minutos) que leen solo los registros nuevos o modificados desde la ultima sincronizacion.

**Deteccion de cambios incrementales:** La base `bdsispro` no tiene columnas de timestamp de modificacion en todas las tablas. Para detectar cambios se utilizan las siguientes estrategias por prioridad:

- Si la tabla tiene `Fec_modif`, `Fechaultmod`, `FECH_MODI` u equivalente: filtrar por fecha mayor al ultimo ciclo.
- Si la tabla solo tiene contador de fila (PK incremental como `double`): filtrar por PK mayor al ultimo valor procesado.
- Si ninguno aplica (ej. tablas sin PK formal como `moviconta`, `det_invfisico`): lectura completa y deduplicacion por checksum.

### 9.2 Orden de Carga por Dependencias Referenciales

```
FASE A — Datos de referencia invariantes:
  1. empresas
  2. parametros (catalogo generico — todos los tipos)
  3. configuracion_sistema
  4. locales
  5. cencosto
  6. gastos / itemgasto / bancos
  7. plantillas
  8. preciofamilia

FASE B — Datos maestros operativos:
  9.  articulos + medidas_alternativas
  10. clientes
  11. proveedores + proveebordado
  12. ctasctes
  13. receptores
  14. relacioncodigos
  15. usuarios + accesos (con transformacion de permisos)

FASE C — Documentos de costo (core del modulo MenuCosto):
  16. enc_solicosto
  17. det_solicosto (hijo de enc_solicosto)
  18. acc_solicosto (hijo de enc_solicosto)
  19. pla_solicosto (hijo de enc_solicosto)
  20. tal_solicosto (hijo de enc_solicosto)
  21. tal_solicostores (hijo de enc_solicosto)
  22. log_solicosto (auditoria, sin PK formal)
  23. com_solicosto

FASE D — Ordenes operativas:
  24. enc_solicompra → det_solicompra → tal_solicompra
  25. enc_occliente  → det_occliente  → tal_occliente
  26. enc_ocompra    → det_ocompra    → tal_ocompra → desrec_ocompra → pago_ocompra
  27. enc_otrabajo   → det_otrabajo   → tal_otrabajo
  28. enc_oservicios → mat_oservicios → req_oservicios → ent_oservicios
  29. enc_ocbordados → det_ocbordados
  30. ocrecuperada

FASE E — Documentos comerciales:
  31. enc_documento → det_documento → ser_documento → desrec_documento → pago_documento
  32. enc_guias     → det_guias     → desrec_guias  → pago_guias
  33. documentovtas / docucompra

FASE F — Inventario:
  34. enc_inventario → documento_inv
  35. enc_invfisico  → det_invfisico
  36. invvalorizado

FASE G — Contabilidad:
  37. cuentas
  38. foliocomprobantes
  39. corrcomprobante
  40. moviconta
  41. movicompra
  42. libcheques / libvctos / ctasctes (movimientos)
  43. correlativos / folios / secuencia / periodos

FASE H — Configuracion de gestion:
  44. paramalarmas / alarmas
  45. paramcomisiones / pago_comisiones
  46. paramcorreccion / enc_correccion / det_correccion
  47. paramestresultado / presupestresultado

FASE I — Evaluaciones (volumen minimo):
  48. enc_evaluacion / det_evaluacion
  49. enc_cotizacion / det_cotizacion
```

---

## 10. Rendimiento y Escalabilidad

### 10.1 Paginacion de Lecturas

- Tamano de pagina por defecto: 500 registros.
- Configurable por tabla segun el tamano promedio de fila.
- Para tablas de alto volumen (`det_invfisico` 389K filas, `moviconta` 205K filas): reducir pagina a 200 registros.
- El cursor de posicion se persiste en base de datos del middleware entre ciclos.

### 10.2 Procesamiento por Lotes (Batch Interno)

- Cada pagina leida del origen genera un lote de insercion en el destino.
- El tamano de la transaccion de insercion es igual al tamano de la pagina de lectura.
- No se acumulan registros en memoria mas alla del tamano de un lote.

### 10.3 Limites de Carga

| Parametro | Valor por defecto | Configurable |
|---|---|---|
| Registros por pagina de lectura | 500 | Si |
| Registros por transaccion de insercion | 500 | Si |
| Conexiones simultaneas al origen | 1 | No (restriccion de proteccion al legacy) |
| Conexiones al destino | 2 (lectura + escritura) | Si |
| Memoria maxima por proceso | 512 MB | Si |
| Timeout de conexion al origen | 30 segundos | Si |
| Timeout de transaccion en destino | 120 segundos | Si |

### 10.4 Control de Memoria

- Los datos de cada lote se procesan en streaming: lectura, transformacion e insercion se ejecutan secuencialmente sin acumular el lote completo en memoria.
- Los campos de tipo `longtext` se procesan con lectura en chunks si superan 1 MB.
- El snapshot JSONB almacenado en `migracion_cola_errores` se limita a 64 KB por registro; si el registro supera este limite, se trunca con indicador de truncamiento.

### 10.5 Paralelizacion Segura

La paralelizacion esta restringida para proteger el sistema legacy:

- **Paralelizacion de lectura del origen:** PROHIBIDA. Solo una conexion activa al MySQL de produccion en cualquier momento.
- **Paralelizacion de escritura en destino:** PERMITIDA con precauciones. Se puede paralelizar la escritura en el destino solo entre tablas sin dependencias referenciales entre si (ej. `clientes` y `proveedores` pueden cargarse en paralelo; `enc_ocompra` y `det_ocompra` no).
- El grafo de dependencias de la Fase de carga (seccion 9.2) define los limites de paralelizacion segura.

---

## 11. Riesgos Reales y Mitigaciones

| ID | Riesgo | Probabilidad | Impacto | Mitigacion |
|---|---|---|---|---|
| RM-01 | Datos huerfanos sin encabezado padre (sin FK declaradas en origen) | Alta | Alto | Pipeline de validacion referencial previa a cada carga; cola de huerfanos separada |
| RM-02 | Identificadores `double` con valores decimales (.5, .3) no esperados | Media | Medio | Rechazo inmediato con log de ID exacto; revision manual del registro origen |
| RM-03 | Contrasenas de usuarios Sispro sin hash en tabla `usuarios` | Alta | Critico | No migrar campo CLAVE_USUARIO; forzar restablecimiento de contrasena en el ERP para todos los usuarios |
| RM-04 | Tablas temporales (tmp_*) con datos de sesion en curso durante la lectura | Alta | Medio | Excluir todas las tablas tmp_* del proceso de migracion |
| RM-05 | Sistema legacy en uso activo durante la lectura (escrituras concurrentes) | Alta | Alto | Leer con nivel READ COMMITTED; aceptar consistencia eventual; la sincronizacion incremental corrige deltaes |
| RM-06 | Campo `Empresa` con valores distintos a los 5 registrados en tabla `empresas` | Baja | Alto | Rechazar registros con empresa no registrada en la tabla `empresas` del origen |
| RM-07 | Columnas con encoding corrupto (caracteres ? observados en campo `Ubicaci¾n` de `articulos`) | Confirmada | Medio | Sanitizacion UTF-8 obligatoria en pipeline de transformacion; log de campos sanitizados |
| RM-08 | `invvalorizado` y `det_invfisico` sin PK formal, con posibles duplicados exactos | Alta | Medio | Deduplicacion por checksum completo del registro; conservar el primero |
| RM-09 | `moviconta` (205K filas) sin PK formal y con indices compuestos no declarados como UNIQUE | Alta | Alto | Lectura ordenada por indices disponibles; deduplicacion por checksum; verificacion de reconciliacion al finalizar |
| RM-10 | Fallo del servidor MySQL 192.168.2.3 durante una migracion en curso | Baja | Critico | Persistencia del cursor de posicion en cada pagina procesada; reanuacion desde ultimo punto exitoso |
| RM-11 | Datos en `TraspasoOTOP.xls` (15 bytes) fuera de la base de datos | Confirmada | Bajo | Documentar como dato no migrable; notificar al equipo funcional para decision manual |
| RM-12 | Tabla `log_solicosto` sin PK formal con 19,354 registros de auditoria | Alta | Bajo | Migrar como tabla de auditoria de solo lectura; deduplicar por checksum |
| RM-13 | Ejecucion en horario de produccion impactando conexiones del sistema legacy | Media | Alto | Restriccion: una sola conexion de lectura; ventana de ejecucion fuera del horario operativo para la migracion historica |

---

## 12. Contexto del Sistema Origen

Esta seccion documenta los hechos tecnicos verificados del sistema origen que condicionan el diseno del middleware.

### 12.1 Arquitectura del Sistema Legacy

- Estilo: Cliente-Servidor Two-Tier. Sin capa de servicios intermediaria.
- Ejecutables: binarios precompilados (inferido VB6 o equivalente). 177 archivos en directorio de aplicaciones.
- Acceso a datos: directo a MySQL desde cada ejecutable. Sin ORM, sin API, sin stored procedures identificados.
- Configuracion de conexion: archivo `MenuCostos.ini` en texto plano. Credenciales expuestas en red local.
- Control de versiones: duplicacion de binarios con sufijos de fecha. Sin VCS formal.
- Nivel de madurez arquitectonica: 2/5 (Definido Informalmente).

### 12.2 Caracteristicas Criticas de la Base de Datos Origen

- Motor: MySQL. Servidor: `192.168.2.3:3306`. Base: `bdsispro`.
- Total de tablas: 110. Vistas declaradas: 0. Foreign Keys declaradas: 0.
- Integridad referencial: implicita. Gestionada exclusivamente por la logica de los ejecutables.
- Multitenant: si. Campo `Empresa varchar(3)` como primer componente de todas las PK. 5 empresas activas.
- Identificadores numericos: tipo `double` (no `int`/`bigint`). Riesgo de valores decimales.
- Tallas: modelo pivot con 30 columnas fijas (Talla01..Talla30) en tablas `tal_*`. Requiere normalizacion.
- Tablas sin PK formal: `moviconta` (204K filas), `det_invfisico` (389K filas), `invvalorizado`, `alarmas`, `log_solicosto`, `secuencia`.
- Campos con encoding problematico: `articulos.Ubicaci¾n` con caracter corrupto identificado.
- Contrasenas de usuarios: almacenadas en `varchar(50)` sin hash en tabla `usuarios`.

### 12.3 Dominios Funcionales Identificados

| Dominio | Tablas involucradas | Volume total aprox. |
|---|---|---|
| Costeo (core) | enc/det/acc/pla/tal_solicosto + logs | ~302,000 filas |
| Ordenes de Compra | enc/det/tal/desrec/pago_ocompra + ocrecuperada | ~71,000 filas |
| OC Cliente | enc/det/tal_occliente | ~17,000 filas |
| Solicitudes de Compra | enc/det/tal_solicompra | ~70,000 filas |
| Ordenes de Trabajo | enc/det/tal_otrabajo | ~37,000 filas |
| Ordenes de Servicio | enc/mat/req/ent_oservicios | ~12,000 filas |
| Inventario | enc_inventario + documento_inv + invfisico | ~500,000 filas |
| Contabilidad | moviconta + movicompra + libcheques + libvctos | ~290,000 filas |
| Maestros | articulos + clientes + proveedores + ctasctes | ~45,000 filas |

---

*Skill generada con base en: `SISPRO_MENU_COSTOS.md` (analisis arquitectonico) y `BD_SISPRO_INFO.md` (esquema real extraido de produccion).*
*Fecha: 2026-02-26 — Version: 1.0 — Uso exclusivo del equipo de arquitectura y migracion ERP.*
