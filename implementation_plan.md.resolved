# Organización de Carpetas – Backend Monolítico (Middleware de Migración SISPRO)

## Contexto

El proyecto es un **middleware de migración** en Spring Boot (`com.antuan_midleware`) que lee datos del
legacy MySQL (SISPRO) y los transporta/transforma hacia el nuevo ERP PostgreSQL.  
La arquitectura es **monolítica en capas** con módulos funcionales internos.

Estado actual:
- `transitorio/` → Proyecto Spring Boot con modelos sin organizar (`CORE/`, `empresas/`)
- `bd_test/` → Scripts SQL de base de datos de prueba (5 archivos [.sql](file:///C:/Users/Ejecutivo2.ANTUAN/Documents/backend_transitorio/bd_test/bdsispro_test_part1.sql))
- `INFORMACION/` → Documentación de esquemas SISPRO
- `SKILLS/` → Skills técnicas del equipo

---

## Propuesta: Estructura de Carpetas

### Nivel raíz (`backend_transitorio/`)

```
backend_transitorio/
│
├── transitorio/                   # Proyecto Spring Boot (sin cambios externos)
│   └── src/main/java/com/antuan_midleware/
│       ├── TransitorioApplication.java   (existente)
│       │
│       ├── config/                       [NUEVA] Configuraciones Spring
│       │   ├── DataSourceLegacyConfig.java
│       │   └── DataSourceTargetConfig.java
│       │
│       ├── core/                         [RENOMBRAR desde CORE/]
│       │   ├── model/                    [NUEVA] Modelos JPA legacy
│       │   │   ├── encsolicosto/
│       │   │   ├── talsolicosto/
│       │   │   ├── detsolicosto/
│       │   │   └── accsolicosto/
│       │   ├── repository/               [NUEVA] Repositorios JPA
│       │   └── service/                  [NUEVA] Servicios de extracción
│       │
│       ├── empresas/                     (existente, solo reorganizar)
│       │   ├── model/                    [NUEVA] Modelos JPA
│       │   └── repository/               [NUEVA] Repositorios
│       │
│       ├── migration/                    [NUEVA] Orquestación de migración
│       │   ├── pipeline/                 Jobs / runners de migración
│       │   ├── transformer/              Lógica de transformación de datos
│       │   └── validator/                Validadores de integridad
│       │
│       ├── shared/                       [NUEVA] Utilidades transversales
│       │   ├── exception/                Excepciones personalizadas
│       │   ├── dto/                      DTOs de transferencia
│       │   └── util/                     Helpers / conversores
│       │
│       └── web/                          [NUEVA] API REST (controladores)
│           └── controller/
│
│   └── src/main/resources/
│       ├── application.properties        (existente)
│       ├── application-dev.properties    [NUEVA] Perfil desarrollo
│       └── db/migration/                 [NUEVA] Scripts Flyway (opcional)
│
├── bd_test/                       # Scripts SQL (sin cambios de estructura)
│   ├── bdsispro_test_setup.sql    (existente)
│   ├── bdsispro_test_part1.sql    (existente)
│   ├── bdsispro_test_part2.sql    (existente)
│   ├── bdsispro_test_part3.sql    (existente)
│   └── bdsispro_test_part4.sql    (existente)
│
├── INFORMACION/                   # Documentación (sin cambios)
│   ├── BD_SISPRO_INFO.md
│   ├── INGRESO_BD.md
│   ├── SISPRO_MENU_CONTA.md
│   └── SISPRO_MENU_COSTOS.md
│
├── SKILLS/                        # Skills técnicas (sin cambios)
│   ├── migracion_sispro.md
│   ├── arquitecto.md
│   ├── ingeniero_datos.md
│   └── sispro_analitic.md
│
└── docs/                          [NUEVA] Documentación técnica del proyecto
    ├── arquitectura.md
    └── guia_migracion.md
```

---

## Acciones Concretas

> [!IMPORTANT]
> **Solo se crean carpetas vacías**. No se mueve ni modifica ningún archivo de código existente.

### Dentro de `transitorio/src/main/java/com/antuan_midleware/`

| Carpeta | Acción | Propósito |
|---|---|---|
| `config/` | [NUEVA] | Configuraciones de DataSource dual (MySQL + H2/PG) |
| `core/model/` | [NUEVA] | Modelos JPA del legacy (mover contenido de `CORE/`) |
| `core/repository/` | [NUEVA] | Repositorios de lectura del legacy |
| `core/service/` | [NUEVA] | Servicios de extracción de datos |
| `empresas/model/` | [NUEVA] | Modelos de empresa existentes organizados |
| `empresas/repository/` | [NUEVA] | Repositorios de empresa |
| `migration/pipeline/` | [NUEVA] | Runners / jobs de migración |
| `migration/transformer/` | [NUEVA] | Transformación y mapeo de datos |
| `migration/validator/` | [NUEVA] | Validación de integridad post-migración |
| `shared/exception/` | [NUEVA] | Excepciones del dominio |
| `shared/dto/` | [NUEVA] | DTOs de transferencia entre capas |
| `shared/util/` | [NUEVA] | Utilidades y helpers |
| `web/controller/` | [NUEVA] | Controladores REST (triggers de migración, health) |

### Dentro de `transitorio/src/main/resources/`

| Carpeta/Archivo | Acción | Propósito |
|---|---|---|
| `application-dev.properties` | [NUEVA] | Perfil de desarrollo separado |
| `db/migration/` | [NUEVA] | Scripts Flyway (si se incorpora) |

### A nivel raíz de `backend_transitorio/`

| Carpeta | Acción | Propósito |
|---|---|---|
| `docs/` | [NUEVA] | Documentación técnica centralizada del proyecto |

---

## Verificación

Este plan **solo crea carpetas** (`.gitkeep` para preservarlas en git). No hay código que ejecutar.
La verificación es visual: confirmar que la estructura resultante en el explorador de archivos
coincide con el árbol documentado arriba.

> [!NOTE]
> Los archivos Java existentes en `CORE/` y `empresas/` **no se mueven** en esta etapa.
> La reorganización del código fuente será una tarea posterior y separada.
