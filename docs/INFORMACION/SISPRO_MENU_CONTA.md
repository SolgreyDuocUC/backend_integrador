# DOCUMENTO DE DESCRIPCIÓN DE ARQUITECTURA

**Basado en ISO/IEC/IEEE 42010 – antes IEEE 1471**

---

## 1. INFORMACIÓN GENERAL

### 1.1 Identificación del Documento

| Campo                     | Valor                                              |
| ------------------------- | -------------------------------------------------- |
| **Nombre del sistema**    | SISPRO – Módulo de Contabilidad (MenuContabilidad) |
| **Organización**          | Socofar S.A. (según metadatos del proyecto VBP)    |
| **Versión del documento** | 1.0.0                                              |
| **Fecha**                 | 2026-02-25                                         |
| **Autor(es)**             | Equipo de TI / Análisis Arquitectónico             |
| **Aprobado por**          | Por definir                                        |
| **Estado del documento**  | Borrador                                           |

---

### 1.2 Propósito del Documento

Este documento describe la arquitectura actual del módulo **MenuContabilidad** del sistema **SISPRO**, con el objetivo de analizar su estado técnico, identificar riesgos tecnológicos, evaluar su alineación con las necesidades del negocio y establecer lineamientos claros para su mejora, modernización o migración hacia una arquitectura más sostenible.

El análisis ha sido realizado a partir del código fuente del proyecto Visual Basic 6 (`.vbp`, `.frm`, `.bas`), archivos de configuración (`.INI`) y las dependencias externas referenciadas (Crystal Reports, ADO, MySQL ODBC).

---

### 1.3 Alcance

**Incluye:**

- Arquitectura lógica del módulo contable
- Arquitectura física (infraestructura y conectividad)
- Componentes principales (formularios, módulos compartidos, EXEs satélite)
- Flujos de operación (login, lanzamiento de submódulos, conexión a BD)
- Evaluación técnica y atributos de calidad

**No incluye:**

- Manual de usuario o de operación detallado
- Código fuente completo de los ~40 EXEs satélite
- Esquema completo de base de datos (tablas, índices, procedimientos almacenados)
- Cobertura de módulos fuera del contexto de Contabilidad

---

### 1.4 Definiciones, Acrónimos y Abreviaturas

| Término             | Definición                                                                                        |
| ------------------- | ------------------------------------------------------------------------------------------------- |
| **ADO**             | ActiveX Data Objects – tecnología Microsoft de acceso a bases de datos                            |
| **DAO**             | Data Access Objects – tecnología Microsoft legada de acceso a bases de datos locales (Access/Jet) |
| **COM**             | Component Object Model – modelo de componentes binario de Microsoft usado en VB6                  |
| **OCX**             | OLE Control Extension – controles COM reutilizables en VB6                                        |
| **VBP**             | Visual Basic Project – archivo de proyecto de Visual Basic 6                                      |
| **FRM**             | Visual Basic Form – formulario de interfaz gráfica de usuario en VB6                              |
| **BAS**             | Visual Basic Module – módulo de código global compartido en VB6                                   |
| **INI**             | Archivo de configuración de texto plano (Windows)                                                 |
| **ODBC**            | Open Database Connectivity – estándar de acceso a bases de datos                                  |
| **EXE**             | Ejecutable compilado de Windows (Win32)                                                           |
| **Shell**           | Función de VB6 que lanza un proceso externo (otro EXE)                                            |
| **Crystal Reports** | Herramienta de generación de reportes, versión 8 (Seagate/Business Objects)                       |
| **SISPRO**          | Sistema de Gestión de Soporte y Desarrollo de la organización                                     |
| **API**             | Application Programming Interface                                                                 |
| **UI**              | User Interface – Interfaz de Usuario                                                              |
| **SLA**             | Service Level Agreement – Acuerdo de Nivel de Servicio                                            |
| **CI/CD**           | Continuous Integration / Continuous Delivery                                                      |
| **On-premise**      | Infraestructura instalada físicamente en las instalaciones de la organización                     |

---

## 2. CONTEXTO DEL SISTEMA

### 2.1 Descripción General

**SISPRO – MenuContabilidad** es el módulo de **punto de entrada y navegación principal** del sistema de contabilidad de la organización. Su rol arquitectónico es el de **orquestador de interfaz**: gestiona la autenticación del usuario, establece la conexión con la base de datos MySQL corporativa y actúa como **lanzador de aplicaciones** para los distintos submódulos contables (EXEs independientes).

**Problema de negocio que resuelve:**
Proporciona un punto de acceso unificado y controlado al ecosistema de aplicaciones contables de la empresa, administrando sesiones multiempresa para un entorno corporativo con múltiples razones sociales.

**Módulos/submódulos lanzados (identificados en el código):**

| Grupo                   | Submódulos (EXE satélite)                                                                              |
| ----------------------- | ------------------------------------------------------------------------------------------------------ |
| **M01 – Comprobantes**  | IngresarComprobante, ModificarComprobante, EliminarComprobante, ImprimirComprobante                    |
| **M02 – Consultas**     | ConsultaPlanCtas, ConsultaMovCuenta, otros                                                             |
| **M03 – Reportes**      | Reportes contables (Crystal Reports)                                                                   |
| **M04 – Proceso IVA**   | LibroCompras, LibroVentas, LibroTransacciones, otros                                                   |
| **M05 – Presupuesto**   | Gestión presupuestaria                                                                                 |
| **M06 – Pagos/Cheques** | CentralizarCheques, PrepararOtrosPagos, ModificarOtrosPagos, DesmarcarCheques, PagoComisiones          |
| **M07 – Mantenimiento** | Periodos, DefinirCuenta, Usuarios, Apertura, Comprobacion, DatosEmpresa, Foliador, LiberarComprobantes |

---

### 2.2 Stakeholders

| Stakeholder               | Rol                     | Intereses                                                    | Preocupaciones                                                               |
| ------------------------- | ----------------------- | ------------------------------------------------------------ | ---------------------------------------------------------------------------- |
| **Gerencia**              | Tomador de decisiones   | Continuidad del negocio, generación de reportes financieros  | Obsolescencia tecnológica, riesgo de pérdida de soporte del sistema          |
| **Equipo TI**             | Mantenimiento y soporte | Estabilidad del ambiente de ejecución, compatibilidad con SO | Dependencias rígidas de DLLs/OCX sin soporte, dificultad de debugging        |
| **Usuarios (Contadores)** | Operadores del sistema  | Acceso fluido a todos los submódulos, generación de reportes | Lentitud, errores de "aplicación no instalada", mensajes de error técnicos   |
| **Auditoría**             | Control y cumplimiento  | Trazabilidad de operaciones, integridad de datos contables   | Ausencia de log de auditoría centralizado, contraseñas en texto plano en INI |

---

### 2.3 Entorno Operacional

| Atributo                            | Valor                                                                                                                                                                                       |
| ----------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Tipo de sistema**                 | Escritorio (Win32 – cliente pesado)                                                                                                                                                         |
| **Infraestructura**                 | On-premise                                                                                                                                                                                  |
| **Motor de BD**                     | MySQL (base: `bdsispro`, servidor: `localhost` / `192.168.2.2`, puerto `3306`)                                                                                                              |
| **Usuarios concurrentes estimados** | Bajo-medio (arquitectura de un EXE por usuario, sin gestión de concurrencia explícita)                                                                                                      |
| **Integraciones externas**          | Crystal Reports 8 (generación de reportes), MySQL ODBC 3.51 Driver                                                                                                                          |
| **Dependencias críticas**           | `craxdrt.dll` (Crystal Reports), `msado26.tlb`, `msado28.tlb`, `msodbc` 3.51, `COMCTL32.OCX`, `MSFLXGRD.OCX`, `MSMASK32.OCX`, `MSCOMCT2.OCX`, `TABCTL32.OCX`, `FLEXWIZ.OCX`, `Crystl32.OCX` |
| **Sistema Operativo requerido**     | Windows (32-bit compatible, tipicamente Windows XP/7/10 con capa de compatibilidad WOW64)                                                                                                   |
| **Ruta de aplicaciones**            | `V:\Sispro\MenuConta\Aplicaciones\`                                                                                                                                                         |
| **Ruta de reportes**                | `V:\Sispro\MenuConta\Reportes\`                                                                                                                                                             |

---

## 3. ARQUITECTURA ACTUAL

### 3.1 Enfoque Arquitectónico

**Estilo predominante: ☑ Monolítico modular (distribución de EXEs)**

El sistema sigue un patrón **"Hub-and-Spoke"** de escritorio legacy:

- Un **EXE central** (`MenuContabilidad.exe`) actúa como hub de navegación y autenticación.
- Cada funcionalidad contable existe como un **EXE satélite independiente**, lanzado por `Shell` con parámetros de sesión pasados como cadena CSV.
- No existe un bus de servicios, API REST ni ningún mecanismo de comunicación inter-proceso más allá del paso de parámetros por línea de comandos.
- La **base de datos MySQL** es el único punto de integración real entre todos los módulos.

```
[Usuario] → MenuContabilidad.exe (Hub)
                ├── Acceso.frm (Autenticación)
                ├── General.bas (Conexión BD + Variables Globales)
                └── Shell → [EXE_01].exe
                         → [EXE_02].exe
                         → ...
                         → [EXE_N].exe (~40 EXEs)
                              │
                              └──── MySQL (bdsispro) ──── Crystal Reports 8
```

---

### 3.2 Vista de Contexto

```
┌─────────────────────────────────────────────────────────────┐
│                       CLIENTE WINDOWS                        │
│                                                             │
│   [MenuContabilidad.exe]  ←→  [EXEs Satélite x ~40]        │
│            │                         │                      │
│            └─────── MySQL ODBC 3.51 ─┘                      │
│                          │                                   │
└──────────────────────────│───────────────────────────────────┘
                           │
                    ┌──────▼──────┐
                    │  MySQL 5.x  │
                    │  bdsispro   │
                    │ :3306       │
                    └─────────────┘
                           │
                    ┌──────▼──────┐
                    │  Crystal    │
                    │  Reports 8  │
                    │  (reportes) │
                    └─────────────┘
```

**Actores externos:**

- **Usuarios (Contadores/Admin):** interactúan exclusivamente con interfaces Win32
- **Servidor MySQL:** único proveedor de persistencia de datos
- **Sistema de archivos compartido (V:\):** aloja los EXEs, reportes e INI

---

### 3.3 Vista Lógica

#### 3.3.1 Capa de Presentación

| Atributo                     | Detalle                                                                                                                                                                                                       |
| ---------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Tecnologías utilizadas**   | Visual Basic 6.0 (Forms Win32), controles OCX (COMCTL32, MSFLXGRD, MSMASK32, MSCOMCT2, TABCTL32, FLEXWIZ, Crystl32)                                                                                           |
| **Nivel de desacoplamiento** | **Nulo** – UI y lógica de negocio están mezcladas en los mismos archivos `.frm`                                                                                                                               |
| **Patrón implementado**      | Ninguno formal (código-detrás / "code-behind" sin capa de presentación separada)                                                                                                                              |
| **Problemas detectados**     | Lógica SQL directamente en eventos de UI (`Click`, `LostFocus`), sin separación de responsabilidades, sin manejo estructurado de errores en todos los puntos, dependencia de controles OCX sin soporte actual |

#### 3.3.2 Capa de Negocio

| Atributo                            | Detalle                                                                                                                                        |
| ----------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------- |
| **Organización interna**            | Módulos BAS globales (`General.bas`, `Validaciones.bas`) con variables globales compartidas                                                    |
| **Separación de responsabilidades** | **Deficiente** – reglas de negocio mezcladas con presentación y acceso a datos                                                                 |
| **Aplicación de principios SOLID**  | **No aplicable** – VB6 no soporta orientación a objetos completa ni interfaces formales                                                        |
| **Problemas detectados**            | Variables globales masivas (>60 `ADODB.Recordset` globales en `General.bas`), acoplamiento total entre módulos, sin pruebas unitarias posibles |

#### 3.3.3 Capa de Persistencia

| Atributo                   | Detalle                                                                                                                                                                         |
| -------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Motor de base de datos** | MySQL (configurado como `bdsispro` en `localhost`/`192.168.2.2:3306`)                                                                                                           |
| **Acceso**                 | ADO 2.6/2.8 vía ODBC 3.51 (driver `MySQL ODBC 3.51 Driver`) – versión de 2001                                                                                                   |
| **Nivel de normalización** | No evaluable sin esquema completo; tablas identificadas: `Empresas`, `Usuarios`, `Periodos`, `PlanCuentas`, `Comprobantes`, etc.                                                |
| **Integridad referencial** | No verificable desde el código fuente; sin ORM ni capa de validación de FK                                                                                                      |
| **Índices**                | No evaluable desde el código (requiere acceso al servidor BD)                                                                                                                   |
| **Auditoría**              | **Sin implementar** – no se detecta ningún mecanismo de log de auditoría en el código analizado                                                                                 |
| **Problemas detectados**   | SQL construido por concatenación de strings (riesgo de SQL Injection), driver ODBC obsoleto (3.51 de 2001), recordsets de cursor cliente, sin transacciones explícitas visibles |

#### 3.3.4 API / Backend

| Atributo                 | Detalle                                                                                                                          |
| ------------------------ | -------------------------------------------------------------------------------------------------------------------------------- |
| **Framework**            | No aplica – aplicación de escritorio Win32 monolítica                                                                            |
| **Arquitectura interna** | Acceso directo a BD sin capa de servicio ni API                                                                                  |
| **Versionamiento**       | No existe (`MajorVer=1`, `MinorVer=0`, `RevisionVer=0`, `AutoIncrementVer=0`)                                                    |
| **Seguridad**            | Credenciales de BD en texto plano en `MenuCon.INI` (`Pass=Sispro#05`), contraseñas de usuario en texto plano en tabla `Usuarios` |
| **Documentación**        | **Inexistente** en el código fuente                                                                                              |
| **Problemas detectados** | Sin API, sin versionamiento, sin seguridad en tránsito, credenciales comprometidas en archivo INI accesible en red compartida    |

---

### 3.4 Vista Física

| Atributo                   | Detalle                                                                                                                                                            |
| -------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| **Servidores**             | Un servidor MySQL (`192.168.2.2`) en red local; clientes Windows acceden desde sus propias estaciones                                                              |
| **Contenedores**           | No aplica                                                                                                                                                          |
| **Balanceadores**          | No aplica                                                                                                                                                          |
| **Entornos (Dev/QA/Prod)** | **Sin separación detectada** – mismo servidor, misma base de datos. El INI comenta un servidor alternativo (`192.168.2.2`) vs `localhost`                          |
| **CI/CD**                  | **No existe** – compilación manual con IDE Visual Basic 6                                                                                                          |
| **Distribución de EXEs**   | Ruta de red compartida `V:\Sispro\MenuConta\Aplicaciones\` – todos los clientes acceden a los mismos EXEs                                                          |
| **Problemas detectados**   | Sin separación de ambientes, sin automatización de despliegue, ruta de red como punto único de fallo, actualizaciones afectan a todos los usuarios simultáneamente |

---

## 4. ATRIBUTOS DE CALIDAD

### 4.1 Escalabilidad

|                           | Detalle                                                                                                                                            |
| ------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Horizontal**            | **No soportada** – arquitectura cliente-servidor sin capacidad de balanceo                                                                         |
| **Vertical**              | Limitada al hardware del servidor MySQL; los clientes son independientes                                                                           |
| **Limitaciones actuales** | Cada usuario abre conexiones ADO independientes con `root`; a mayor cantidad de usuarios concurrentes, mayor carga directa sobre MySQL sin pooling |

### 4.2 Rendimiento

|                                  | Detalle                                                                                                                |
| -------------------------------- | ---------------------------------------------------------------------------------------------------------------------- |
| **Tiempo de respuesta promedio** | No medido; dependiente de latencia de red LAN al servidor MySQL                                                        |
| **Cuellos de botella**           | Conexiones ODBC 3.51 sin pooling, `Recordset.Requery()` frecuentes, queries dinámicas sin prepared statements ni caché |
| **Uso de recursos**              | Cada EXE satélite abre su propia conexión ADO al iniciar, multiplicando conexiones activas                             |

### 4.3 Seguridad

|                               | Detalle                                                                                 |
| ----------------------------- | --------------------------------------------------------------------------------------- |
| **Autenticación**             | Formulario login con RUT + Empresa + Password; máximo 3 intentos                        |
| **Autorización**              | Por campo `Sistema='CONTAB'` en tabla `Usuarios`; sin roles granulares visibles         |
| **Protección contra ataques** | **Ninguna** – SQL concatenado directamente expone a SQL Injection                       |
| **Cifrado**                   | **Sin cifrado** – contraseñas en texto plano en BD y en INI; comunicación MySQL sin TLS |
| **Auditoría**                 | **Sin log de auditoría** – no hay registro de acciones del usuario                      |

> [!CAUTION]
> Las credenciales de la base de datos (`root`/`Sispro#05`) están en texto plano en un archivo INI accesible desde la red compartida `V:\`. Esto representa un riesgo crítico de seguridad.

### 4.4 Disponibilidad

|                              | Detalle                                                                               |
| ---------------------------- | ------------------------------------------------------------------------------------- |
| **SLA actual**               | No definido formalmente                                                               |
| **Redundancia**              | **Sin redundancia** – único servidor MySQL; si cae, todo el sistema deja de funcionar |
| **Recuperación ante fallos** | Manual; sin procedimientos automáticos de failover o backup documentados              |

### 4.5 Mantenibilidad

|                            | Detalle                                                                                                                                       |
| -------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------- |
| **Modularidad**            | Baja – la separación en EXEs ofrece cierto aislamiento funcional, pero el módulo de código es monolítico                                      |
| **Legibilidad del código** | Moderada – VB6 es legible, pero sin comentarios de documentación, con nombres de variables no estándares y mezcla de idiomas (español/inglés) |
| **Documentación**          | **Inexistente** dentro del código fuente                                                                                                      |
| **Testing**                | **Sin pruebas automatizadas** – imposible implementarlas con VB6 en este estado                                                               |

### 4.6 Observabilidad

|                  | Detalle                                                                            |
| ---------------- | ---------------------------------------------------------------------------------- |
| **Logging**      | **Ausente** – solo `MsgBox` de errores al usuario; sin log en archivo o BD         |
| **Monitoreo**    | **Ausente** – sin métricas de rendimiento ni alertas                               |
| **Trazabilidad** | **Ausente** – imposible reconstruir el flujo de una operación sin debugging manual |

---

## 5. FLUJOS DE OPERACIÓN

### 5.1 Flujo Funcional Principal

```
1. Usuario ejecuta MenuContabilidad.exe
2. Sub Main() lee parámetros de línea de comandos (Empresa, CodEmp, Usuario, Password)
3. Se lee MenuCon.INI para obtener configuración del servidor MySQL
4. Se establece conexión ADO con MySQL vía ODBC 3.51
   ├── [ÉXITO] → Se muestra Acceso.frm (formulario de login)
   └── [FALLO] → El sistema no arranca (sin mensaje de error amigable)
5. Usuario ingresa Nombre Empresa + RUT + Password
6. Acceso.frm consulta tabla Empresas (SELECT * FROM Empresas WHERE Nombre_Empresa=...)
7. Acceso.frm consulta tabla Usuarios (SELECT * FROM Usuarios WHERE Sistema='CONTAB' AND ...)
   ├── [CREDENCIALES VÁLIDAS] → Se carga MenuContabilidad.frm (menú principal)
   └── [CREDENCIALES INVÁLIDAS] → Se incrementa contador de intentos
                                    ├── [< 3 intentos] → Volver al paso 5
                                    └── [= 3 intentos] → Se descarga el formulario (End)
8. Usuario selecciona opción del menú
9. Se verifica existencia del EXE satélite en ruta de red (Dir())
   ├── [EXE EXISTE] → Shell "EXE.exe Empresa,CodEmp,Usuario,Password"
   └── [EXE NO EXISTE] → MsgBox "Aplicación no instalada, avisar a Informática"
10. EXE satélite se lanza en proceso independiente con parámetros de sesión
```

### 5.2 Flujo Técnico de Datos

| Etapa             | Detalle                                                                                                  |
| ----------------- | -------------------------------------------------------------------------------------------------------- |
| **Entrada**       | Parámetros CLI (cadena CSV) o entrada de usuario en formulario Win32                                     |
| **Validación**    | Validación de no-nulos en UI (`If campo = "" Then MsgBox...`), sin validación de tipo ni longitud formal |
| **Procesamiento** | Query SQL dinámico construido por concatenación en VB6, ejecutado directamente sobre MySQL               |
| **Persistencia**  | `ADODB.Recordset.Open` con cursor cliente (`adUseClient`), `adOpenDynamic`, `adLockBatchOptimistic`      |
| **Respuesta**     | Actualización de controles UI o lanzamiento de EXE satélite vía `Shell`                                  |

---

## 6. RIESGOS Y DEUDA TÉCNICA

### 6.1 Riesgos Técnicos

| Riesgo                                              | Impacto | Probabilidad | Nivel      |
| --------------------------------------------------- | ------- | ------------ | ---------- |
| VB6 sin soporte oficial desde 2008                  | Crítico | Alta         | 🔴 CRÍTICO |
| Crystal Reports 8 sin soporte (2003)                | Alto    | Alta         | 🔴 CRÍTICO |
| MySQL ODBC 3.51 (2001) sin soporte                  | Alto    | Alta         | 🔴 CRÍTICO |
| Credenciales BD en texto plano (INI red compartida) | Crítico | Alta         | 🔴 CRÍTICO |
| SQL Injection por concatenación de strings          | Crítico | Media        | 🔴 CRÍTICO |
| Sin separación Dev/QA/Prod                          | Alto    | Alta         | 🟠 ALTO    |
| Punto único de fallo (MySQL sin redundancia)        | Alto    | Media        | 🟠 ALTO    |
| OCX desregistrados rompen la UI completa            | Alto    | Media        | 🟠 ALTO    |
| Sin respaldo/recuperación documentado               | Alto    | Media        | 🟠 ALTO    |
| Incompatibilidad con versiones futuras de Windows   | Medio   | Media        | 🟡 MEDIO   |

### 6.2 Deuda Técnica Identificada

| Nivel          | Descripción                                                                                              |
| -------------- | -------------------------------------------------------------------------------------------------------- |
| 🔴 **Crítica** | Tecnología principal (VB6) sin soporte desde hace 18 años; imposibilidad de aplicar parches de seguridad |
| 🔴 **Crítica** | Credenciales expuestas en archivo INI accesible en red                                                   |
| 🔴 **Crítica** | SQL Injection estructural en toda la capa de datos                                                       |
| 🟠 **Alta**    | Ausencia total de pruebas automatizadas; cualquier cambio es de riesgo alto                              |
| 🟠 **Alta**    | 60+ `ADODB.Recordset` globales – gestión de memoria caótica y propenso a Memory Leaks                    |
| 🟠 **Alta**    | Sin log de auditoría ni trazabilidad operacional                                                         |
| 🟡 **Media**   | Sin documentación técnica ni comentarios en el código                                                    |
| 🟡 **Media**   | Sin separación de ambientes (Dev/QA/Prod)                                                                |
| 🟢 **Baja**    | Mezcla de idiomas en nombres de variables y mensajes de error                                            |

---

## 7. PROBLEMÁTICA DE COMPATIBILIDAD

| Problema                      | Descripción                                                                                                                                            |
| ----------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------ |
| **Dependencias rígidas**      | Requiere 12+ componentes COM/OCX registrados en la máquina cliente. La ausencia de cualquiera de ellos impide el arranque                              |
| **Integraciones frágiles**    | La integración con Crystal Reports 8 es a través de `craxdrt.dll` (versión 2003); incompatible con versiones modernas de Crystal Reports               |
| **Dificultad de migración**   | La mezcla total de capas (UI + lógica + datos en el mismo `.frm`) hace imposible la migración incremental sin reescritura                              |
| **Limitaciones tecnológicas** | VB6 es tecnología de 32-bit exclusiva; no puede aprovechar hardware de 64-bit ni extensiones modernas                                                  |
| **Bloqueos por proveedor**    | Dependencia de libraries de Microsoft (DAO, ADO) y Seagate/Business Objects (Crystal Reports) en versiones que no reciben actualizaciones de seguridad |
| **ODBC Legacy**               | El driver `MySQL ODBC 3.51` es del año 2001 y no soporta características modernas de MySQL 5.x/8.x (SSL, JSON, etc.)                                   |

---

## 8. EVALUACIÓN COMPARATIVA

| Criterio                       | Arquitectura Actual (VB6)                       | Alternativa Propuesta (Web/API moderna)                            | Impacto                 |
| ------------------------------ | ----------------------------------------------- | ------------------------------------------------------------------ | ----------------------- |
| **Escalabilidad**              | Mínima – 1 conexión/usuario                     | Alta – API stateless con pooling de conexiones                     | ✅ Mejora crítica       |
| **Complejidad de instalación** | Alta – 12+ OCX/DLL en cada cliente              | Baja – solo navegador web o cliente thin                           | ✅ Mejora crítica       |
| **Costos de mantenimiento**    | Muy alto – especialistas VB6 escasos y costosos | Bajo-medio – tecnologías mainstream con amplio mercado             | ✅ Mejora               |
| **Seguridad**                  | Crítica – múltiples vectores de ataque          | Alta – HTTPS, JWT, cifrado en tránsito, sin credenciales expuestas | ✅ Mejora crítica       |
| **Testabilidad**               | Nula                                            | Alta – pruebas unitarias, integración, E2E                         | ✅ Mejora               |
| **Riesgo de migración**        | Bajo (status quo)                               | Alto (proyecto de reescritura)                                     | ⚠️ Requiere gestión     |
| **Continuidad operacional**    | Alta (funciona hoy)                             | Media durante transición                                           | ⚠️ Requiere gestión     |
| **Tiempo de implementación**   | N/A                                             | Alto (6-24 meses según alcance)                                    | ⚠️ Considerar por fases |

---

## 9. PROPUESTA DE MEJORA O MIGRACIÓN

### 9.1 Estrategia Recomendada

> [!IMPORTANT]
> Dado el nivel de deuda técnica crítica, se recomienda una **Migración por Fases con encapsulamiento intermedio**, no una refactorización del código VB6 existente (que no sería viable ni rentable).

**Estrategia: Migración parcial → Reescritura modular por dominio**

1. **Fase de estabilización** (inmediata): Mitigar riesgos críticos de seguridad sin tocar el código
2. **Fase de encapsulamiento**: Envolver la BD existente con una API REST moderna
3. **Fase de migración**: Reescribir módulo por módulo hacia tecnología web

### 9.2 Roadmap

**Corto plazo (0-3 meses):**

- Mover credenciales de BD fuera del archivo INI (variables de entorno o gestor de secretos)
- Crear usuario MySQL dedicado con privilegios mínimos (no usar `root`)
- Habilitar SSL/TLS en conexiones MySQL
- Implementar log de auditoría mínimo a nivel de base de datos (triggers MySQL)
- Documentar el esquema actual de BD y los 40 módulos EXE

**Mediano plazo (3-12 meses):**

- Diseñar e implementar API REST (Django REST / FastAPI / Node.js) sobre la BD existente
- Migrar los módulos de mayor uso (Comprobantes, Reportes, IVA) a interfaces web
- Establecer ambiente de QA separado del productivo
- Implementar sistema de autenticación moderno (JWT + roles granulares)
- Reemplazar Crystal Reports 8 por generación de reportes vía PDF en servidor

**Largo plazo (12-24+ meses):**

- Migración completa de todos los submódulos contables a plataforma web
- Implementar CI/CD con pruebas automatizadas
- Migrar a MySQL 8.x o PostgreSQL con soporte activo
- Implementar observabilidad completa (logging centralizado, monitoreo, alertas)
- Descomisionar gradualmente los EXEs VB6

### 9.3 Impacto Esperado

| Dimensión       | Impacto                                                                         |
| --------------- | ------------------------------------------------------------------------------- |
| **Técnico**     | Eliminación de deuda técnica crítica, sistema mantenible y extensible           |
| **Operacional** | Sin instalaciones en clientes, actualizaciones centralizadas, multi-dispositivo |
| **Económico**   | Reducción de costos de soporte a largo plazo; inversión inicial en migración    |
| **Estratégico** | Independencia de proveedores legacy, capacidad de integrarse con ERPs modernos  |

---

## 10. MATRIZ DE MADUREZ ARQUITECTÓNICA

| Área                                | Puntaje (1-5) | Observaciones                                                         |
| ----------------------------------- | ------------- | --------------------------------------------------------------------- |
| **Separación de responsabilidades** | 1/5           | Nula: UI, lógica, datos mezclados en formularios VB6                  |
| **Escalabilidad**                   | 1/5           | Arquitectura cliente-servidor sin mecanismos de escala                |
| **Seguridad**                       | 1/5           | Credenciales expuestas, SQL Injection, sin cifrado, sin auditoría     |
| **Rendimiento**                     | 2/5           | Funciona en red LAN; sin optimización de queries ni caché             |
| **Mantenibilidad**                  | 2/5           | Modular en EXEs pero sin arquitectura interna limpia                  |
| **Documentación**                   | 1/5           | Inexistente en código; este documento es el primer registro formal    |
| **Testing**                         | 1/5           | Sin pruebas de ningún tipo; imposible implementar en el estado actual |
| **Observabilidad**                  | 1/5           | Solo MsgBox de errores; sin logging, monitoreo ni trazabilidad        |

**Puntaje Total: 10/40 → Nivel de Madurez: MUY BAJO (25%)**

> [!WARNING]
> Un puntaje de madurez del 25% indica que el sistema se encuentra en **zona crítica de riesgo tecnológico**. La continuidad operacional está garantizada solo mientras el hardware y el sistema operativo Windows mantengan compatibilidad con los componentes COM/OCX de la era 2001-2003.

**Interpretación general:**
El sistema cumple su función operacional en el corto plazo, pero carece de los fundamentos arquitectónicos necesarios para garantizar seguridad, escalabilidad o mantenibilidad. Cualquier fallo en las dependencias COM/OCX o en el servidor MySQL tendría impacto total en la operación contable de la organización.

---

## 11. CONCLUSIONES

### Resumen Ejecutivo del Estado Arquitectónico

El módulo **MenuContabilidad de SISPRO** es un sistema de escritorio **Visual Basic 6** desarrollado aproximadamente entre 1998-2005, que actúa como hub de navegación para un ecosistema de ~40 aplicaciones EXE satélite que conforman el sistema contable corporativo. La base de datos es **MySQL** accedida mediante **ADO 2.6 / ODBC 3.51** con credenciales en texto plano, y los reportes se generan con **Crystal Reports 8**.

| Indicador                     | Estado                                                                                                                                            |
| ----------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Nivel de madurez**          | 🔴 **MUY BAJO** – 25% (10/40 puntos)                                                                                                              |
| **Riesgos críticos**          | Seguridad (credenciales expuestas, SQL Injection), obsolescencia tecnológica (VB6 sin soporte 18 años), ausencia total de pruebas y auditoría     |
| **Recomendación principal**   | Iniciar **migración planificada por fases** hacia arquitectura web/API moderna, comenzando con la mitigación inmediata de riesgos de seguridad    |
| **Prioridad de intervención** | 🔴 **URGENTE** – Los riesgos de seguridad deben abordarse en el corto plazo; la migración debe planificarse con presupuesto y cronograma formales |

---

## 12. ANEXOS

### Anexo A – Estructura del Proyecto MenuContabilidad

```
MenuContabilidad/
├── MenuContabilidad.vbp          # Proyecto VB6 (Tipo: EXE, Versión 1.0.0)
├── MenuContabilidad.vbw          # Workspace de Visual Basic 6
├── Menucon.INI                   # Configuración: servidor MySQL, rutas de aplicaciones
└── Formas/
    ├── Acceso.frm                # Formulario de Login (Rut + Empresa + Password, max 3 intentos)
    ├── Acceso.frx                # Recursos binarios del formulario Acceso
    ├── MenuContabilidad.frm      # Formulario principal (menú hub, lanza EXEs satélite)
    └── MenuContabilidad.frx      # Recursos binarios del formulario principal

Módulos compartidos (relativos):
└── ../Comunes/Modulos/
    ├── General.bas               # Variables globales (60+ Recordsets ADO), conexión MySQL, Sub Main
    ├── Validaciones.bas          # Funciones de validación compartidas
    ├── GeneraDNS.bas             # Generación de cadena de conexión DNS
    ├── ERR_ORACLE.bas            # Manejo de errores (referencia a Oracle legacy)
    └── DB_ORACLE.bas             # Módulo de conexión Oracle (no activo en este proyecto)
```

### Anexo B – Dependencias Registradas (VBP)

| Componente              | Archivo         | Versión | Función                                  |
| ----------------------- | --------------- | ------- | ---------------------------------------- |
| OLE Automation          | `stdole2.tlb`   | 2.0     | Automatización OLE                       |
| Microsoft DAO           | `dao360.dll`    | 5.0     | Acceso a bases de datos locales (legado) |
| Crystal Reports Viewer  | `crviewer.dll`  | 8.0     | Visualización de reportes                |
| Crystal Reports Runtime | `craxdrt.dll`   | 8.0     | Motor de reportes Crystal Reports        |
| ADO Multi-dimensional   | `msadomd28.tlb` | 2.8     | ADO OLAP                                 |
| ADO                     | `msado26.tlb`   | 2.6     | Acceso a datos principal                 |
| ADO Recordset           | `msador28.tlb`  | 2.8     | Recordsets ADO                           |
| ADO DDL/Security        | `msadox28.tlb`  | 2.8     | DDL y seguridad ADO                      |
| Common Controls         | `COMCTL32.OCX`  | 1.3     | Controles comunes Windows                |
| MS Comm                 | `MSCOMM32.OCX`  | 1.1     | Comunicaciones seriales                  |
| MS FlexGrid             | `MSFLXGRD.OCX`  | 1.0     | Grillas de datos                         |
| MS Masked Edit          | `MSMASK32.OCX`  | 1.1     | Campos con máscara                       |
| MS Common Controls 2    | `MSCOMCT2.OCX`  | 2.0     | Controles adicionales                    |
| Crystal Reports OCX     | `Crystl32.OCX`  | —       | Crystal Reports integrado                |
| Tab Control             | `TABCTL32.OCX`  | 1.1     | Pestañas de interfaz                     |
| FlexWiz                 | `FLEXWIZ.OCX`   | 1.0     | Control wizard                           |

### Anexo C – Cadena de Conexión MySQL

```ini
# MenuCon.INI
[SERVIDOR]
Nom_Servidor=localhost
usr=root
Pass=Sispro#05
Puerto=3306
NomDNS=Sispro_prn

[Ubicacion]
PATH_APLICACIONES=V:\Sispro\MenuConta\Aplicaciones\

[RUTA_R]
RUTA_REP=V:\Sispro\MenuConta\Reportes\

[BASE]
Nom_Base=bdsispro
```

**Cadena ADO generada (`General.bas`):**

```
driver={MySQL ODBC 3.51 Driver};Server=localhost;database=bdsispro;User=root;Password=Sispro#05;Option=3;
```

> [!CAUTION]
> La cadena de conexión incluye usuario `root` y contraseña en texto plano. Este archivo reside en una unidad de red compartida accesible por cualquier usuario del dominio.

### Anexo D – EXEs Satélite Identificados

| ID     | EXE                        | Descripción                                          |
| ------ | -------------------------- | ---------------------------------------------------- |
| M01-01 | `IngresarComprobante.exe`  | Ingreso de comprobantes contables                    |
| M01-02 | `ModificarComprobante.exe` | Modificación de comprobantes                         |
| M01-03 | `EliminarComprobante.exe`  | Eliminación de comprobantes                          |
| M01-04 | `ImprimirComprobante.exe`  | Impresión de comprobantes                            |
| M06-01 | `CentralizarCheques.exe`   | Centralización de cheques                            |
| M06-02 | `PrepararOtrosPagos.exe`   | Preparación de otros pagos                           |
| M06-03 | `ModificarOtrosPagos.exe`  | Modificación de otros pagos                          |
| M06-04 | `DesmarcarCheques.exe`     | Desmarcar cheques procesados                         |
| M06-05 | `PagoComisiones.exe`       | Pago de comisiones                                   |
| M07-01 | `Periodos.exe`             | Mantenimiento de períodos contables                  |
| M07-02 | `DefinirCuenta.exe`        | Definición de estructura del plan de cuentas         |
| M07-03 | `Usuarios.exe`             | Mantención de usuarios del sistema                   |
| M07-04 | `Apertura.exe`             | Generación de apertura del año contable              |
| M07-05 | `Comprobacion.exe`         | Comprobación de comprobantes descuadrados            |
| M07-06 | `DatosEmpresa.exe`         | Datos de la empresa                                  |
| M07-07 | `Foliador.exe`             | Gestión de folios                                    |
| M07-08 | `LiberarComprobantes.exe`  | Liberación de comprobantes                           |
| ...    | + ~23 más                  | (Menús M02, M03, M04, M05 no completamente listados) |

---

_Documento generado el 2026-02-25 mediante análisis estático del código fuente del proyecto `MenuContabilidad.vbp`._
