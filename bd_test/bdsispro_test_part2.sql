-- ============================================================
-- SISPRO MIGRATION TEST — PARTE 2: Fases C y D (Core MenuCosto)
-- Requiere haber ejecutado primero bdsispro_test_part1.sql
-- ============================================================
USE bdsispro_test;

-- ============================================================
-- FASE C — Solicitudes de Costo (núcleo del módulo)
-- ============================================================

CREATE TABLE enc_solicosto (
  Empresa               VARCHAR(3)  NOT NULL,
  Nro_Solicitud         DOUBLE      NOT NULL,
  Fecha                 DATETIME,
  Cliente               INT,
  Vendedor              INT,
  Estado                INT,
  Articulo              VARCHAR(50),
  Cantidad              DOUBLE,
  Precio                DOUBLE,
  CostoTelas            DOUBLE,
  CostoHilos            DOUBLE,
  CostoEtiqueta         DOUBLE,
  CostoFlete            DOUBLE,
  CostoMOsinGratif      DOUBLE,
  CostoMOconGratif      DOUBLE,
  CostoBordado          DOUBLE,
  CostoLogotipo         DOUBLE,
  CostoEstampado        DOUBLE,
  CostoAccesorios       DOUBLE,
  CostoTotal            DOUBLE,
  CostoTotal_R          DOUBLE,
  Fec_Solicitud         DATETIME,
  Fec_Costo             DATETIME,
  Fec_Autorizacion      DATETIME,
  Fec_OP                DATETIME,
  Fec_Entrega           DATETIME,
  Fec_Cierre            DATETIME,
  Nro_OP                DOUBLE,
  CCosto                INT,
  Especificaciones      LONGTEXT,
  Observaciones         LONGTEXT,
  Personalizada         VARCHAR(1)  DEFAULT 'N',
  Plantilla             INT,
  Modelo                VARCHAR(45),
  Tela                  VARCHAR(45),
  Composicion           VARCHAR(45),
  Genero                VARCHAR(45),
  Color                 VARCHAR(45),
  Aplicacion            VARCHAR(45),
  PorcCumplimiento      DOUBLE,
  Fechaultmod           DATETIME,
  PRIMARY KEY (Empresa, Nro_Solicitud)
) ENGINE=InnoDB;

CREATE TABLE det_solicosto (
  Empresa        VARCHAR(3)  NOT NULL,
  Nro_Solicitud  DOUBLE      NOT NULL,
  Linea          INT         NOT NULL,
  Telas          VARCHAR(20),
  Provee_Telas   DOUBLE,
  Precio_Unit    DOUBLE,
  Consumo        DOUBLE,
  Uni_Med        VARCHAR(6),
  Costo          DOUBLE,
  Descrip        VARCHAR(50),
  Precio_Unit_R  DOUBLE,
  Consumo_R      DOUBLE,
  Costo_R        DOUBLE,
  Entrega        DOUBLE,
  Devolucion     DOUBLE,
  Ancho          DOUBLE,
  Costo_Unidad   DOUBLE,
  ReqOT          VARCHAR(1)  DEFAULT 'N',
  PRIMARY KEY (Empresa, Nro_Solicitud, Linea)
) ENGINE=InnoDB;

CREATE TABLE acc_solicosto (
  Empresa        VARCHAR(3)  NOT NULL,
  Nro_Solicitud  DOUBLE      NOT NULL,
  Linea          INT         NOT NULL,
  Accesorio      VARCHAR(50),
  Proveedor      DOUBLE,
  Precio         DOUBLE,
  Consumo        DOUBLE,
  Unimed         VARCHAR(5),
  Costo          DOUBLE,
  Precio_R       DOUBLE,
  Consumo_r      DOUBLE,
  Costo_R        DOUBLE,
  Codigo         VARCHAR(20),
  PRIMARY KEY (Empresa, Nro_Solicitud, Linea)
) ENGINE=InnoDB;

CREATE TABLE pla_solicosto (
  Empresa     VARCHAR(3)   NOT NULL,
  NroCosto    DOUBLE       NOT NULL,
  Plantilla   INT          NOT NULL,
  Linea       INT          NOT NULL,
  Nombre      VARCHAR(255),
  Descripcion LONGTEXT,
  PRIMARY KEY (Empresa, NroCosto, Plantilla, Linea)
) ENGINE=InnoDB;

CREATE TABLE tal_solicosto (
  Empresa        VARCHAR(3)  NOT NULL,
  Nro_Solicitud  DOUBLE      NOT NULL,
  Linea          INT         NOT NULL,
  Talla          VARCHAR(50),
  Cantidad       DOUBLE,
  CantOServ      DOUBLE,
  PRIMARY KEY (Empresa, Nro_Solicitud, Linea)
) ENGINE=InnoDB;

CREATE TABLE tal_solicostores (
  Empresa        VARCHAR(3)  NOT NULL,
  Nro_Solicitud  DOUBLE      NOT NULL,
  Linea          INT         NOT NULL,
  Talla          VARCHAR(50),
  Cantidad       DOUBLE,
  CantOServ      DOUBLE,
  PRIMARY KEY (Empresa, Nro_Solicitud, Linea)
) ENGINE=InnoDB;

-- Sin PK formal (igual que producción)
CREATE TABLE log_solicosto (
  Empresa        VARCHAR(3),
  Nro_Solicitud  DOUBLE,
  Linea          INT,
  Tipo           VARCHAR(15),
  Cantidad       DOUBLE,
  Precio         DOUBLE,
  Proveedor      DOUBLE,
  Cantidad_R     DOUBLE,
  Precio_R       DOUBLE,
  Ubicacion      VARCHAR(255),
  Color          VARCHAR(255),
  Tamano         VARCHAR(255)
) ENGINE=InnoDB;

CREATE TABLE com_solicosto (
  Empresa       VARCHAR(3)  NOT NULL,
  Numero_OP     DOUBLE      NOT NULL,
  Linea         INT         NOT NULL,
  Fecha         DATETIME,
  CCosto        INT,
  TipoComp      VARCHAR(20),
  Codigo        VARCHAR(20),
  Descrip       VARCHAR(100),
  Cant_Soli     DOUBLE,
  Prec_Soli     DOUBLE,
  Cant_Bodega   DOUBLE,
  Cant_Comp     DOUBLE,
  Prec_Comp     DOUBLE,
  Fec_Entrega   DATETIME,
  Proveedor     DOUBLE,
  OrdCompra     DOUBLE,
  LinCompra     INT,
  Comentario    LONGTEXT,
  Tipo          VARCHAR(20),
  Marca_Centra  VARCHAR(255),
  PRIMARY KEY (Empresa, Numero_OP, Linea)
) ENGINE=InnoDB;

-- ============================================================
-- FASE D — Órdenes operativas
-- ============================================================

-- Solicitudes de Compra
CREATE TABLE enc_solicompra (
  Empresa          VARCHAR(3)  NOT NULL,
  Nro_Sol          DOUBLE      NOT NULL,
  Fecha            DATETIME,
  Cliente          INT,
  Vendedor         INT,
  Estado           INT,
  OC_Cliente       VARCHAR(50),
  Fecha_Entrega    DATETIME,
  CCosto           INT,
  FechaHoraTer     DATETIME,
  Especificaciones LONGTEXT,
  Referencia       VARCHAR(50),
  Personalizada    VARCHAR(1)  DEFAULT 'N',
  PRIMARY KEY (Empresa, Nro_Sol)
) ENGINE=InnoDB;

CREATE TABLE det_solicompra (
  Empresa          VARCHAR(3)  NOT NULL,
  Nro_Sol          DOUBLE      NOT NULL,
  Linea            INT         NOT NULL,
  Producto         VARCHAR(50),
  Proveedor        DOUBLE,
  Cant_Solicitada  DOUBLE,
  Cant_Bodega      DOUBLE,
  Cant_Comprada    DOUBLE,
  Nro_OCompra      DOUBLE,
  Logo             VARCHAR(1),
  Modelo           VARCHAR(45),
  Tela             VARCHAR(45),
  Composicion      VARCHAR(45),
  Genero           VARCHAR(45),
  Color            VARCHAR(45),
  Aplicacion       VARCHAR(45),
  ProveedorAscii   VARCHAR(45),
  ReqOT            VARCHAR(1)  DEFAULT 'N',
  PUnitario        DOUBLE,
  espelineal       LONGTEXT,
  PRIMARY KEY (Empresa, Nro_Sol, Linea)
) ENGINE=InnoDB;

CREATE TABLE tal_solicompra (
  Empresa    VARCHAR(3) NOT NULL,
  Nro_Sol    DOUBLE     NOT NULL,
  Linea      INT        NOT NULL,
  Talla01 INT, Talla02 INT, Talla03 INT, Talla04 INT, Talla05 INT,
  Talla06 INT, Talla07 INT, Talla08 INT, Talla09 INT, Talla10 INT,
  Talla11 INT, Talla12 INT, Talla13 INT, Talla14 INT, Talla15 INT,
  Talla16 INT, Talla17 INT, Talla18 INT, Talla19 INT, Talla20 INT,
  Talla21 INT, Talla22 INT, Talla23 INT, Talla24 INT, Talla25 INT,
  Talla26 INT, Talla27 INT, Talla28 INT, Talla29 INT, Talla30 INT,
  PrecCompra DOUBLE,
  PRIMARY KEY (Empresa, Nro_Sol, Linea)
) ENGINE=InnoDB;

-- OC Cliente
CREATE TABLE enc_occliente (
  Empresa           VARCHAR(3) NOT NULL,
  Nro_OCCli         DOUBLE     NOT NULL,
  Fecha             DATETIME,
  Cliente           INT,
  Vendedor          INT,
  Estado            INT,
  OC_Cliente        VARCHAR(50),
  Fecha_Entrega     DATETIME,
  CCosto            INT,
  FechaHoraTer      DATETIME,
  Especificaciones  LONGTEXT,
  Personalizada     VARCHAR(1) DEFAULT 'N',
  PorcCumplimiento  DOUBLE,
  Fechaultmod       DATETIME,
  PRIMARY KEY (Empresa, Nro_OCCli)
) ENGINE=InnoDB;

CREATE TABLE det_occliente (
  Empresa        VARCHAR(3)  NOT NULL,
  Nro_OCCli      DOUBLE      NOT NULL,
  Linea          INT         NOT NULL,
  Producto       VARCHAR(50),
  Cantidad       DOUBLE,
  Logo           VARCHAR(1),
  Nro_OPSC       DOUBLE,
  Linea_OPSC     INT,
  Modelo         VARCHAR(45),
  Tela           VARCHAR(45),
  Composicion    VARCHAR(45),
  Genero         VARCHAR(45),
  Color          VARCHAR(45),
  Aplicacion     VARCHAR(45),
  ProveedorAscii VARCHAR(45),
  PUnitario      DOUBLE,
  Totallin       DOUBLE,
  CantFacturada  DOUBLE,
  ReqOT          VARCHAR(1)  DEFAULT 'N',
  espelineal     LONGTEXT,
  PRIMARY KEY (Empresa, Nro_OCCli, Linea)
) ENGINE=InnoDB;

CREATE TABLE tal_occliente (
  Empresa    VARCHAR(3)  NOT NULL,
  Nro_OCCli  DOUBLE      NOT NULL,
  Linea      INT         NOT NULL,
  Tipo       VARCHAR(2)  NOT NULL,
  Talla01 INT, Talla02 INT, Talla03 INT, Talla04 INT, Talla05 INT,
  Talla06 INT, Talla07 INT, Talla08 INT, Talla09 INT, Talla10 INT,
  Talla11 INT, Talla12 INT, Talla13 INT, Talla14 INT, Talla15 INT,
  Talla16 INT, Talla17 INT, Talla18 INT, Talla19 INT, Talla20 INT,
  Talla21 INT, Talla22 INT, Talla23 INT, Talla24 INT, Talla25 INT,
  Talla26 INT, Talla27 INT, Talla28 INT, Talla29 INT, Talla30 INT,
  NroSCOP    DOUBLE,
  Estado     INT,
  PrecCompra DOUBLE,
  PRIMARY KEY (Empresa, Nro_OCCli, Linea, Tipo)
) ENGINE=InnoDB;

-- OC Proveedor
CREATE TABLE enc_ocompra (
  EMPRESA           VARCHAR(3)  NOT NULL,
  Nro_OCompra       DOUBLE      NOT NULL,
  Fecha_Documento   DATETIME,
  Periodo           INT,
  Rut_Proveedor     INT         NOT NULL,
  Centro_Costo      INT,
  Comprador         INT,
  Local             INT,
  Moneda            VARCHAR(50),
  Paridad           VARCHAR(50),
  Condicion_pago    VARCHAR(10),
  Fec_Entrega       DATETIME,
  Glosa             VARCHAR(90),
  Sub_Total         DOUBLE,
  Total_Descuentos  DOUBLE,
  Total_Recargos    DOUBLE,
  Total_Afecto      DOUBLE,
  Total_Exento      DOUBLE,
  Total_Iva         DOUBLE,
  Total_Documento   DOUBLE,
  Tipo_Comprobante  VARCHAR(1),
  Nro_Comprobante   DOUBLE,
  Fec_Comprobante   DATETIME,
  Marca_Centra      VARCHAR(1),
  Por_Cumplimiento  DOUBLE,
  Comentario        LONGTEXT,
  PRIMARY KEY (EMPRESA, Nro_OCompra)
) ENGINE=InnoDB;

CREATE TABLE det_ocompra (
  Empresa              VARCHAR(3)  NOT NULL,
  Nro_OCompra          DOUBLE      NOT NULL,
  Item                 INT         NOT NULL,
  Fecha                DATETIME,
  Codigo_Producto      VARCHAR(20),
  Cantidad             DOUBLE,
  Precio_Unitario      DOUBLE,
  SubTotal             DOUBLE,
  Porcentaje_Des_Rec   DOUBLE,
  Valor_Des_Rec        DOUBLE,
  Total_Producto       DOUBLE,
  Fec_Entrega          DATETIME,
  Cant_Entregada       DOUBLE,
  Glosa                VARCHAR(255),
  Tipo                 VARCHAR(20),
  Total_Recargos       DOUBLE,
  Total_Afecto         DOUBLE,
  Total_Exento         DOUBLE,
  Total_Iva            DOUBLE,
  Total_Documento      DOUBLE,
  Tipo_Comprobante     VARCHAR(255),
  Nro_Comprobante      DOUBLE,
  Fec_Comprobante      DATETIME,
  Marca_Centra         VARCHAR(255),
  Por_Cumplimiento     DOUBLE,
  Comentario           LONGTEXT,
  Concepto             DOUBLE,
  Modelo               VARCHAR(45),
  Tela                 VARCHAR(45),
  Composicion          VARCHAR(45),
  Genero               VARCHAR(45),
  Color                VARCHAR(45),
  Aplicacion           VARCHAR(45),
  PRIMARY KEY (Empresa, Nro_OCompra, Item)
) ENGINE=InnoDB;

CREATE TABLE tal_ocompra (
  Empresa      VARCHAR(3) NOT NULL,
  Nro_OCompra  DOUBLE     NOT NULL,
  Linea        INT        NOT NULL,
  Talla01 INT, Talla02 INT, Talla03 INT, Talla04 INT, Talla05 INT,
  Talla06 INT, Talla07 INT, Talla08 INT, Talla09 INT, Talla10 INT,
  Talla11 INT, Talla12 INT, Talla13 INT, Talla14 INT, Talla15 INT,
  Talla16 INT, Talla17 INT, Talla18 INT, Talla19 INT, Talla20 INT,
  Talla21 INT, Talla22 INT, Talla23 INT, Talla24 INT, Talla25 INT,
  Talla26 INT, Talla27 INT, Talla28 INT, Talla29 INT, Talla30 INT,
  NroSCOP    DOUBLE,
  PrecCompra DOUBLE,
  PRIMARY KEY (Empresa, Nro_OCompra, Linea)
) ENGINE=InnoDB;

CREATE TABLE desrec_ocompra (
  Empresa      VARCHAR(3) NOT NULL,
  Nro_OCompra  DOUBLE     NOT NULL,
  Item         INT        NOT NULL,
  Tipo         VARCHAR(20),
  Porcentaje   DOUBLE,
  Valor        DOUBLE,
  PRIMARY KEY (Empresa, Nro_OCompra, Item)
) ENGINE=InnoDB;

CREATE TABLE pago_ocompra (
  Empresa      VARCHAR(3)  NOT NULL,
  Nro_OCompra  DOUBLE      NOT NULL,
  Codigo       INT         NOT NULL,
  Fecha        DATETIME    NOT NULL,
  Vencimiento  DATETIME,
  Valor        DOUBLE,
  PRIMARY KEY (Empresa, Nro_OCompra, Codigo)
) ENGINE=InnoDB;

CREATE TABLE ocrecuperada (
  Empresa       VARCHAR(3)  NOT NULL,
  NroOC         VARCHAR(30) NOT NULL,
  Linea         INT         NOT NULL,
  Codigo        VARCHAR(20),
  Descripcion   VARCHAR(100),
  ProductoCli   VARCHAR(50),
  Talla         VARCHAR(20),
  Genero        VARCHAR(20),
  Color         VARCHAR(20),
  Logo          VARCHAR(1),
  Umed          VARCHAR(10),
  FecEntrega    DATETIME,
  Cantidad      DOUBLE,
  PreUnitario   DOUBLE,
  Total         DOUBLE,
  NroNota       VARCHAR(30),
  PRIMARY KEY (Empresa, NroOC, Linea)
) ENGINE=InnoDB;

-- Órdenes de Trabajo
CREATE TABLE enc_otrabajo (
  Empresa          VARCHAR(3) NOT NULL,
  Nro_OTrabajo     DOUBLE     NOT NULL,
  Fecha            DATETIME,
  Cliente          INT,
  Vendedor         INT,
  Estado           INT,
  OC_Cliente       VARCHAR(50),
  Nro_Solicitud    DOUBLE,
  Fecha_Entrega    DATETIME,
  CCosto           INT,
  FechaHoraTer     DATETIME,
  Especificaciones LONGTEXT,
  Referencia       VARCHAR(50),
  PRIMARY KEY (Empresa, Nro_OTrabajo)
) ENGINE=InnoDB;

CREATE TABLE det_otrabajo (
  Empresa          VARCHAR(3)  NOT NULL,
  Nro_OTrabajo     DOUBLE      NOT NULL,
  Linea            INT         NOT NULL,
  Producto         VARCHAR(50),
  Proveedor        DOUBLE,
  Cant_Solicitada  DOUBLE,
  Cant_Bodega      DOUBLE,
  Cant_Comprada    DOUBLE,
  Modelo           VARCHAR(45),
  Tela             VARCHAR(45),
  Composicion      VARCHAR(45),
  Genero           VARCHAR(45),
  Color            VARCHAR(45),
  Aplicacion       VARCHAR(45),
  Logo             VARCHAR(1),
  ReqOT            VARCHAR(1)  DEFAULT 'N',
  PRIMARY KEY (Empresa, Nro_OTrabajo, Linea)
) ENGINE=InnoDB;

CREATE TABLE tal_otrabajo (
  Empresa       VARCHAR(3) NOT NULL,
  Nro_OT        DOUBLE     NOT NULL,
  Linea         INT        NOT NULL,
  Talla01 INT, Talla02 INT, Talla03 INT, Talla04 INT, Talla05 INT,
  Talla06 INT, Talla07 INT, Talla08 INT, Talla09 INT, Talla10 INT,
  Talla11 INT, Talla12 INT, Talla13 INT, Talla14 INT, Talla15 INT,
  Talla16 INT, Talla17 INT, Talla18 INT, Talla19 INT, Talla20 INT,
  Talla21 INT, Talla22 INT, Talla23 INT, Talla24 INT, Talla25 INT,
  Talla26 INT, Talla27 INT, Talla28 INT, Talla29 INT, Talla30 INT,
  PRIMARY KEY (Empresa, Nro_OT, Linea)
) ENGINE=InnoDB;

-- Órdenes de Servicio
CREATE TABLE enc_oservicios (
  Empresa           VARCHAR(3) NOT NULL,
  Nro_OServicio     DOUBLE     NOT NULL,
  Fecha             DATETIME,
  Rut_Taller        INT,
  Nro_OP            DOUBLE,
  TipoDoc           VARCHAR(20),
  Fec_Entrega       DATETIME,
  Valor             DOUBLE,
  Obser             LONGTEXT,
  ContactoTaller    VARCHAR(60),
  TelefonoTaller    VARCHAR(20),
  CortesPrendas     DOUBLE,
  TipoOrigen        VARCHAR(20),
  Cliente           INT,
  Prenda            VARCHAR(50),
  Por_Cumplimiento  DOUBLE,
  PRIMARY KEY (Empresa, Nro_OServicio)
) ENGINE=InnoDB;

CREATE TABLE mat_oservicios (
  Empresa       VARCHAR(3)  NOT NULL,
  Nro_OServicio DOUBLE      NOT NULL,
  Linea         INT         NOT NULL,
  Cantidad      DOUBLE,
  Medida        VARCHAR(20),
  Descripcion   VARCHAR(100),
  Referencias   VARCHAR(100),
  PRIMARY KEY (Empresa, Nro_OServicio, Linea)
) ENGINE=InnoDB;

CREATE TABLE req_oservicios (
  Empresa       VARCHAR(3) NOT NULL,
  Nro_OServicio DOUBLE     NOT NULL,
  Linea         INT        NOT NULL,
  Cantidad      DOUBLE,
  Descripcion   LONGTEXT,
  Talla         VARCHAR(20),
  Vinculos      VARCHAR(100),
  PRIMARY KEY (Empresa, Nro_OServicio, Linea)
) ENGINE=InnoDB;

CREATE TABLE ent_oservicios (
  Empresa       VARCHAR(3) NOT NULL,
  Nro_OServicio DOUBLE     NOT NULL,
  Linea         INT        NOT NULL,
  Cantidad      DOUBLE,
  Fecha         DATETIME,
  Descripcion   VARCHAR(100),
  PRIMARY KEY (Empresa, Nro_OServicio, Linea)
) ENGINE=InnoDB;

-- OC Bordados
CREATE TABLE enc_ocbordados (
  Empresa        VARCHAR(3) NOT NULL,
  Nro_Orden      DOUBLE     NOT NULL,
  Fecha          DATETIME,
  Proveedor      INT,
  Estado         INT,
  Observaciones  LONGTEXT,
  Muestra        VARCHAR(100),
  Especificacion LONGTEXT,
  Ficha          VARCHAR(100),
  PRIMARY KEY (Empresa, Nro_Orden)
) ENGINE=InnoDB;

CREATE TABLE det_ocbordados (
  Empresa     VARCHAR(3) NOT NULL,
  Nro_Orden   DOUBLE     NOT NULL,
  Linea       INT        NOT NULL,
  Descripcion VARCHAR(100),
  Cantidad    DOUBLE,
  Precio      DOUBLE,
  Total       DOUBLE,
  Tipo        VARCHAR(20),
  Numero      DOUBLE,
  PRIMARY KEY (Empresa, Nro_Orden, Linea)
) ENGINE=InnoDB;

SELECT 'PARTE 2 OK: Fases C y D (core MenuCosto) creadas.' AS status;
