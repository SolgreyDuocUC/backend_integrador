---
name: data-engineer-legacy-migration
description: "Skill de Ingeniería de Datos Senior para la deconstrucción, saneamiento y migración de la base de datos legacy bdsispro hacia un modelo relacional moderno y robusto."
tags:
  - data-engineering
  - database-migration
  - legacy-systems
  - etl
  - erp
scope: project
version: 1.0
---

# Skill: Senior Data Engineer - Estrategia Migración SISPRO

Actúo como un Ingeniero de Datos Senior experto en depuración y migración de sistemas transaccionales legacy (ERP). Mi prioridad es la preservación del historial, la corrección semántica y la inyección de integridad referencial.

Siguiendo las directrices del Arquitecto Cloud/Enterprise (`arquitecto.md`), este es el plan oficial estructurado:

## DIAGNÓSTICO
La base de datos origen (`bdsispro`, MySQL 110 tablas) presenta un severo déficit estructural para la operación de un ERP moderno:
- **Ausencia de Integridad Referencial**: 0 FOREIGN KEYs declaradas. Toda relación es implícita a nivel de aplicación, garantizando la existencia de registros huérfanos (violaciones de integridad).
- **Tipado Frágil**: Identificadores de negocio críticos (`Nro_OCompra`, `Nro_Solicitud`, `CUENTA` contable) están tipados como `double`, con grave riesgo de pérdida de precisión e ineficiencias en los índices.
- **Antipatrones de Normalización (1NF/2NF violadas)**:
    - Pivotización dura: 6 tablas de tallas dependientes de columnas fijas (`Talla01` a `Talla30`).
    - Matriz de seguridad flat: Tabla `accesos` usa más de 200 columnas `bit` (`M01_Op01`...).
- **Seguridad Comprometida**: Credenciales de acceso almacenadas en texto plano en la tabla `usuarios`.
- **Diseño Multi-tenant Obsoleto**: Claves primarias compuestas repetitivas y poco eficientes dependientes del campo string `Empresa varchar(3)`.
- **Tablas de sesión poco confiables**: Uso crónico de almacenamiento persistente (`tmp_*`) para estados temporales/multiusuario que carecen del debido aislamiento.

## ARQUITECTURA PROPUESTA
- **Arquitectura de Migración Estranguladora**:
    - `Fuente Legacy (bdsispro)` → `ETL Pipeline (Extraer)` → `Staging Area (Validar y Limpiar)` → `Transformar/Normalizar` → `Nuevo ERP DB`.
- **Estructura Objetivo**:
    - Bases de datos multi-tenant lógicas estructuradas por `tenant_id` moderno o esquemas aislados.
    - Seguridad basada en roles (RBAC).
    - Manejo de parámetros de dimensionalidad variable (`doc_linea_talla` relacional normalizada).
    - Inyección de Claves Primarias Surrogate formales (tipo `BIGINT IDENTITY` o `UUID`). Las PKs de legacy se mantendrán estrictamente como un atributo de lectura `legacy_id` para trazabilidad.

## FASES DE MIGRACIÓN
1. **Extracción Raw (As-Is)**: Volcado inmutable hacia la base de Staging sin aplicar filtros, directo de MySQL de producción. Se fuerza el uso de Charset `utf8mb4` para preservar los encodings de campos `longtext`.
2. **Profiling y Quality Assurance (QA) de Datos**: Detección computacional cruzada de huérfanos (Ej. test `det_solicosto` contra `enc_solicosto`). Detección de rangos inconsistentes y comprobantes duplicados pre-normalización.
3. **Limpieza y Enriquecimiento (Scrubbing)**:
    - Aislamiento o re-asignación de registros huérfanos a "entidades basurero histórico" para NO corromper nunca la sumatoria contable.
    - Destrucción de contraseñas legacy y setup de banderas de "Forzar Cambio Inmediato Primer Login".
4. **Transformación (Unpivot y Normalización)**:
    - Desdoblamiento de matrices en runtime (Tallas y Permisos), transformándolos a estructuras normalizadas y relacionales dentro de Staging.
5. **Generación de Constraints e Índices**: Aplicación de todas las FOREIGN KEYS y Constraints `NOT NULL`/`UNIQUE` reales según la lógica de negocio, testeando las reglas de inserción limpia.
6. **Inserción y Cut-Over**: Carga final en bulk hacia la base de datos moderna con monitoreo completo y cuádruple verificación técnica.

## RIESGOS
- **Discontinuidad Contable e Histórica**: Una falla parcial de trasvase en `moviconta` (204.889 registros) o la pérdida en decimales podría desbalancear el libro mayor o los estados contables.
- **Pérdida por Tipos de Datos (Cast `double`)**: En migraciones que manejen floats indiscriminados, evaluaciones absolutas o transformaciones pueden introducir basura decimal fraccionada si no se pasan en primer lugar a `DECIMAL(18,4)` u omiten rutinas de truncado apropiadas.
- **Truncamientos de Textos Sensibles**: Posible pérdida de fichas léxicas o `longtext` (`Especificaciones`, `Glosa`) atribuibles a colisiones del tamaño de buffer.
- **Colisiones Post-Limpieza**: Limpiar un diseño donde abundan duplicidades fantasmas podría resultar en violaciones de primary keys al inyectar al nuevo modelo.

## VALIDACIONES
- **Hash de Consistencia Financiera**: Total sumatoria acumulada `DEBE` == Total sumatoria `HABER` agregado por Empresa y Periodo Contable en `moviconta` = Reemplazo verificado.
- **Integridad Física/Inventario transaccional**: Stock saliente vs Stock entrante debe mantener paridad comprobando `invvalorizado` maestro contra las agrupaciones log normalizadas de `documento_inv`.
- **Trazabilidad de Huella Dual**: El nuevo sistema debe permitir la exploración sin roce usando la clave primaria antigua (`Nro_OCompra`, `Nro_Solicitud`) como identificador histórico natural.

## DECISIONES TÉCNICAS
- **Aislamiento Pipeline**: Ningún componente del ERP nuevo tocará en ningún caso la matriz antigua `bdsispro`. La descompresión e integridad ocurrirá enteramente off-site (Staging Area).
- **Descarte Consciente**: Las tablas temporales (`tmp_*`) quedan fuera del proceso de portabilidad.
- **Algoritmo de Unpivot Duro**: Las 6 tablas de características/tallas serán depuradas transaccionalmente generando un solo payload verticalizado dinámico.
    - *Pipeline mock*: `INSERT INTO tallaje (doc_id, linea, id_talla, cant) SELECT Nro_OCompra, Linea, 'Talla01', Talla01 FROM tal_ocompra WHERE Talla01 > 0;` (Aplicable en las 30 nubes concurrentes).
- **Tipado Estriccional**: Todo valor de moneda se almacenará obligatoriamente como `DECIMAL` / `NUMERIC` formal y nunca como Floating point genérico. Identidad ID migra a `BIGINT`.

## RESUMEN EJECUTIVO
El plan aborda la deconstrucción y saneamiento masivo de `bdsispro`. Se detecta una grave deficiencia transaccional (anti-patrones, ausencia de constraints formales e identificadores inestables). La solución aplica un modelo de **limpieza defensiva paralela**, forzando todos los datos legacy por un filtro validador de Staging para un-pivot de tablas rotas, reconstrucción postuma de claves foráneas reales y preservación total blindada del historial contable (`moviconta`), asegurando compatibilidad analítica garantizada bajo una arquitectura RBAC del nuevo ambiente.