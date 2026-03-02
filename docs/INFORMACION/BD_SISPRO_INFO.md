# BASE DE DATOS SISPRO — Documentacion Tecnica Completa

## Base: `bdsispro` | Motor: MySQL | Servidor: `192.168.2.3:3306`

> **Fecha de extraccion:** 2026-02-26
> **Metodo:** Conexion directa via `mysql-connector-python` al servidor de produccion
> **Usuario de consulta:** `sispro`
> **Total de tablas:** 110
> **Vistas declaradas:** 0
> **Claves foraneas declaradas (FOREIGN KEY):** 0 (integridad referencial implicita, no declarada)

---

## Tabla de Contenidos

1. [Parametros de Conexion](#1-parametros-de-conexion)
2. [Resumen del Esquema](#2-resumen-del-esquema)
3. [Modelo Entidad-Relacion Logico](#3-modelo-entidad-relacion-logico)
4. [Descripcion Detallada de Tablas por Dominio](#4-descripcion-detallada-de-tablas-por-dominio)
5. [Observaciones Tecnicas para la Migracion](#5-observaciones-tecnicas-para-la-migracion)
6. [Referencia Completa de Columnas por Tabla](#6-referencia-completa-de-columnas-por-tabla)

---

## 1. Parametros de Conexion


| Parametro           | Valor         |
| ------------------- | ------------- |
| Host                | `192.168.2.3` |
| Puerto              | `3306`        |
| Base de datos       | `bdsispro`    |
| Usuario             | `sispro`      |
| Contrasena          | `sispro`      |
| Motor               | MySQL         |
| Charset recomendado | `utf8mb4`     |

> **Advertencia:** Las credenciales estan en texto plano en `MenuCostos.ini`. No distribuir este documento fuera del equipo tecnico.

---

## 2. Resumen del Esquema


| Categoria                                         | Total   |
| ------------------------------------------------- | ------- |
| Tablas de datos maestros                          | 18      |
| Tablas de encabezado de documentos (enc_*)        | 12      |
| Tablas de detalle de documentos (det_*)           | 12      |
| Tablas de tallas (tal_*)                          | 6       |
| Tablas de pagos                                   | 5       |
| Tablas de descuentos/recargos (desrec_*)          | 5       |
| Tablas de costos (solicosto, acc_solicosto, etc.) | 8       |
| Tablas de ordenes operativas                      | 8       |
| Tablas contables                                  | 8       |
| Tablas temporales (tmp_*)                         | 6       |
| Tablas de configuracion y parametros              | 7       |
| Tablas de log y alarmas                           | 3       |
| Tablas de inventario                              | 4       |
| Otras                                             | 8       |
| **TOTAL**                                         | **110** |

### Tablas con Mayor Volumen de Datos


| Tabla            | Filas   |
| ---------------- | ------- |
| `moviconta`      | 204,889 |
| `det_invfisico`  | 389,018 |
| `pla_solicosto`  | 136,046 |
| `movicompra`     | 55,076  |
| `det_solicompra` | 53,988  |
| `det_solicosto`  | 45,189  |
| `documento_inv`  | 88,212  |
| `det_ocompra`    | 34,789  |
| `det_otrabajo`   | 32,361  |
| `acc_solicosto`  | 71,776  |
| `enc_solicosto`  | 19,960  |
| `libvctos`       | 21,966  |
| `libcheques`     | 7,685   |
| `enc_ocompra`    | 12,291  |
| `pago_ocompra`   | 12,550  |

---

## 3. Modelo Entidad-Relacion Logico

La base de datos no declara FOREIGN KEYs formales. Las relaciones son implicitas y se infieren por nomenclatura, tipos de dato compartidos y logica de negocio observada.

### 3.1 Diagrama MER — Dominio de Costos (Core)

```
empresas (CODIGO)
     |
     +---> configuracion_sistema (Empresa, Sistema)
     |
     +---> usuarios (EMPRESA, RUT_USUARIO, Sistema)
     |
     +---> accesos (Empresa, Usuario, Sistema)
     |         [Menu_01..10, M01_Op01..20 x 10 menus = 200 permisos bit por usuario]
     |
     +---> enc_solicosto (Empresa, Nro_Solicitud)  [19,960 registros]
               |
               +---> det_solicosto (Empresa, Nro_Solicitud, Linea)  [45,189 reg] [TELAS]
               |
               +---> acc_solicosto (Empresa, Nro_Solicitud, Linea)  [71,776 reg] [ACCESORIOS]
               |
               +---> pla_solicosto (Empresa, NroCosto, Plantilla, Linea) [136,046 reg]
               |
               +---> tal_solicosto (Empresa, Nro_Solicitud, Linea)  [10,025 reg] [TALLAS]
               |
               +---> tal_solicostores (Empresa, Nro_Solicitud, Linea) [5,121 reg] [TALLAS REALES]
               |
               +---> log_solicosto (sin PK formal)  [19,354 reg] [AUDITORIA]
               |
               +---> com_solicosto (Empresa, Numero_OP, Linea) [22 reg]
               |
               +---> enc_otrabajo (Empresa, Nro_OTrabajo)  [vinculado por Nro_Solicitud]
               |
               +---> enc_oservicios (Empresa, Nro_OServicio)  [vinculado por Nro_OP]
```

### 3.2 Diagrama MER — Dominio de Ordenes de Compra

```
proveedores (EMPRESA, RUT_PROVEEDOR)
clientes (EMPRESA, RUT_CLIENTE)
     |
     +---> enc_ocompra (EMPRESA, Nro_OCompra)  [12,291 reg]
     |          |
     |          +---> det_ocompra (Empresa, Nro_OCompra, Item)  [34,789 reg]
     |          |         [con campos: Modelo, Tela, Composicion, Genero, Color, Aplicacion]
     |          |
     |          +---> tal_ocompra (Empresa, Nro_OCompra, Linea)  [5,661 reg] [TALLAS x30]
     |          |
     |          +---> desrec_ocompra (Empresa, Nro_OCompra, Item)  [43 reg]
     |          |
     |          +---> pago_ocompra (Empresa, Nro_OCompra, Codigo, Fecha) [12,550 reg]
     |          |
     |          +---> ocrecuperada (Empresa, NroOC, Linea)  [6,120 reg]
     |
     +---> enc_occliente (Empresa, Nro_OCCli)  [1,386 reg]
               |
               +---> det_occliente (Empresa, Nro_OCCli, Linea)  [7,834 reg]
               |
               +---> tal_occliente (Empresa, Nro_OCCli, Linea, Tipo)  [7,882 reg]
```

### 3.3 Diagrama MER — Dominio de Solicitudes de Compra

```
enc_solicompra (Empresa, Nro_Sol)  [8,180 reg]
     |
     +---> det_solicompra (Empresa, Nro_Sol, Linea)  [53,988 reg]
     |         [con campos: Modelo, Tela, Composicion, Genero, Color, Aplicacion, Nro_OCompra]
     |
     +---> tal_solicompra (Empresa, Nro_Sol, Linea)  [7,833 reg]
```

### 3.4 Diagrama MER — Dominio de Ordenes de Trabajo y Servicios

```
enc_otrabajo (Empresa, Nro_OTrabajo)  [4,248 reg]
     |
     +---> det_otrabajo (Empresa, Nro_OTrabajo, Linea)  [32,361 reg]
     |
     +---> tal_otrabajo (Empresa, Nro_OT, Linea)  [1,386 reg]

enc_oservicios (Empresa, Nro_OServicio)  [1,078 reg]
     |
     +---> mat_oservicios (Empresa, Nro_OServicio, Linea)  [6,047 reg] [MATERIALES]
     |
     +---> req_oservicios (Empresa, Nro_OServicio, Linea)  [4,871 reg] [REQUERIMIENTOS]
     |
     +---> ent_oservicios (Empresa, Nro_OServicio, Linea)  [131 reg] [ENTREGAS]

enc_ocbordados (Empresa, Nro_Orden)  [4 reg]
     +---> det_ocbordados (Empresa, Nro_Orden, Linea)  [2 reg]
```

### 3.5 Diagrama MER — Dominio de Documentos (Ventas/Guias)

```
clientes (EMPRESA, RUT_CLIENTE)
     |
     +---> enc_documento (EMPRESA, Tipo_Documento, Folio_Inicial)  [9,781 reg]
     |          |
     |          +---> det_documento (EMPRESA, Tipo_Documento, Folio_Inicial, Item) [43,712 reg]
     |          +---> ser_documento (EMPRESA, Tipo_Documento, Folio_Inicial, Item) [4,614 reg]
     |          +---> desrec_documento (EMPRESA, Tipo_Documento, Folio_Inicial, Item) [672 reg]
     |          +---> pago_documento (EMPRESA, Tipo_Documento, Folio_Inicial, Item) [7,285 reg]
     |
     +---> enc_guias (EMPRESA, Nro_Guia)  [1 reg]
               +---> det_guias (Empresa, Nro_Guia, Item)  [21 reg]
               +---> desrec_guias (vacía)
               +---> pago_guias (Empresa, Nro_Guia, Codigo)  [2 reg]
```

### 3.6 Diagrama MER — Dominio Contable

```
cuentas (EMPRESA, CUENTA)
     |
     +---> moviconta (EMPRESA, NUMCOMP, TIPOCOMP, LINEACOM, ANN_COMP)  [204,889 reg]
     |
     +---> corrcomprobante (EMPRESA, NUMCOMP, TIPOCOMP, LINEACOM, ANN_COMP)  [19 reg]
     |
     +---> foliocomprobantes (Empresa, NumComp, TipoComp, Ann_Comp)  [1,476 reg]
     |
     +---> movicompra (EMPRESA, PERIODO, LOCAL, TIPODOCUMENTO, NRODOCUMENTO)  [55,076 reg]
     |
     +---> libcheques (Empresa, Ano_Mov, Numero, Tip_Comp, Linea)  [7,685 reg]
     |
     +---> libvctos (Empresa, Local, TipoDocumento, NroDocumento, RutProveedor, Fecha) [21,966 reg]

ctasctes (EMPRESA, RUT_CTACTE)
cencosto (EMPRESA, CODIGO) - Centros de costos [575 reg]
```

### 3.7 Diagrama MER — Datos Maestros

```
articulos (EMPRESA, Codigo_Articulo)  [15,649 reg]
     +---> medidas_alternativas (Empresa, Codigo_Articulo, Unidad_medida)
     +---> paramcomisiones (Empresa, Producto)  [10,302 reg]
     +---> invvalorizado [4,893 reg] - snapshot de valorizado

parametros (EMPRESA, TIPO, CODIGO)  [896 reg]
     -- Almacena catalogos de tipo-codigo: Modelos, Telas, Generos, Colores,
     -- Composiciones, Aplicaciones, Condiciones de pago, etc.

plantillas (Empresa, Plantilla, Correlativo)  [129 reg]
preciofamilia (Empresa, Familia)  [41 reg]
relacioncodigos (Empresa, Cliente, CodigoCliente)  [139 reg]
receptores (Empresa, Receptor)  [7 reg]
```

---

## 4. Descripcion Detallada de Tablas por Dominio

### 4.1 Tablas de Configuracion y Sistema


| Tabla                   | Filas | Descripcion                                                                                                                                                                                  |
| ----------------------- | ----- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `empresas`              | 5     | Registro de empresas del grupo. PK: RUT_EMPRESA. Contiene razon social, giro, representante legal, datos de contacto.                                                                        |
| `configuracion_sistema` | 7     | Parametros globales por empresa y sistema. Incluye porcentaje IVA, control de inventario, parametros de costos fijos (flete, hilos, etiqueta, mano de obra), fechas de temporadas.           |
| `configuracuenta`       | 3     | Configuracion del plan de cuentas contable: largosy nombres de niveles (hasta 5 niveles jerarquicos).                                                                                        |
| `parametros`            | 896   | Tabla catastro generica (EMPRESA, TIPO, CODIGO). Almacena todos los catalogos del sistema: modelos, telas, generos, colores, composiciones, aplicaciones, condiciones de pago, monedas, etc. |
| `periodos`              | 47    | Control de periodos contables abiertos/cerrados por empresa y sistema, con flags por mes (MES01..MES12).                                                                                     |
| `correlativos`          | 4     | Correlativos de documentos por tipo.                                                                                                                                                         |
| `folios`                | 34    | Control de folios de documentos por equipo.                                                                                                                                                  |
| `locales`               | 5     | Locales/sucursales de la empresa.                                                                                                                                                            |
| `secuencia`             | 612   | Rangos de secuencias de comprobantes contables.                                                                                                                                              |

### 4.2 Tablas de Datos Maestros


| Tabla                  | Filas  | Descripcion                                                                                                                                                                                                              |
| ---------------------- | ------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| `articulos`            | 15,649 | Catalogo de articulos/productos. PK: (EMPRESA, Codigo_Articulo). Contiene descripcion, unidad de medida, precios, costos, stock minimo/maximo, flags de control (lotes, seriales, compras, inventario), codigo de barra. |
| `clientes`             | 9,352  | Maestro de clientes. PK: (EMPRESA, RUT_CLIENTE). Datos comerciales, credito, vendedor, condicion de pago.                                                                                                                |
| `proveedores`          | 2,008  | Maestro de proveedores. PK: (EMPRESA, RUT_PROVEEDOR). Estructura identica a clientes.                                                                                                                                    |
| `ctasctes`             | 17,822 | Cuentas corrientes (terceros). PK: (EMPRESA, RUT_CTACTE). Compartido entre clientes y proveedores para la contabilidad.                                                                                                  |
| `proveebordado`        | 25     | Proveedores especializados en bordado. Tabla independiente con datos bancarios.                                                                                                                                          |
| `gastos`               | 444    | Catalogo de tipos de gastos.                                                                                                                                                                                             |
| `bancos`               | 14     | Lista de bancos.                                                                                                                                                                                                         |
| `activos`              | 28     | Activos fijos (catalogo simple).                                                                                                                                                                                         |
| `cencosto`             | 575    | Centros de costos. PK: (EMPRESA, CODIGO).                                                                                                                                                                                |
| `itemgasto`            | 633    | Items de gasto para contabilidad analitica.                                                                                                                                                                              |
| `plantillas`           | 129    | Plantillas de especificacion de productos (para solicitudes de costo).                                                                                                                                                   |
| `preciofamilia`        | 41     | Precios y costos por familia de producto.                                                                                                                                                                                |
| `relacioncodigos`      | 139    | Tabla de cruce entre codigos internos y codigos del cliente. Util para OC de clientes.                                                                                                                                   |
| `receptores`           | 7      | Receptores de alertas (correo electronico + equipo).                                                                                                                                                                     |
| `medidas_alternativas` | 0      | Unidades de medida alternativas por articulo (sin datos actualmente).                                                                                                                                                    |
| `documentovtas`        | 29     | Tipos de documentos de venta (facturas, boletas, notas, guias, etc.) con configuracion de impuestos y formato.                                                                                                           |
| `docucompra`           | 43     | Tipos de documentos de compra con configuracion contable e IVA.                                                                                                                                                          |

### 4.3 Tablas de Solicitudes de Costo (Dominio Central)

El ciclo de costeo es el proceso mas voluminoso del sistema.


| Tabla              | Filas   | Descripcion                                                                                                                                                                                                                                                                                                                                                                                      |
| ------------------ | ------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| `enc_solicosto`    | 19,960  | Encabezado de solicitud de costo. PK: (Empresa, Nro_Solicitud). Contiene: cliente, vendedor, estado, articulo a producir, cantidad, precio, parametros de costo (telas, hilos, etiquetas, flete, mano de obra sin/con gratificacion, bordado, logotipo, estampado), fechas de ciclo (solicitud, costo, autorizacion, OP, entrega, cierre), campos Real/Proyectado, especificaciones en longtext. |
| `det_solicosto`    | 45,189  | Detalle de telas de la solicitud. PK: (Empresa, Nro_Solicitud, Linea). Campos: codigo de tela, proveedor, precio unitario, consumo, unidad de medida, costo, valores reales vs proyectados, ancho, costo por unidad. Flag ReqOT.                                                                                                                                                                 |
| `acc_solicosto`    | 71,776  | Detalle de accesorios de la solicitud. PK: (Empresa, Nro_Solicitud, Linea). Campos: accesorio, proveedor, precio, consumo, unidad, costo, valores reales. Codigo interno del accesorio.                                                                                                                                                                                                          |
| `pla_solicosto`    | 136,046 | Plantillas de especificacion asociadas a cada solicitud. PK: (Empresa, NroCosto, Plantilla, Linea). Nombre y descripcion longtext.                                                                                                                                                                                                                                                               |
| `tal_solicosto`    | 10,025  | Distribucion de tallas (solicitud). PK: (Empresa, Nro_Solicitud, Linea). Talla (varchar) + Cantidad + CantOServ.                                                                                                                                                                                                                                                                                 |
| `tal_solicostores` | 5,121   | Distribucion de tallas reales (resultado). Misma estructura que tal_solicosto.                                                                                                                                                                                                                                                                                                                   |
| `log_solicosto`    | 19,354  | Log de cambios en solicitudes de costo (sin PK formal). Registra tipo de cambio, cantidad, precio, proveedor, ubicacion, color, tamano.                                                                                                                                                                                                                                                          |
| `com_solicosto`    | 22      | Compras asociadas a solicitudes de costo, vinculadas a ordenes de produccion.                                                                                                                                                                                                                                                                                                                    |

### 4.4 Tablas de Ordenes de Compra a Proveedor


| Tabla            | Filas  | Descripcion                                                                                                                                                                                                                                                                                                                                                    |
| ---------------- | ------ | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `enc_ocompra`    | 12,291 | Encabezado OC a proveedor. PK: (EMPRESA, Nro_OCompra). Campos: fecha, periodo, RUT proveedor, centro costo, comprador, local, moneda, paridad, condicion pago, fecha entrega, totales (SubTotal, Descuentos, Recargos, Afecto, Exento, IVA, Total), comprobante contable, porcentaje cumplimiento, comentarios longtext.                                       |
| `det_ocompra`    | 34,789 | Detalle de lineas OC proveedor. PK: (Empresa, Nro_OCompra, Item). Campos: producto, cantidad, precio unitario, subtotal, descuentos, total, fecha entrega, cantidad entregada, glosa, tipo, recargos, totales por linea, comprobante, marca centralizada, cumplimiento, comentario, atributos textiles (Modelo, Tela, Composicion, Genero, Color, Aplicacion). |
| `tal_ocompra`    | 5,661  | Distribucion de tallas de OC proveedor. PK: (Empresa, Nro_OCompra, Linea). 30 columnas Talla01..Talla30 de tipo int. NroSCOP (vincculo con solicitud de compra).                                                                                                                                                                                               |
| `desrec_ocompra` | 43     | Descuentos/recargos en OC. PK: (Empresa, Nro_OCompra, Item).                                                                                                                                                                                                                                                                                                   |
| `pago_ocompra`   | 12,550 | Condiciones de pago de OC. PK: (Empresa, Nro_OCompra, Codigo). Fecha, vencimiento, valor.                                                                                                                                                                                                                                                                      |
| `ocrecuperada`   | 6,120  | OC recuperadas de sistemas externos (ej. cliente envia OC por otro canal). PK: (Empresa, NroOC string, Linea).                                                                                                                                                                                                                                                 |

### 4.5 Tablas de Ordenes de Compra al Cliente (OC Cliente)


| Tabla           | Filas | Descripcion                                                                                                                                                                                                                                                |
| --------------- | ----- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `enc_occliente` | 1,386 | Encabezado OC cliente. PK: (Empresa, Nro_OCCli). Cliente, vendedor, estado, OC del cliente, fecha entrega, centro costo, especificaciones longtext, porcentaje cumplimiento.                                                                               |
| `det_occliente` | 7,834 | Detalle lineas OC cliente. PK: (Empresa, Nro_OCCli, Linea). Producto, cantidad, logo, Nro_OPSC (vinculo solicitud de costo), Linea_OPSC, atributos textiles, precio unitario, total linea, cantidad facturada, flag ReqOT, especificacion lineal longtext. |
| `tal_occliente` | 7,882 | Tallas de OC cliente. PK: (Empresa, Nro_OCCli, Linea, Tipo). 30 columnas Talla01..Talla30, NroSCOP, Estado, PrecCompra.                                                                                                                                    |

### 4.6 Tablas de Solicitudes de Compra (Flujo de Abastecimiento)


| Tabla            | Filas  | Descripcion                                                                                                                                                                                                                 |
| ---------------- | ------ | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `enc_solicompra` | 8,180  | Encabezado solicitud de compra. PK: (Empresa, Nro_Sol). Cliente, vendedor, estado, OC cliente, fecha entrega, centro costo, especificaciones.                                                                               |
| `det_solicompra` | 53,988 | Detalle solicitud de compra. PK: (Empresa, Nro_Sol, Linea). Producto, proveedor, cantidades (solicitada/bodega/comprada), Nro_OCompra (vinculo), logo, atributos textiles, precio unitario, especificacion lineal longtext. |
| `tal_solicompra` | 7,833  | Tallas de solicitud de compra. PK: (Empresa, Nro_Sol, Linea). 30 columnas Talla01..Talla30, PrecCompra.                                                                                                                     |

### 4.7 Tablas de Ordenes de Trabajo


| Tabla          | Filas  | Descripcion                                                                                                                                                                            |
| -------------- | ------ | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `enc_otrabajo` | 4,248  | Encabezado OT. PK: (Empresa, Nro_OTrabajo). Cliente, vendedor, estado, OC cliente, Nro_Solicitud (vinculo solicitud costo), fecha entrega, centro costo, especificaciones, referencia. |
| `det_otrabajo` | 32,361 | Detalle lineas OT. PK: (Empresa, Nro_OTrabajo, Linea). Producto, proveedor, cantidades (solicitada/bodega/comprada), atributos textiles, logo, flag ReqOT.                             |
| `tal_otrabajo` | 1,386  | Tallas de OT. PK: (Empresa, Nro_OT, Linea). 30 columnas Talla01..Talla30.                                                                                                              |

### 4.8 Tablas de Ordenes de Servicio (Tercerizado)


| Tabla            | Filas | Descripcion                                                                                                                                                                                                                                |
| ---------------- | ----- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| `enc_oservicios` | 1,078 | Encabezado OS. PK: (Empresa, Nro_OServicio). RUT taller, Nro_OP (vinculo OP), tipo doc, fecha entrega, valor, observaciones longtext, datos de contacto del taller, cortes/prendas, tipo origen, cliente, prenda, porcentaje cumplimiento. |
| `mat_oservicios` | 6,047 | Materiales entregados al taller. PK: (Empresa, Nro_OServicio, Linea). Cantidad, medida, descripcion, referencias cruzadas.                                                                                                                 |
| `req_oservicios` | 4,871 | Requerimientos de la OS. PK: (Empresa, Nro_OServicio, Linea). Cantidad, descripcion longtext, talla, vinculos.                                                                                                                             |
| `ent_oservicios` | 131   | Entregas recibidas de la OS. PK: (Empresa, Nro_OServicio, Linea).                                                                                                                                                                          |

### 4.9 Tablas de Ordenes de Bordado


| Tabla            | Filas | Descripcion                                                                                                                 |
| ---------------- | ----- | --------------------------------------------------------------------------------------------------------------------------- |
| `enc_ocbordados` | 4     | Encabezado OC bordado. Hasta 20 descripciones con anchos, muestra, observaciones longtext, imagenes, especificacion, ficha. |
| `det_ocbordados` | 2     | Detalle bordado. Descripcion, cantidad, precio, total, tipo, numero.                                                        |

### 4.10 Tablas de Documentos de Venta/Facturacion


| Tabla              | Filas  | Descripcion                                                                                                                                                     |
| ------------------ | ------ | --------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `enc_documento`    | 9,781  | Encabezado de facturas, boletas, notas de debito/credito. Toda la informacion del documento tributario. Vinculo opcional a solicitudes (Sol_Compra, Sol_Costo). |
| `det_documento`    | 43,712 | Lineas de detalle del documento. Producto, cantidades, precios, descuentos, glosa. Trazabilidad de origen (DocOrigen, NroOrigen, LinOrigen).                    |
| `ser_documento`    | 4,614  | Lineas de servicio del documento (diferente a productos fisicos).                                                                                               |
| `desrec_documento` | 672    | Descuentos y recargos del documento.                                                                                                                            |
| `pago_documento`   | 7,285  | Forma de pago del documento.                                                                                                                                    |
| `enc_guias`        | 1      | Encabezado de guias de despacho.                                                                                                                                |
| `det_guias`        | 21     | Detalle de guias.                                                                                                                                               |
| `pago_guias`       | 2      | Pago de guias.                                                                                                                                                  |

### 4.11 Tablas de Evaluacion de Negocios


| Tabla            | Filas | Descripcion                                                                                                                                                                                                               |
| ---------------- | ----- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `enc_evaluacion` | 1     | Encabezado de evaluacion de negocio/cotizacion compleja. Contiene todos los parametros de costo proyectado (hilos, etiquetas, flete, mano de obra), fechas de ciclo, campos reales vs proyectados, indicadores de margen. |
| `det_evaluacion` | 1     | Detalle lineas de evaluacion: producto, cantidad, familia, precios, costos de compra y fabricacion, precio BE (punto de equilibrio), costos totales, margenes con y sin comision.                                         |

### 4.12 Tablas Contables


| Tabla               | Filas   | Descripcion                                                                                                                                                                                                                                                                                                                       |
| ------------------- | ------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `moviconta`         | 204,889 | Libro diario/mayor contable. Sin PK formal (indices compuestos). Campos: empresa, numero comprobante, tipo, linea, año, cuenta, debe, haber, tipo analisis, cta cte item, tipo documento, numero documento, vencimiento, centro costo, conciliacion, usuario de creacion/modificacion, timestamps. Tabla mas grande del sistema. |
| `corrcomprobante`   | 19      | Comprobantes de correccion. Misma estructura que moviconta.                                                                                                                                                                                                                                                                       |
| `foliocomprobantes` | 1,476   | Registro de comprobantes contables con estado (abierto/cerrado/anulado), usuario, timestamps, equipo.                                                                                                                                                                                                                             |
| `cuentas`           | 983     | Plan de cuentas. PK: (EMPRESA, CUENTA). Tipo, naturaleza, analisis, conciliacion, cuenta de clasificacion y resultado.                                                                                                                                                                                                            |
| `movicompra`        | 55,076  | Movimiento de compras para el libro de compras tributario. PK compuesta de 9 campos. Contiene distribuciones contables (cuentas afecta, exenta, total, codigo).                                                                                                                                                                   |
| `libvctos`          | 21,966  | Libro de vencimientos. PK: 6 campos. Monto, fecha de pago, valor pagado, marca, comprobante, cuenta, nombre, centro costo.                                                                                                                                                                                                        |
| `libcheques`        | 7,685   | Libro de cheques y movimientos financieros. PK: 5 campos. Debe/Haber, analisis, conciliacion, tiempo de creacion/modificacion.                                                                                                                                                                                                    |
| `ctasctes`          | 17,822  | Cuentas corrientes de terceros para conciliacion contable.                                                                                                                                                                                                                                                                        |

### 4.13 Tablas de Control de Acceso


| Tabla      | Filas | Descripcion                                                                                                                                                                  |
| ---------- | ----- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `usuarios` | 39    | Usuarios del sistema. PK: (EMPRESA, RUT_USUARIO, Sistema). RUT, nombre, clave (varchar 50, sin hash evidente), vigencia.                                                     |
| `accesos`  | 70    | Permisos por usuario y sistema. PK: (Empresa, Usuario, Sistema). 10 menus (Menu_01..10), cada menu con 20 opciones (M01_Op01..M01_Op20). Total: 210 campos bit por registro. |

### 4.14 Tablas de Inventario


| Tabla            | Filas   | Descripcion                                                                                                                                                                                                |
| ---------------- | ------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `documento_inv`  | 88,212  | Movimientos de inventario (entradas, salidas, traspasos). PK: (EMPRESA, Tipo_Documento, Numero_Doc, Item). Tipo GF (Gasto/Factura), bodega, producto, cantidades, precios, lote, vencimiento.              |
| `enc_inventario` | 22,553  | Encabezado de documentos de inventario. PK: (Empresa, Tipo_Documento, Nro_Documento). Bodega origen/destino, referencia, responsable, estado.                                                              |
| `enc_invfisico`  | 412     | Encabezado de tomas de inventario fisico. PK: (Empresa, Nro_Toma).                                                                                                                                         |
| `det_invfisico`  | 389,018 | Detalle de inventario fisico. Sin PK formal. Producto, periodo, bodega, stock vs contado, diferencia, tipo. Tabla mas grande del sistema en filas.                                                         |
| `invvalorizado`  | 4,893   | Snapshot de inventario valorizado (sin PK formal). Articulo, descripcion, bodega, stock, costo, ultimo costo, fecha ultima compra, mayor precio compra. La columna Equipo sugiere generacion por estacion. |

### 4.15 Tablas Temporales (tmp_*)

Estas tablas son utilizadas como acumuladores temporales durante la generacion de informes y procesos batch. No tienen tiempo de vida garantizado entre sesiones.


| Tabla                | Filas | Descripcion                                                       |
| -------------------- | ----- | ----------------------------------------------------------------- |
| `tmp_balancecompro`  | 25    | Balance de comprobacion temporal.                                 |
| `tmp_balancegeneral` | 426   | Balance general temporal (activos, pasivos, perdidas, ganancias). |
| `tmp_ctaficha`       | 0     | Ficha de cuenta temporal (con campo Equipo para multiusuario).    |
| `tmp_documento`      | 0     | Documento de venta temporal para impresion.                       |
| `tmp_libromayor`     | 53    | Libro mayor temporal.                                             |
| `tmp_saldodocto`     | 0     | Saldos de documentos temporales.                                  |
| `tmp_saldos`         | 1     | Saldos de inventario temporales.                                  |

### 4.16 Tablas de Alarmas y Parametros de Alerta


| Tabla          | Filas  | Descripcion                                                                                                                                                         |
| -------------- | ------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `alarmas`      | 10,902 | Registro de alarmas generadas. Sin PK formal. Empresa, tipo de alarma, dias, receptor, numero de documento, referencia, fecha cierre, equipo, estado, fecha estado. |
| `paramalarmas` | 26     | Parametros de configuracion de alarmas. PK: (Empresa, TipoAlarma, Dias, Receptor).                                                                                  |

---

## 5. Observaciones Tecnicas para la Migracion

### 5.1 Ausencia de Claves Foraneas Declaradas

La base de datos no declara ninguna FOREIGN KEY formal. Todas las relaciones son implicitas. Esto significa que:

- No existe integridad referencial en el motor de base de datos.
- Los datos huerfanos son posibles y probables.
- Durante la migracion, se necesitara un proceso de validacion y limpieza de referencias cruzadas antes de crear constraints en el nuevo sistema.

### 5.2 Patron de PK Compuesta con Campo Empresa

El patron `(Empresa varchar(3), ...)` como primer componente de la PK se repite en la totalidad de las tablas. Esto indica que la base de datos esta disenada para ser multi-empresa. En el nuevo sistema se debe considerar si se mantiene este patron o se migra a un modelo con `tenant_id` o bases de datos separadas por empresa.

### 5.3 Uso Extensivo de `double` para Identificadores

Los campos numericos de identificacion de documentos (Nro_OCompra, Nro_Solicitud, Nro_OTrabajo, etc.) son de tipo `double` en lugar de `int` o `bigint`. Esto puede causar problemas de precision en ciertos contexts. En la migracion se recomienda mapear estos campos a `BIGINT` o `DECIMAL(18,0)`.

### 5.4 Tablas de Tallas con Columnas Fijas (Talla01..Talla30)

Las tablas `tal_*` almacenan distribuciones de tallas con 30 columnas fijas (Talla01..Talla30). Esto es un patron de diseno antipattern conocido como "pivot embebido". En el nuevo sistema se recomienda normalizar a un modelo `(nro_doc, linea, codigo_talla, cantidad)`.

### 5.5 Tabla `accesos` con 200+ Columnas bit

La tabla `accesos` almacena permisos como 200+ columnas bit individuales. En el nuevo sistema se recomienda migrar a un modelo RBAC (Role-Based Access Control) con tablas de permisos normalizadas.

### 5.6 Tablas Temporales Compartidas

Las tablas `tmp_*` son compartidas entre sesiones de usuario y algunas incluyen un campo `Equipo` para discriminar por maquina. Esto es un patron de concurrencia sin aislamiento formal. En el nuevo sistema estas tablas deben reemplazarse por estructuras en memoria (cache, DTOs) o tablas temporales de sesion.

### 5.7 Contrasenas Sin Hash

La tabla `usuarios` almacena `CLAVE_USUARIO varchar(50)` sin evidencia de hash criptografico. Al migrar los usuarios, las contrasenas deben requerir restablecimiento forzado, nunca migrarse en texto plano al nuevo sistema.

### 5.8 Campos `longtext` para Especificaciones

Numerosas tablas usan `longtext` para columnas como `Especificacion`, `Observacion`, `Glosa`, `Imagenes`, `Moldes`. Esto debe planificarse explicitamente en el modelo de datos del nuevo sistema (CLOB, TEXT o almacenamiento de archivos externo).

---

## 6. Referencia Completa de Columnas por Tabla

> Esta seccion lista cada tabla con sus columnas, tipos exactos, PK, nulabilidad y cantidad de registros, extraidos directamente de la base de datos de produccion.

### acc_solicosto — 71,776 filas


| Campo         | Tipo        | Null     | Key |
| ------------- | ----------- | -------- | --- |
| Empresa       | varchar(3)  | NOT NULL | PK  |
| Nro_Solicitud | double      | NOT NULL | PK  |
| Linea         | int         | NOT NULL | PK  |
| Accesorio     | varchar(50) | NULL     |     |
| Proveedor     | double      | NULL     |     |
| Precio        | double      | NULL     |     |
| Consumo       | double      | NULL     |     |
| Unimed        | varchar(5)  | NULL     |     |
| Costo         | double      | NULL     |     |
| Precio_R      | double      | NULL     |     |
| Consumo_r     | double      | NULL     |     |
| Costo_R       | double      | NULL     |     |
| Codigo        | varchar(20) | NULL     |     |

### accesos — 70 filas

PK: (Empresa, Usuario, Sistema). Contiene 200+ columnas bit organizadas como Menu_01..Menu_10 con 20 opciones cada uno (M01_Op01..M01_Op20).

### activos — 28 filas


| Campo       | Tipo        | Key |
| ----------- | ----------- | --- |
| EMPRESA     | varchar(3)  | PK  |
| CODIGO      | varchar(20) | PK  |
| DESCRIPCION | varchar(50) |     |

### alarmas — 10,902 filas

Sin PK declarada. Campos: Empresa, TipoAlarma(int), Dias(int), Receptor(varchar 40), Nro_Documento(double), Referencia(varchar 100), Fecha_Cierre(datetime), Equipo(varchar 20), Estado(tinyint), Fecha_Estado(datetime).

### articulos — 15,649 filas

PK: (EMPRESA, Codigo_Articulo). Campos clave: Descripcion, Comentario, Moneda_Compra, Moneda_venta, Unidad_Medida, Precio_Venta, Costo, Costo_Ult_Compra, Fecha_Ult_Compra, Stock_Minimo, Stock_Maximo, Factor, Procedencia(varchar 1), flags bit (Lotes, Seriales, Compras, ControlaStock), Plantilla(int), CodigoBarra, Activo.

### bancos — 14 filas

PK: IdBanco (auto_increment). Empresa, Nombre, Codigo.

### cencosto — 575 filas

PK: (EMPRESA, CODIGO int). DESCRIPCION varchar(50).

### clientes — 9,352 filas

PK: (EMPRESA, RUT_CLIENTE int). DV_RUT, RAZON_SOCIAL(100), SIGLA(60), GIRO(50), DIRECCION(100), COMUNA(50), CIUDAD(50), TELEFONO(15), FAX(15), CORREO(50), CODIGO_VENDEDOR, CONDICION_PAGO, LISTA_PRECIO, CONTACTO, RUT_COMPRADOR(12), NOMBRE_COMPRADOR(50), CRED_MAXIMO(double), VIGEN_CREDITO(datetime), POR_DEUDA, MAX_RETRASO, ULT_COMPRA, BODEGA.

### com_solicosto — 22 filas

PK: (Empresa, Numero_OP, Linea). Fecha, CCosto, TipoComp(20), Codigo(20), Descrip(100), Cant_Soli, Prec_Soli, Cant_Bodega, Cant_Comp, Prec_Comp, Fec_Entrega, Proveedor, OrdCompra, LinCompra, Comentario(longtext), Tipo(20), Marca_Centra(255).

### configuracion_sistema — 7 filas

PK: (Empresa, Sistema). Campos de configuracion: Por_Iva, Ann_Activo, ControlaInv(bit), Rut_Nulos, Rut_Boletas, NombreFormato, Primera/Segunda/Tercera(datetime-temporadas), Local, Bodega, Vendedor, PorCostoBE, Dias_Compra/Corte/Termino (1 y 2), CostoFlete, CostoHilos, CostoFijo, CostoEtiqueta, CostoEmbalaje, ValorFijo, CostosinGratif, CostoconGratif.

### ctasctes — 17,822 filas

PK: (EMPRESA, RUT_CTACTE). DIGITO, NOMBRE(100), DIRECCION(100), COMUNA(50), CIUDAD(50), TELEFONO(50), CONTACTO(50), FONO(50), FAX(50), CORREO(50), Banco(45), TipoCta(20), NroCta(20).

### cuentas — 983 filas

PK: (EMPRESA varchar 50, CUENTA double). NOMBRE(50), TIPO(varchar 1), NATURALEZA(50), ANALISIS(50), CENTROCOSTO(bit), CONCILIACION(bit), CUENTACLAS(float), CUENTARESU(double).

### det_ocompra — 34,789 filas

PK: (Empresa, Nro_OCompra, Item). Fecha, Codigo_Producto(20), Cantidad, Precio_Unitario, SubTotal, Porcentaje_Des_Rec, Valor_Des_Rec, Total_Producto, Fec_Entrega, Cant_Entregada, Glosa(255), Tipo(20), Total_Recargos, Total_Afecto, Total_Exento, Total_Iva, Total_Documento, Tipo_Comprobante(255), Nro_Comprobante, Fec_Comprobante, Marca_Centra(255), Por_Cumplimiento, Comentario(longtext), Concepto(double), Modelo(45), Tela(45), Composicion(45), Genero(45), Color(45), Aplicacion(45).

### det_occliente — 7,834 filas

PK: (Empresa, Nro_OCCli, Linea). Producto(50), Cantidad, Logo(1), Nro_OPSC, Linea_OPSC, Modelo(45), Tela(45), Composicion(45), Genero(45), Color(45), Aplicacion(45), ProveedorAscii(45), det_ocproveedorcol(45), PUnitario, Totallin, CantFacturada, ReqOT(1 DEFAULT=N), espelineal(longtext).

### det_solicompra — 53,988 filas

PK: (Empresa, Nro_Sol, Linea). Producto(50), Proveedor, Cant_Solicitada, Cant_Bodega, Cant_Comprada, Nro_OCompra, Logo(1), Modelo(45), Tela(45), Composicion(45), Genero(45), Color(45), Aplicacion(45), ProveedorAscii(45), det_solicompracol(45), ReqOT(1 DEFAULT=N), PUnitario, espelineal(longtext).

### det_solicosto — 45,189 filas

PK: (Empresa, Nro_Solicitud, Linea). Telas(20), Provee_Telas, Precio_Unit, Consumo, Uni_Med(6), Costo, Descrip(50), Precio_Unit_R, Consumo_R, Costo_R, Entrega, Devolucion, Ancho, Costo_Unidad, ReqOT(1 DEFAULT=N).

### enc_occliente — 1,386 filas

PK: (Empresa, Nro_OCCli). Fecha, Cliente, Vendedor, Estado(int), OC_Cliente(50), Fecha_Entrega, CCosto, FechaHoraTer, Especificaciones(longtext), Personalizada(1 DEFAULT=N), PorcCumplimiento, Fechaultmod.

### enc_ocompra — 12,291 filas

PK: (EMPRESA, Nro_OCompra). Fecha_Documento, Periodo, Rut_Proveedor(NOT NULL), Centro_Costo, Comprador, Local, Moneda(50), Paridad(50), Condicion_pago(10), Fec_Entrega, Glosa(90), Sub_Total, Total_Descuentos, Total_Recargos, Total_Afecto, Total_Exento, Total_Iva, Total_Documento, Tipo_Comprobante(1), Nro_Comprobante, Fec_Comprobante, Marca_Centra(1), Por_Cumplimiento, Comentario(longtext).

### enc_solicompra — 8,180 filas

PK: (Empresa, Nro_Sol). Fecha, Cliente, Vendedor, Estado, OC_Cliente(50), Fecha_Entrega, CCosto, FechaHoraTer, Especificaciones(longtext), Referencia(50), Personalizada(1 DEFAULT=N).

### enc_solicosto — 19,960 filas

PK: (Empresa, Nro_Solicitud). 70+ campos. Ver seccion 4.3 para descripcion completa.

### enc_otrabajo — 4,248 filas

PK: (Empresa, Nro_OTrabajo). Fecha, Cliente, Vendedor, Estado, OC_Cliente, Nro_Solicitud, Fecha_Entrega, CCosto, FechaHoraTer, Especificaciones(longtext), Referencia.

### enc_oservicios — 1,078 filas

PK: (Empresa, Nro_OServicio). Fecha, Rut_Taller, Nro_OP, TipoDoc, Fec_Entrega, Valor, Obser(longtext), datos contacto taller, CortesPrendas, TipoOrigen, Cliente, Prenda, Por_Cumplimiento.

### empresas — 5 filas

PK: RUT_EMPRESA(int). DIGITO, NOMBRE_EMPRESA(50), CODIGO(3), SIGLA(50), GIRO(80), RUT_REPRESEN, DV_REPRESEN, NOMBRE_REPRESEN, DIRECCION, COMUNA, CIUDAD, TELEFONO, FAX, CORREO, CODIGO_ACTIVIDAD.

### gastos — 444 filas

PK: (EMPRESA, CODIGO varchar 20). DESCRIPCION(50), Estado(int).

### invvalorizado — 4,893 filas

Sin PK. Equipo, Empresa, Articulo(20), Descripcion(60), Bodega(20), Stock, Costo, UltCosto, FechaUComp, MayorPrecCom.

### log_solicosto — 19,354 filas

Sin PK. Empresa, Nro_Solicitud, Linea, Tipo(15), Cantidad, Precio, Proveedor, Cantidad_R, Precio_R, Ubicacion(255), Color(255), Tamano(255).

### movicompra — 55,076 filas

PK: 9 campos (EMPRESA, PERIODO, LOCAL, TIPODOCUMENTO, NRODOCUMENTO, RUTPROVEE, LINEA). Distribucion contable: AFE_*, EXE_*, TOT_*, COD_* (cuenta, vencimiento, centro costo, item). TIPOORIGEN, NROORIGEN.

### moviconta — 204,889 filas

Sin PK formal (solo indices). 25 campos incluyendo auditoria (USUA_CREA, USUA_MODI, FECH_CREA, FECH_MODI).

### ocrecuperada — 6,120 filas

PK: (Empresa, NroOC varchar 30, Linea). Codigo, Descripcion, ProductoCli, Talla, Genero, Color, Logo, Umed, FecEntrega, Cantidad, PreUnitario, Total, NroNota.

### parametros — 896 filas

PK: (EMPRESA, TIPO varchar 10, CODIGO int). DESCRIPCION(50), ATRIBUTO1(50), ATRIBUTO2(50), ATRIBUTO3(double), ATRIBUTO4(double). Tabla catastro generica para todos los catalogos del sistema.

### pla_solicosto — 136,046 filas

PK: (Empresa, NroCosto, Plantilla, Linea). Nombre(255), Descripcion(longtext).

### plantillas — 129 filas

PK: (Empresa, Plantilla, Correlativo). Nombre(255).

### proveedores — 2,008 filas

PK: (EMPRESA, RUT_PROVEEDOR). Misma estructura que clientes mas campos de credito. Sin campo BODEGA.

### receptores — 7 filas

PK: (Empresa, Receptor varchar 40). Correo(80), Equipo(20).

### relacioncodigos — 139 filas

PK: (Empresa, Cliente double, CodigoCliente varchar 20). DescripCliente, CodigoInterno(NOT NULL), Modelo, Composicion, Tela.

### tal_occliente — 7,882 filas

PK: (Empresa, Nro_OCCli, Linea, Tipo varchar 2). Talla01..Talla30(int), NroSCOP, Estado, PrecCompra.

### tal_ocompra — 5,661 filas

PK: (Empresa, Nro_OCompra, Linea). Talla01..Talla30(int), NroSCOP, PrecCompra.

### tal_solicompra — 7,833 filas

PK: (Empresa, Nro_Sol, Linea). Talla01..Talla30(int), PrecCompra.

### tal_solicosto — 10,025 filas

PK: (Empresa, Nro_Solicitud, Linea). Talla(varchar 50), Cantidad, CantOServ.

### tal_solicostores — 5,121 filas

PK: (Empresa, Nro_Solicitud, Linea). Estructura identica a tal_solicosto.

### tal_otrabajo — 1,386 filas

PK: (Empresa, Nro_OT, Linea). Talla01..Talla30(int).

### usuarios — 39 filas

PK: (EMPRESA, RUT_USUARIO varchar 15, Sistema varchar 10). DIGITO, NOMBRE_USUARIO(50), CLAVE_USUARIO(50)[SIN HASH], Vigencia(bit).

---

*Documento generado por extraccion directa de la base de datos de produccion `bdsispro` en `192.168.2.3:3306`.*
*Script de extraccion: Python 3.14 + mysql-connector-python.*
*Fecha: 2026-02-26 — Para uso exclusivo del equipo de migracion ERP.*
