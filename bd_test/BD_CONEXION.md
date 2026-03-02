# Configuración de Base de Datos — Entorno Desarrollo

Este documento explica cómo configurar y conectar el backend al entorno local de base de datos usando MySQL (XAMPP).

---

## 1. Requisitos

Antes de iniciar debes tener instalado:

- XAMPP
- MySQL corriendo
- Java JDK 21+
- Maven o Gradle
- IDE (IntelliJ recomendado)

---

## 2. Iniciar servidor MySQL (XAMPP)

1. Abrir XAMPP Control Panel
2. Iniciar módulo:


Debe quedar en verde.

---

## 3. Crear base de datos local

Abrir consola MySQL o cliente visual (Workbench o phpMyAdmin) y ejecutar:

```sql
CREATE DATABASE antuan_test;
```

Verificar:
```sql
SHOW DATABASES;
```

Debe aparecer:

```sql
antuan_test
```

CREAR USUARIO SI NO EXISTE
```sql
CREATE USER 'antuan_app'@'localhost' IDENTIFIED BY 'Antuan123*';
GRANT ALL PRIVILEGES ON antuan_test.* TO 'antuan_app'@'localhost';
FLUSH PRIVILEGES;
```

---

Este MD está listo para:
- repositorio
- onboarding de desarrolladores
- documentación interna
- auditorías técnicas

---

### Siguiente paso recomendado (nivel empresa real)
Ahora que tienes conexión y tablas automáticas, el siguiente salto profesional es:

> congelar estructura de tablas y pasar de `ddl-auto=update` → `validate`

para evitar cambios accidentales en producción.

---

Si quieres, te preparo el **Paso 10 documentado estilo empresa**:  
migraciones controladas con Flyway (como usan sistemas empresariales reales).