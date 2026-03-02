# INGRESO A LA BASE DE DATOS — Sistema Sispro

> **Fuente de parametros:** Archivo `MenuCostos.ini` — Modulo MenuCosto  
> **Motor de base de datos:** MySQL  
> **Fecha de referencia:** 2026-02-26

---

## Parametros de Conexion

| Parametro | Valor |
|---|---|
| Host / IP del servidor | `192.168.2.3` |
| Puerto | `3306` |
| Usuario | `sispro` |
| Contrasena | `sispro` |
| Base de datos | `bdsispro` |
| Nombre DNS (impresion) | `Sispro_prn` |

> **Advertencia de seguridad:** Las credenciales estan almacenadas en texto plano en `MenuCostos.ini`. Se recomienda no compartir este documento fuera del equipo tecnico autorizado.

---

## Metodo 1 — MySQL Command Line (CMD / Terminal)

Desde cualquier equipo de la red local con el cliente MySQL instalado, ejecutar:

```bash
mysql -h 192.168.2.3 -P 3306 -u sispro -p
```

Cuando el sistema solicite la contrasena, ingresar:

```
sispro
```

Para ingresar directamente a la base de datos del sistema:

```bash
mysql -h 192.168.2.3 -P 3306 -u sispro -p bdsispro
```

Una vez dentro, verificar que la base activa es la correcta:

```sql
SELECT DATABASE();
```

---

## Metodo 2 — MySQL Workbench (Interfaz Grafica)

1. Abrir **MySQL Workbench**.
2. Hacer clic en **"+"** junto a "MySQL Connections".
3. Completar los campos:

   | Campo | Valor |
   |---|---|
   | Connection Name | `Sispro Produccion` |
   | Hostname | `192.168.2.3` |
   | Port | `3306` |
   | Username | `sispro` |
   | Password | Clic en "Store in Vault..." → ingresar `sispro` |
   | Default Schema | `bdsispro` |

4. Hacer clic en **"Test Connection"** para verificar.
5. Confirmar con **"OK"** y luego doble clic sobre la conexion creada para acceder.

---

## Metodo 3 — DBeaver (Cliente Universal)

1. Abrir DBeaver y seleccionar **"Nueva Conexion"**.
2. Elegir **MySQL** como tipo de base de datos.
3. Completar:

   | Campo | Valor |
   |---|---|
   | Server Host | `192.168.2.3` |
   | Port | `3306` |
   | Database | `bdsispro` |
   | Username | `sispro` |
   | Password | `sispro` |

4. Clic en **"Test Connection"** → **"Finalizar"**.

---

## Verificacion Post-Ingreso

Una vez conectado, ejecutar las siguientes consultas para validar el acceso:

```sql
-- Verificar base de datos activa
SELECT DATABASE();

-- Listar tablas disponibles
SHOW TABLES;

-- Verificar version del motor
SELECT VERSION();

-- Verificar usuario activo
SELECT CURRENT_USER();
```

---

## Requisitos de Red

- El equipo desde el que se accede debe estar en la **red local** `192.168.2.x`.
- El puerto `3306` debe estar **habilitado** en el firewall del servidor `192.168.2.3`.
- La unidad de red `V:\Sispro\` debe estar mapeada si se requiere acceso a los archivos del modulo.

---

## Notas Tecnicas (del Analisis Arquitectonico)

- La base de datos `bdsispro` es accedida **directamente** por cada ejecutable del modulo MenuCosto sin capa intermediaria.
- No existe conexion via API ni servicio REST; el acceso es **two-tier** (cliente-servidor directo).
- El usuario `sispro` es el unico usuario de aplicacion identificado en la configuracion del modulo.

---

*Documento basado en el analisis del modulo MenuCosto — Sispro. Parametros extraidos de `V:\Sispro\MenuCosto\Aplicaciones\MenuCostos.ini`.*
