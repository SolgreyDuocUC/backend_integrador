# INFORME TECNICO ARQUITECTONICO
## Modulo: MenuCosto — Sistema Sispro
### Documento de Descripcion Arquitectonica

---

> **Clasificacion:** Documento Tecnico Interno  
> **Estandares aplicados:** IEEE 42010:2011 — IEEE 1016:2009 — ISO/IEC 25010:2011  
> **Rol del analista:** Ingeniero en Informatica Senior — Arquitectura de Software y Evaluacion de Sistemas Legacy  
> **Fecha de elaboracion:** 2026-02-26  
> **Version del documento:** 1.0  
> **Alcance:** Modulo MenuCosto — `V:\Sispro\MenuCosto`

---

## Tabla de Contenidos

1. [Resumen Ejecutivo](#1-resumen-ejecutivo)
2. [Descripcion General del Modulo](#2-descripcion-general-del-modulo)
3. [Analisis Arquitectonico](#3-analisis-arquitectonico)
4. [Vista Logica del Modulo](#4-vista-logica-del-modulo)
5. [Flujo Funcional Principal](#5-flujo-funcional-principal)
6. [Flujo Tecnico de Datos](#6-flujo-tecnico-de-datos)
7. [Evaluacion de Atributos de Calidad ISO/IEC 25010](#7-evaluacion-de-atributos-de-calidad-isoiec-25010)
8. [Identificacion de Riesgos Tecnicos](#8-identificacion-de-riesgos-tecnicos)
9. [Nivel de Madurez Arquitectonica](#9-nivel-de-madurez-arquitectonica)
10. [Conclusion Tecnica](#10-conclusion-tecnica)
11. [Anexos](#11-anexos)

---

## 1. Resumen Ejecutivo

El modulo **MenuCosto** constituye el nucleo operativo del area de costeo y logistica de compras del sistema de escritorio Sispro. Opera bajo un modelo de arquitectura **cliente-servidor monolitico distribuido por aplicaciones independientes**, en el cual un ejecutable principal actua como launcher de subprocesos funcionales especializados. Cada subproceso corresponde a un ejecutable binario `.exe` precompilado que establece su propia conexion con la base de datos MySQL remota, utilizando parametros extraidos de un archivo de configuracion compartido (`MenuCostos.ini`).

El analisis identifica deficiencias estructurales significativas clasificadas dentro de las dimensiones de mantenibilidad, seguridad, escalabilidad e integridad de datos, segun los criterios del estandar ISO/IEC 25010. Se constata la ausencia de una capa de API, la inexistencia de mecanismos formales de control de versiones de aplicacion, la exposicion de credenciales en texto plano, y una gestion de versiones historica basada en la duplicacion fisica de archivos ejecutables.

El modulo es operativamente funcional en su contexto de despliegue actual, pero presenta un nivel de madurez arquitectonica **bajo-medio**, con riesgos tecnicos calificados como **Altos** y **Criticos** en las dimensiones de seguridad y obsolescencia.

---

## 2. Descripcion General del Modulo

### 2.1 Proposito Funcional

El modulo MenuCosto provee la interfaz de acceso centralizado a las funcionalidades vinculadas a la gestion de costos empresariales dentro del sistema Sispro. Su proposito principal es consolidar, desde un punto de entrada unico (launcher), el conjunto de aplicaciones ejecutables que gestionan:

- Estructuracion y calculo de costos de productos y familias.
- Emision y gestion de Ordenes de Compra (OC) a proveedores y clientes.
- Emision y control de Ordenes de Trabajo (OT) y Ordenes de Produccion (OP).
- Solicitudes de compra y sus flujos de aprobacion, verificacion y anulacion.
- Gestion de guias de corte y control de cierre de operaciones productivas.
- Exportacion de informacion de costos hacia otros sistemas o formatos.
- Generacion de informes operativos y de control de gestion sobre la cadena de valor.

### 2.2 Rol dentro del Sistema

El modulo MenuCosto actua como un **sub-sistema funcional autonomo** dentro del ecosistema Sispro. No depende de un servidor de aplicaciones centralizado; en cambio, cada ejecutable del modulo se comporta como una aplicacion de escritorio independiente que interactua directamente con la base de datos `bdsispro` alojada en un servidor MySQL en la red interna.

Dentro de la cadena de valor del negocio, este modulo se posiciona entre las areas de **produccion**, **compras** y **costos**, actuando como el sistema de registro y control de los flujos economicos asociados a dichas areas.

### 2.3 Dependencias Internas y Externas

| Tipo | Descripcion | Identificador |
|---|---|---|
| Interna | Archivo de configuracion compartido por todos los ejecutables | `MenuCostos.ini` |
| Interna | Motor de reportes Crystal Reports | Archivos `.rpt` en `\Reportes\` |
| Interna | Launcher del modulo | `MenuCostos.exe` |
| Interna | Directorio de aplicaciones | `\Aplicaciones\` |
| Interna | Registro de errores de ejecucion | `event.log` |
| Externa | Servidor de base de datos MySQL | `192.168.2.3:3306` — Base: `bdsispro` |
| Externa | Servidor de impresion en red | Referenciado en `event.log` |
| Externa | Sistema de archivos en red (unidad mapeada) | `V:\Sispro\MenuCosto\` |

---

## 3. Analisis Arquitectonico

### 3.1 Estilo Arquitectonico Identificado

El modulo opera bajo un estilo arquitectonico de tipo **Cliente-Servidor de Dos Capas (Two-Tier)** con distribucion funcional por ejecutables independientes. Este patron se caracteriza por:

- Ausencia de capa intermedia (middleware) o servidor de aplicaciones.
- Cada ejecutable `.exe` actua simultaneamente como capa de presentacion y capa de logica de negocio.
- La capa de persistencia es accedida directamente desde cada ejecutable mediante conectores de base de datos embebidos.
- La configuracion de conectividad es externa y compartida via archivo INI.

Este patron, aunque funcional para entornos de trabajo con baja concurrencia y control operativo centralizado, introduce restricciones estructurales que limitan la capacidad de escalabilidad y mantenimiento del sistema.

### 3.2 Nivel de Acoplamiento

El nivel de acoplamiento del modulo es **Alto**, fundamentado en los siguientes hallazgos:

- **Acoplamiento por datos compartidos:** Todos los ejecutables acceden a la misma base de datos `bdsispro` sin mediacion de una capa de servicio. Un cambio en el esquema de base de datos tiene impacto directo y potencial sobre la totalidad de los ejecutables.
- **Acoplamiento por configuracion:** El archivo `MenuCostos.ini` es la unica fuente de configuracion de conectividad. Si este archivo es modificado, movido o corrompido, la totalidad del modulo pierde capacidad funcional.
- **Acoplamiento por rutas fisicas:** Las rutas de aplicaciones y reportes (`PATH_APLICACIONES`, `RUTA_REP`) estan definidas de forma absoluta y estatica, lo que genera dependencia directa con la estructura de directorios del sistema operativo anfitrion.
- **Duplicacion de aplicaciones:** La presencia de multiples versiones de un mismo ejecutable con sufijos de fecha (ej. `Cos_Mant_OCCliente_20260206.exe`) indica que el control de cambios no es gestionado mediante un sistema de versionado formal, sino mediante duplicacion fisica en el sistema de archivos.

### 3.3 Nivel de Cohesion Interna

La cohesion del modulo es de tipo **Cohesion Funcional Moderada**. Cada ejecutable tiene una funcion bien delimitada en terminos de negocio; sin embargo, la separacion de responsabilidades tecnicas dentro de cada ejecutable no puede ser verificada sin acceso al codigo fuente. La observacion de patrones de nomenclatura y tamanos de archivo sugiere que cada binario amalgama presentacion, logica y acceso a datos.

### 3.4 Responsabilidades Mezcladas Identificadas

| Responsabilidad | Ejecutable de Referencia | Observacion |
|---|---|---|
| Presentacion + Negocio + Persistencia | Todos los `.exe` | No existe separacion por capas |
| Generacion de documentos impresos | `Cos_Mant_OCompra.exe`, `Cos_Man_OrdenProduccion.exe` | El ejecutable invoca los `.rpt` directamente |
| Gestion de configuracion | `MenuCostos.exe` + `MenuCostos.ini` | Config centralizada pero sin encriptacion |
| Control de versiones | Sistema de archivos (duplicacion) | No existe sistema VCS formal |

---

## 4. Vista Logica del Modulo

### 4.1 Capa de Presentacion

La capa de presentacion existe de forma implicita dentro de cada ejecutable binario precompilado. Se infiere que el entorno tecnologico de construccion de estos ejecutables es **Visual Basic 6 (VB6)** o una plataforma similar de desarrollo Windows Form de generacion anterior, dado el tamano caracteristico de los binarios (entre 61 KB y 1,085 KB) y el patron de construccion observado (launchers + modulos satelite).

**Observacion:** No existe separacion fisica entre la capa de presentacion y la capa de logica de negocio. El formulario de interfaz y la logica de proceso coexisten dentro del mismo ejecutable.

### 4.2 Capa de Negocio

La logica de negocio se encuentra embebida en cada ejecutable funcional. Los procesos identificados son:

| Ejecutable | Proceso de Negocio |
|---|---|
| `Cos_Mant_Costos.exe` | Mantenimiento de estruturas de costo |
| `Cos_Mant_OCCliente.exe` | Gestion de ordenes de compra a clientes (proceso mas activo: 14 versiones) |
| `Cos_Mant_OCompra.exe` | Gestion de ordenes de compra a proveedores |
| `Cos_Soli_Compras.exe` | Solicitudes de compra y flujo de aprobacion |
| `Cos_Man_OrdenProduccion.exe` | Ordenes de produccion |
| `Cos_OTrabajo.exe` | Ordenes de trabajo |
| `Cos_Guia_Corte.exe` | Guias de corte |
| `Cos_Exportar_Costos.exe` | Exportacion de datos de costos |
| `Cos_AutorizaRechaza_Costos.exe` | Flujo de autorizacion de costos |
| `Cos_Verificar_Compras.exe` | Verificacion de compras |
| `Cos_Modi_Costos.exe` | Modificacion de estructuras de costo |
| `Cos_Modi_Nvtas.exe` | Modificacion de notas de venta |
| `Cos_Anular_SolCompras.exe` | Anulacion de solicitudes de compra |
| `Cos_LiberaDocumentos.exe` | Liberacion de documentos |

### 4.3 Capa de Persistencia

La capa de persistencia opera mediante **acceso directo a la base de datos MySQL** desde cada ejecutable. Los parametros de conexion son:

```
Host:     192.168.2.3
Puerto:   3306
Base:     bdsispro
Usuario:  sispro
Password: sispro
```

**Observacion critica:** No existe una capa de abstraccion de datos (DAL), repositorio, ORM ni servicio de datos intermediario. Las consultas SQL son ejecutadas directamente desde el codigo de negocio embebido en cada binario.

### 4.4 Existencia de API

El modulo **no dispone de API** de ningun tipo (REST, SOAP, RPC o similar). La comunicacion entre el cliente de escritorio y la base de datos es directa, sin mediacion de servicio. Esta arquitectura imposibilita la integracion con sistemas externos sin intervencion directa sobre la base de datos.

### 4.5 Evaluacion de Principios SOLID

| Principio | Estado | Fundamento |
|---|---|---|
| Responsabilidad Unica (SRP) | **No aplicado** | Cada ejecutable concentra presentacion, negocio y persistencia |
| Abierto/Cerrado (OCP) | **No aplicado** | La extension funcional requiere duplicacion de ejecutables |
| Sustitucion de Liskov (LSP) | **No evaluable** | Sin acceso a codigo fuente |
| Segregacion de Interfaces (ISP) | **No aplicado** | No existen interfaces definidas entre capas |
| Inversion de Dependencias (DIP) | **No aplicado** | Los ejecutables dependen directamente de la base de datos concreta |

---

## 5. Flujo Funcional Principal

El flujo funcional central del modulo MenuCosto corresponde al ciclo de vida de una **Orden de Compra al Cliente**, que es el proceso con mayor cantidad de versiones activas (14 binarios identificados), lo que evidencia su criticidad operativa.

**Paso 1 — Ingreso al modulo:**  
El usuario ejecuta `MenuCostos.exe`, que lee `MenuCostos.ini` para obtener rutas y parametros de conexion, y presenta el menu principal de acceso a las funcionalidades.

**Paso 2 — Solucion de costos:**  
El operador accede a `Cos_Mant_Costos.exe` o `Cos_Soli_Costos.exe` para definir o consultar la estructura de costos del articulo o familia productiva.

**Paso 3 — Solicitud de compra:**  
Mediante `Cos_Soli_Compras.exe`, se genera la solicitud de insumos o materiales requeridos por el proceso de produccion. Esta solicitud queda registrada en la base de datos.

**Paso 4 — Autorizacion:**  
El responsable del area utiliza `Cos_AutorizaRechaza_Costos.exe` para aprobar o rechazar la solicitud registrada.

**Paso 5 — Emision de Orden de Compra:**  
Aprobada la solicitud, el operador genera la Orden de Compra mediante `Cos_Mant_OCompra.exe` (proveedor) o `Cos_Mant_OCCliente.exe` (cliente). El sistema genera el documento impreso mediante el motor Crystal Reports (`OrdenCompra.rpt`).

**Paso 6 — Verificacion:**  
`Cos_Verificar_Compras.exe` gestiona la verificacion de la recepcion o cumplimiento de la orden de compra.

**Paso 7 — Orden de Produccion / Trabajo:**  
Vinculado a la compra verificada, `Cos_Man_OrdenProduccion.exe` o `Cos_OTrabajo.exe` generan las ordenes internas de produccion o trabajo.

**Paso 8 — Guia de Corte:**  
`Cos_Guia_Corte.exe` emite la guia de corte asociada a la orden de produccion, instrumento operativo para el area de manufactura.

**Paso 9 — Cierre:**  
`Cos_Cerrar_Guias.exe` y `Cos_InformeCierreOP.exe` / `Cos_InformeCierreOT.exe` ejecutan el cierre formal del ciclo productivo y generan los informes de conclusion.

---

## 6. Flujo Tecnico de Datos

### 6.1 Entrada

Los datos de entrada son capturados directamente desde formularios de interfaz de usuario (Windows Forms embebidos en los ejecutables). No existe validacion centralizada ni esquema de validacion declarativo externo. La entrada incluye:

- Datos maestros: articulos, clientes, proveedores, modelos, telas, colores, composiciones.
- Transaccionales: cantidades, precios, fechas, identificadores de ordenes.
- Parametros de sistema: rutas, conexion, configuracion de impresion.

### 6.2 Validacion

La validacion se realiza de forma **ad-hoc dentro de cada ejecutable**, sin framework de validacion compartido. No se puede verificar la completitud de las validaciones sin acceso al codigo fuente. Los registros del archivo `event.log` evidencian que errores de conectividad con el servidor de impresion y errores de apertura de reportes Crystal Reports no son capturados de forma controlada, sino que se registran como excepciones no manejadas.

### 6.3 Procesamiento

El procesamiento de negocio ocurre dentro del mismo proceso del ejecutable cliente. No existe separacion entre el hilo de interfaz y el hilo de procesamiento de datos, lo que introduce riesgo de bloqueo de la interfaz ante operaciones de larga duracion (consultas complejas, generacion de reportes).

### 6.4 Persistencia

La persistencia se realiza mediante escritura directa en las tablas de la base de datos `bdsispro` en el servidor MySQL `192.168.2.3:3306`. No se han identificado procedimientos almacenados, vistas materializadas ni mecanismos de transaccion explicita gestionados desde el lado cliente. La integridad referencial depende totalmente del motor de base de datos.

### 6.5 Respuesta

La respuesta al usuario se materializa en dos formas:
1. Actualizacion de la interfaz de usuario en el formulario activo.
2. Generacion de documentos impresos mediante Crystal Reports (`.rpt`), con dependencia directa al servidor de impresion de red.

### 6.6 Puntos Criticos Identificados

| Punto Critico | Descripcion | Nivel |
|---|---|---|
| Credenciales en texto plano | `MenuCostos.ini` expone usuario y contrasena sin cifrado | Critico |
| Archivo INI como punto unico de falla | Sin el archivo INI, todos los ejecutables fallan al iniciar | Alto |
| Acceso directo a base de datos | Cualquier ejecutable puede ejecutar DML arbitrario sobre `bdsispro` | Critico |
| Dependencia de rutas absolutas | El modulo falla si la unidad `V:` no esta mapeada o si la ruta es relocalizada | Alto |
| Event log sin rol de alarma | Los errores se registran pero no disparan alertas ni mecanismos de recuperacion | Medio |
| Motor Crystal Reports sin version controlada | Los `.rpt` pueden desincronizarse de la version de base de datos | Alto |

---

## 7. Evaluacion de Atributos de Calidad ISO/IEC 25010

### 7.1 Mantenibilidad

**Nivel evaluado: Bajo**

La mantenibilidad del modulo se ve comprometida por multiples factores estructurales:

- La ausencia de codigo fuente versionado impide la trazabilidad de cambios. La unica evidencia de gestion de cambios es la presencia de 14 versiones del ejecutable `Cos_Mant_OCCliente` con fechas incorporadas en el nombre del archivo, lo que constituye un patron de mantenimiento por duplicacion, contrario a las practicas de gestion de configuracion estandar (IEEE 828).
- La existencia de archivos con nomenclatura informal (`Cos_Mant_OCCliente - Copy.exe`, `Cos_Mant_OCompra_rrr.exe`, `Cos_Soli_Costos_MALO.exe`) indica ausencia de control formal de artefactos de software.
- La logica de negocio embebida en binarios precompilados impide modificaciones incrementales sin recompilacion y redistribucion completa del ejecutable.

### 7.2 Seguridad

**Nivel evaluado: Critico**

- Las credenciales de acceso a la base de datos (`usr=sispro`, `Pass=sispro`) se almacenan en texto plano en el archivo `MenuCostos.ini`, el cual reside en un directorio accesible desde la red local (`V:\Sispro\MenuCosto\Aplicaciones\`). Cualquier usuario con acceso a la unidad compartida puede leer dichas credenciales sin autenticacion adicional.
- La contrasena del usuario de base de datos es identica al nombre de usuario (`sispro`), lo que constituye una vulnerabilidad de credencial debil clasificable bajo CWE-521.
- No se evidencia ninguna capa de autenticacion entre la aplicacion cliente y la base de datos que no sea la autenticacion municipal del motor MySQL.
- No existe control de acceso por rol a nivel de aplicacion verificable desde el analisis estatico de los artefactos disponibles. La aplicacion `Cos_Usuarios.exe` sugiere la existencia de un modulo de usuarios, pero su alcance de control no puede determinarse sin codigo fuente.
- No existe cifrado de comunicaciones entre los clientes de escritorio y el servidor MySQL (sin TLS/SSL evidente).

### 7.3 Fiabilidad

**Nivel evaluado: Medio-Bajo**

- El archivo `event.log` registra errores recurrentes de tipo "Server has not yet been opened" asociados al motor Crystal Reports, lo que evidencia fallos en la generacion de reportes ante condiciones de conectividad inestable o configuracion de impresora.
- No se identifican mecanismos de recuperacion ante fallos de conexion a la base de datos. Si el servidor `192.168.2.3` no es alcanzable, los ejecutables fallan sin manejo estructurado del error.
- La ausencia de un mecanismo de transacciones explicito en la capa cliente introduce riesgo de datos inconsistentes ante fallo de proceso durante una operacion de escritura.

### 7.4 Rendimiento

**Nivel evaluado: No determinable con precision / Riesgo Medio**

- Las operaciones de generacion de reportes Crystal Reports con documentos de tamano significativo (ej. `Gen_Costos.rpt`: 248 KB, `OrdenCompra.rpt`: 666 KB) sobre conexiones de red pueden introducir latencia perceptible para el usuario.
- La ausencia de cache, paginacion o procesamiento asincrono introduce riesgo de degradacion de la experiencia de usuario en operaciones de alto volumen de datos.
- No se pueden cuantificar tiempos de respuesta sin instrumentacion activa del sistema.

### 7.5 Escalabilidad

**Nivel evaluado: Bajo**

- El modelo de acceso directo a la base de datos desde multiples clientes simultaneos, sin pool de conexiones centralizado, expone al servidor MySQL a una presion de conexiones proporcional al numero de usuarios activos.
- La arquitectura two-tier no admite escalabilidad horizontal sin una refactorizacion estructural profunda.
- La adicion de nuevas funcionalidades requiere la generacion de un nuevo ejecutable independiente, lo que agrava el problema de proliferacion de binarios ya observado.

### 7.6 Integridad

**Nivel evaluado: Medio**

- La integridad de los datos depende exclusivamente de las restricciones definidas en el motor MySQL (`bdsispro`). No existen capas adicionales de validacion centralizada en el lado aplicativo.
- La existencia de operaciones de modificacion directa (`Cos_Modi_Costos.exe`, `Cos_Modi_Nvtas.exe`) y de recuperacion de ordenes (`Cos_RecuperaOC.exe`) sin mecanismo de auditoria observable representa un riesgo de integridad operacional.
- El archivo `TraspasoOTOP.xls` (15 bytes) sugiere la utilizacion de hojas de calculo como medio de traspaso de datos entre modulos, lo que representa un vector de riesgo de integridad de datos fuera del control de la base de datos.

### 7.7 Usabilidad

**Nivel evaluado: No evaluable con precision / Observaciones estructurales**

- La usabilidad no puede evaluarse completamente sin acceso a las interfaces graficas de los ejecutables. Sin embargo, la existencia de multiples versiones de los mismos ejecutables disponibles simultaneamente en el directorio de aplicaciones (`Cos_Mant_OCCliente_20260206.exe` junto a `Cos_Mant_OCCliente.exe`) representa un riesgo de confusion para el usuario operativo respecto a cual version es la vigente.
- La generacion de documentos impresos mediante Crystal Reports introduce una dependencia de impresora que, como evidencia el `event.log`, puede derivar en fallos visibles para el usuario sin mensajes de error informativos adecuados.

---

## 8. Identificacion de Riesgos Tecnicos

### 8.1 Riesgos Arquitectonicos

| ID | Riesgo | Criticidad | Fundamento |
|---|---|---|---|
| RA-01 | Ausencia de capa de servicio intermediaria | Alto | El acceso directo a BD desde multiples clientes impide control centralizado de transacciones y reglas de negocio |
| RA-02 | Punto unico de falla en archivo INI | Alto | La corrupcion o perdida de `MenuCostos.ini` inhabilita la totalidad del modulo |
| RA-03 | Ausencia de separacion de capas (N-Tier) | Alto | La mezcla de presentacion, logica y persistencia en un solo ejecutable impide mantenimiento independiente por capa |
| RA-04 | Proliferacion descontrolada de ejecutables | Medio | 177 archivos en directorio de aplicaciones sin mecanismo formal de clasificacion o depuracion |

### 8.2 Riesgos Operativos

| ID | Riesgo | Criticidad | Fundamento |
|---|---|---|---|
| RO-01 | Dependencia de ruta de red mapeada | Alto | Si la unidad `V:` no esta mapeada al inicio del ejecutable, el sistema no funciona |
| RO-02 | Fallos no manejados en generacion de reportes | Medio | El `event.log` registra errores de impresion y apertura de reportes sin recuperacion automatica |
| RO-03 | Ausencia de mecanismo de backup de configuracion | Medio | No se evidencia copia de respaldo automatica del archivo `MenuCostos.ini` |
| RO-04 | Datos de traspaso fuera de la base de datos | Medio | El archivo `TraspasoOTOP.xls` sugiere intercambio de datos via hoja de calculo sin control de integridad |

### 8.3 Riesgos Tecnologicos

| ID | Riesgo | Criticidad | Fundamento |
|---|---|---|---|
| RT-01 | Tecnologia de construccion legacy (VB6 o similar) | Critico | Los binarios presentan caracteristicas compatibles con entornos de desarrollo discontinuados por Microsoft, lo que implica ausencia de soporte oficial y limitaciones de integracion con plataformas modernas |
| RT-02 | Motor Crystal Reports sin version declarada | Alto | La version del motor Crystal Reports utilizada no esta documentada, lo que introduce riesgo de incompatibilidad ante actualizaciones del sistema operativo |
| RT-03 | Ausencia de framework de validacion | Alto | Cada ejecutable implementa validaciones propias sin esquema compartido, generando inconsistencias potenciales entre modulos |

### 8.4 Riesgos de Seguridad

| ID | Riesgo | Criticidad | Fundamento |
|---|---|---|---|
| RS-01 | Credenciales de BD en texto plano | Critico | `MenuCostos.ini` expone usuario y contrasena de MySQL sin cifrado en directorio accesible en red |
| RS-02 | Credencial debil de base de datos | Critico | La contrasena del usuario `sispro` es igual al nombre de usuario, violando principios basicos de autenticacion segura |
| RS-03 | Ausencia de cifrado en comunicacion cliente-servidor | Alto | No se evidencia uso de TLS/SSL en la conexion MySQL desde los clientes de escritorio |
| RS-04 | Sin control de acceso a nivel de aplicacion verificable | Medio | El alcance del modulo `Cos_Usuarios.exe` no puede determinarse sin fuente; no se descarta acceso sin restriccion de roles |

### 8.5 Riesgos de Obsolescencia

| ID | Riesgo | Criticidad | Fundamento |
|---|---|---|---|
| ROB-01 | Plataforma de desarrollo discontinuada | Critico | Sin capacidad de modificar el codigo fuente con herramientas actuales, el modulo no puede ser corregido ni extendido ante nuevos requerimientos operativos |
| ROB-02 | Ausencia de documentacion tecnica previa | Alto | No se identifico documentacion de arquitectura, diagramas de base de datos, ni especificaciones funcionales previas al presente informe |
| ROB-03 | Gestion de versiones por duplicacion de binarios | Alto | El patron de nombrado con fecha (`_20260206`) indica ausencia de un sistema VCS, lo que impide trazabilidad de cambios y recuperacion de versiones anteriores de forma controlada |

---

## 9. Nivel de Madurez Arquitectonica

### Estimacion

**Nivel de Madurez: 2 — Definido Informalmente (escala 1-5)**

### Justificacion Tecnica

La estimacion de madurez se fundamenta en los siguientes criterios evaluados:

| Dimension | Nivel Observado | Fundamento |
|---|---|---|
| Separacion de capas | Deficiente | Presentacion, negocio y persistencia en un solo ejecutable |
| Documentacion arquitectonica | Ausente | No se identifico documentacion tecnica preexistente |
| Control de configuracion | Basico | Archivo INI como unico mecanismo de configuracion, en texto plano |
| Gestion de versiones | Informal | Duplicacion de binarios con sufijo de fecha como unico mecanismo de versionado |
| Seguridad | Critica | Credenciales expuestas, sin cifrado ni control de acceso verificable |
| Modularidad | Baja | Cada ejecutable es un modulo monolitico independiente sin interfaces definidas |
| Observabilidad | Minima | Un unico archivo `event.log` plano sin niveles de severidad estructurados |
| Capacidad de prueba | No verificable | Sin framework de pruebas observable; los binarios no permiten prueba unitaria sin codigo fuente |

El nivel 2 (Definido Informalmente) refleja que el modulo posee una estructura funcional reconocible y una separacion de responsabilidades de negocio a nivel de ejecutables, pero carece de los mecanismos arquitectonicos formales que caracterizarian un sistema con madurez media o superior: documentacion sistematica, separacion de capas, control de versiones, seguridad gestionada y mecanismos de observabilidad.

El modulo supera el nivel 1 (Ad-Hoc) unicamente por la existencia de una convencion de nomenclatura de ejecutables consistente, un archivo de configuracion centralizado y una estructura de directorios reconocible.

---

## 10. Conclusion Tecnica

El modulo MenuCosto del sistema Sispro representa un sistema operativo funcional que cumple su proposito de negocio en el contexto de despliegue actual, pero exhibe un conjunto significativo de deficiencias estructurales, de seguridad y de mantenibilidad que comprometen su sostenibilidad tecnica a mediano y largo plazo.

**A mediano plazo (12 a 36 meses),** los principales factores de riesgo son:

1. La imposibilidad de extender o corregir el sistema sin acceso al entorno de desarrollo original y a las herramientas de compilacion especificas de la plataforma en que fue construido.
2. La acumulacion progresiva de versiones de ejecutables sin mecanismo de gestion formal, que incrementa el riesgo de que un usuario opere sobre una version obsoleta o incorrecta.
3. La exposicion continua de credenciales de base de datos en texto plano en un archivo accesible por la red local, lo que representa un vector de compromiso activo.

**A largo plazo (mas de 36 meses),** el riesgo mas significativo es la obsolescencia tecnologica de la plataforma de desarrollo subyacente. Sin capacidad de recompilacion, cualquier cambio funcional requerira la reescritura completa de los modulos afectados en una nueva plataforma, sin documentacion de arquitectura ni especificacion funcional formal que sirva de base para dicha migracion.

Se concluye que el modulo MenuCosto, en su estado actual, es **tecnicamente insostenible a largo plazo** sin una intervencion estructurada de documentacion, seguridad y gestion de configuracion como primer nivel de estabilizacion, seguida de una evaluacion de migracion progresiva hacia una arquitectura con separacion de capas, API centralizada y mecanismos de seguridad conforme a estandares modernos.

La continuidad operativa inmediata no esta en riesgo ante el mantenimiento del entorno de infraestructura actual (servidor MySQL en `192.168.2.3`, unidad de red `V:` disponible, impresora de red accesible), pero la capacidad de evolucion funcional del sistema es practicamente nula bajo la arquitectura presente.

---

## 11. Anexos

### Anexo A — Inventario de Ejecutables del Modulo (Directorio Aplicaciones)

| Ejecutable | Tamano (KB) | Funcion Inferida |
|---|---|---|
| `MenuCostos.exe` | 136 | Launcher principal del modulo |
| `Cos_Mant_OCCliente.exe` | 1,061 | Gestion de OC a Clientes (version vigente) |
| `Cos_Mant_OCompra.exe` | 876 | Gestion de Ordenes de Compra a Proveedores |
| `Cos_Mant_Costos.exe` | 720 | Mantenimiento de Estructuras de Costo |
| `Cos_Modi_Nvtas.exe` | 1,024 | Modificacion de Notas de Venta |
| `Cos_Soli_Compras.exe` | 712 | Solicitudes de Compra |
| `Cos_Man_OrdenProduccion.exe` | 580 | Ordenes de Produccion |
| `Cos_Guia_Corte.exe` | 716 | Guias de Corte |
| `Cos_OTrabajo.exe` | 608 | Ordenes de Trabajo |
| `Cos_Verificar_Compras.exe` | 584 | Verificacion de Recepciones de Compra |
| `Cos_Modi_Costos.exe` | 756 | Modificacion de Costos |
| `Cos_Exportar_Costos.exe` | 416 | Exportacion de Datos de Costos |
| `Cos_AutorizaRechaza_Costos.exe` | 360 | Autorizacion / Rechazo de Costos |
| `Cos_Mant_Avaluacion.exe` | 484 | Mantenimiento de Avaluacion |
| `Cos_Mant_OCBordado.exe` | 404 | Ordenes de Compra de Bordado |
| `Cos_Mant_OrdenServicios.exe` | 576 | Ordenes de Servicios |
| `Cos_Soli_Costos.exe` | 668 | Solicitud de Costos |
| `Cos_Cerrar_Guias.exe` | 396 | Cierre de Guias de Corte |
| `Cos_Anular_SolCompras.exe` | 236 | Anulacion de Solicitudes de Compra |
| `Cos_RecuperaOC.exe` | 240 | Recuperacion de Ordenes de Compra |
| *(+ 157 archivos adicionales entre versiones historicas, informes, configuraciones y atajos)* | — | — |

### Anexo B — Inventario de Reportes Crystal Reports (Directorio Reportes)

| Reporte | Tamano (KB) | Documento Generado |
|---|---|---|
| `OrdenCompra.rpt` | 651 | Orden de Compra a Proveedor |
| `OrdenCompraCliente.rpt` | 261 | Orden de Compra a Cliente |
| `Gen_Costos.rpt` | 243 | Informe General de Costos |
| `Gen_Guia.rpt` | 241 | Guia de Corte |
| `OrdenCompraBordado.rpt` | 499 | Orden de Compra de Bordado |
| `OrdenServicio.rpt` | 523 | Orden de Servicio |
| `Orden_Produccion.rpt` | 155 | Orden de Produccion |
| `O_Trabajo.rpt` | 119 | Orden de Trabajo |
| `Hoja_Compra.rpt` | 169 | Hoja de Compra |
| `S_Compra.rpt` | 159 | Solicitud de Compra |
| `Soli_Costos.rpt` | 120 | Solicitud de Costos |
| `EvaluacionNegocios.rpt` | 88 | Evaluacion de Negocios |

### Anexo C — Contenido del Archivo de Configuracion MenuCostos.ini

```ini
[SERVIDOR]
Nom_Servidor=192.168.2.3
usr=sispro
Pass=sispro
Puerto=3306
NomDNS=Sispro_prn

[Ubicacion]
PATH_APLICACIONES=V:\Sispro\MenuCosto\Aplicaciones\

[RUTA_R]
RUTA_REP=V:\Sispro\MenuCosto\Reportes\

[BASE]
Nom_Base=bdsispro
```

**Nota de seguridad:** Este archivo contiene credenciales de acceso a la base de datos en texto plano. La seccion `[SERVIDOR]` expone usuario, contrasena, host y puerto del motor MySQL de produccion.

### Anexo D — Registro de Errores Observados (event.log)

| Fecha | Severidad | Artefacto | Descripcion del Error |
|---|---|---|---|
| 2022-10-10 | 4 | `Orden_Produccion.rpt` | Server has not yet been opened |
| 2022-12-16 | 4 | `Orden_Produccion.rpt` | Server has not yet been opened |
| 2024-01-17 | 4 | `Orden_Produccion.rpt` | Server has not yet been opened |
| 2024-02-20 | 4 | `Hoja_Compra.rpt` | Error starting print job |

**Observacion:** El campo de severidad con valor `4` puede corresponder a un nivel de error en la escala del motor Crystal Reports. Los errores registrados son recurrentes y abarcan un periodo de mas de un ano, lo que indica que no han sido resueltos de forma definitiva.

---

*Documento generado conforme a la skill `sispro_analitic` — Estandares IEEE 42010:2011, IEEE 1016:2009, ISO/IEC 25010:2011.*  
*Fecha de elaboracion: 2026-02-26 — Version: 1.0*
