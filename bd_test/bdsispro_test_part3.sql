-- ============================================================
-- SISPRO MIGRATION TEST — PARTE 3: Fases E-I + Tablas extra
-- Requiere haber ejecutado part1 y part2 primero
-- ============================================================
USE bdsispro_test;

-- ============================================================
-- FASE E — Documentos comerciales
-- ============================================================

CREATE TABLE enc_documento (
  EMPRESA           VARCHAR(3)  NOT NULL,
  Tipo_Documento    VARCHAR(10) NOT NULL,
  Folio_Inicial     DOUBLE      NOT NULL,
  Fecha             DATETIME,
  Rut_Cliente       INT,
  Vendedor          INT,
  Estado            INT,
  Sol_Compra        DOUBLE,
  Sol_Costo         DOUBLE,
  Sub_Total         DOUBLE,
  Total_Descuentos  DOUBLE,
  Total_Recargos    DOUBLE,
  Total_Afecto      DOUBLE,
  Total_Exento      DOUBLE,
  Total_Iva         DOUBLE,
  Total_Documento   DOUBLE,
  Glosa             VARCHAR(200),
  Nro_Comprobante   DOUBLE,
  PRIMARY KEY (EMPRESA, Tipo_Documento, Folio_Inicial)
) ENGINE=InnoDB;

CREATE TABLE det_documento (
  EMPRESA         VARCHAR(3)  NOT NULL,
  Tipo_Documento  VARCHAR(10) NOT NULL,
  Folio_Inicial   DOUBLE      NOT NULL,
  Item            INT         NOT NULL,
  Codigo_Producto VARCHAR(20),
  Cantidad        DOUBLE,
  Precio_Unitario DOUBLE,
  SubTotal        DOUBLE,
  Descuento       DOUBLE,
  Total_Linea     DOUBLE,
  Glosa           VARCHAR(255),
  DocOrigen       VARCHAR(10),
  NroOrigen       DOUBLE,
  LinOrigen       INT,
  PRIMARY KEY (EMPRESA, Tipo_Documento, Folio_Inicial, Item)
) ENGINE=InnoDB;

CREATE TABLE ser_documento (
  EMPRESA         VARCHAR(3)  NOT NULL,
  Tipo_Documento  VARCHAR(10) NOT NULL,
  Folio_Inicial   DOUBLE      NOT NULL,
  Item            INT         NOT NULL,
  Descripcion     VARCHAR(255),
  Cantidad        DOUBLE,
  Precio          DOUBLE,
  Total           DOUBLE,
  PRIMARY KEY (EMPRESA, Tipo_Documento, Folio_Inicial, Item)
) ENGINE=InnoDB;

CREATE TABLE desrec_documento (
  EMPRESA         VARCHAR(3)  NOT NULL,
  Tipo_Documento  VARCHAR(10) NOT NULL,
  Folio_Inicial   DOUBLE      NOT NULL,
  Item            INT         NOT NULL,
  Tipo            VARCHAR(20),
  Porcentaje      DOUBLE,
  Valor           DOUBLE,
  PRIMARY KEY (EMPRESA, Tipo_Documento, Folio_Inicial, Item)
) ENGINE=InnoDB;

CREATE TABLE pago_documento (
  EMPRESA         VARCHAR(3)  NOT NULL,
  Tipo_Documento  VARCHAR(10) NOT NULL,
  Folio_Inicial   DOUBLE      NOT NULL,
  Item            INT         NOT NULL,
  FormaPago       VARCHAR(30),
  Monto           DOUBLE,
  Vencimiento     DATETIME,
  PRIMARY KEY (EMPRESA, Tipo_Documento, Folio_Inicial, Item)
) ENGINE=InnoDB;

CREATE TABLE enc_guias (
  EMPRESA    VARCHAR(3) NOT NULL,
  Nro_Guia   DOUBLE     NOT NULL,
  Fecha      DATETIME,
  Destinatario VARCHAR(100),
  Estado     INT,
  PRIMARY KEY (EMPRESA, Nro_Guia)
) ENGINE=InnoDB;

CREATE TABLE det_guias (
  Empresa   VARCHAR(3) NOT NULL,
  Nro_Guia  DOUBLE     NOT NULL,
  Item      INT        NOT NULL,
  Producto  VARCHAR(50),
  Cantidad  DOUBLE,
  Precio    DOUBLE,
  Total     DOUBLE,
  PRIMARY KEY (Empresa, Nro_Guia, Item)
) ENGINE=InnoDB;

CREATE TABLE desrec_guias (
  Empresa   VARCHAR(3),
  Nro_Guia  DOUBLE,
  Item      INT,
  Tipo      VARCHAR(20),
  Valor     DOUBLE
) ENGINE=InnoDB;

CREATE TABLE pago_guias (
  Empresa   VARCHAR(3)  NOT NULL,
  Nro_Guia  DOUBLE      NOT NULL,
  Codigo    INT         NOT NULL,
  Monto     DOUBLE,
  Fecha     DATETIME,
  PRIMARY KEY (Empresa, Nro_Guia, Codigo)
) ENGINE=InnoDB;

-- ============================================================
-- FASE F — Inventario
-- ============================================================

CREATE TABLE enc_inventario (
  Empresa          VARCHAR(3)  NOT NULL,
  Tipo_Documento   VARCHAR(10) NOT NULL,
  Nro_Documento    DOUBLE      NOT NULL,
  Fecha            DATETIME,
  Bodega_Origen    VARCHAR(20),
  Bodega_Destino   VARCHAR(20),
  Referencia       VARCHAR(100),
  Responsable      VARCHAR(60),
  Estado           INT,
  PRIMARY KEY (Empresa, Tipo_Documento, Nro_Documento)
) ENGINE=InnoDB;

CREATE TABLE documento_inv (
  EMPRESA          VARCHAR(3)  NOT NULL,
  Tipo_Documento   VARCHAR(10) NOT NULL,
  Numero_Doc       DOUBLE      NOT NULL,
  Item             INT         NOT NULL,
  Tipo_GF          VARCHAR(5),
  Bodega           VARCHAR(20),
  Codigo_Producto  VARCHAR(20),
  Cantidad         DOUBLE,
  Precio           DOUBLE,
  Costo            DOUBLE,
  Lote             VARCHAR(30),
  Vencimiento      DATETIME,
  PRIMARY KEY (EMPRESA, Tipo_Documento, Numero_Doc, Item)
) ENGINE=InnoDB;

CREATE TABLE enc_invfisico (
  Empresa   VARCHAR(3) NOT NULL,
  Nro_Toma  DOUBLE     NOT NULL,
  Fecha     DATETIME,
  Bodega    VARCHAR(20),
  Estado    INT,
  Responsable VARCHAR(60),
  PRIMARY KEY (Empresa, Nro_Toma)
) ENGINE=InnoDB;

-- Sin PK formal (igual que producción)
CREATE TABLE det_invfisico (
  Empresa   VARCHAR(3),
  Nro_Toma  DOUBLE,
  Producto  VARCHAR(20),
  Periodo   INT,
  Bodega    VARCHAR(20),
  Stock     DOUBLE,
  Contado   DOUBLE,
  Diferencia DOUBLE,
  Tipo      VARCHAR(10)
) ENGINE=InnoDB;

-- Sin PK formal
CREATE TABLE invvalorizado (
  Equipo       VARCHAR(20),
  Empresa      VARCHAR(3),
  Articulo     VARCHAR(20),
  Descripcion  VARCHAR(60),
  Bodega       VARCHAR(20),
  Stock        DOUBLE,
  Costo        DOUBLE,
  UltCosto     DOUBLE,
  FechaUComp   DATETIME,
  MayorPrecCom DOUBLE
) ENGINE=InnoDB;

-- ============================================================
-- FASE G — Contabilidad
-- ============================================================

CREATE TABLE cuentas (
  EMPRESA        VARCHAR(50) NOT NULL,
  CUENTA         DOUBLE      NOT NULL,
  NOMBRE         VARCHAR(50),
  TIPO           VARCHAR(1),
  NATURALEZA     VARCHAR(50),
  ANALISIS       VARCHAR(50),
  CENTROCOSTO    BIT(1),
  CONCILIACION   BIT(1),
  CUENTACLAS     FLOAT,
  CUENTARESU     DOUBLE,
  PRIMARY KEY (EMPRESA, CUENTA)
) ENGINE=InnoDB;

CREATE TABLE foliocomprobantes (
  Empresa    VARCHAR(3)  NOT NULL,
  NumComp    DOUBLE      NOT NULL,
  TipoComp   VARCHAR(10) NOT NULL,
  Ann_Comp   INT         NOT NULL,
  Estado     INT,
  Usuario    VARCHAR(30),
  Timestamp  DATETIME,
  Equipo     VARCHAR(30),
  PRIMARY KEY (Empresa, NumComp, TipoComp, Ann_Comp)
) ENGINE=InnoDB;

-- Sin PK formal
CREATE TABLE moviconta (
  EMPRESA      VARCHAR(3),
  NUMCOMP      DOUBLE,
  TIPOCOMP     VARCHAR(10),
  LINEACOM     INT,
  ANN_COMP     INT,
  CUENTA       DOUBLE,
  DEBE         DOUBLE,
  HABER        DOUBLE,
  TIPO_ANALISIS VARCHAR(10),
  CTA_CTE_ITEM DOUBLE,
  TIPO_DOCTO   VARCHAR(10),
  NRO_DOCTO    DOUBLE,
  VENCIMIENTO  DATETIME,
  CENTROCOSTO  INT,
  CONCILIACION BIT(1),
  USUA_CREA    VARCHAR(30),
  USUA_MODI    VARCHAR(30),
  FECH_CREA    DATETIME,
  FECH_MODI    DATETIME
) ENGINE=InnoDB;

CREATE TABLE corrcomprobante (
  EMPRESA    VARCHAR(3),
  NUMCOMP    DOUBLE,
  TIPOCOMP   VARCHAR(10),
  LINEACOM   INT,
  ANN_COMP   INT,
  CUENTA     DOUBLE,
  DEBE       DOUBLE,
  HABER      DOUBLE,
  USUA_CREA  VARCHAR(30),
  FECH_CREA  DATETIME
) ENGINE=InnoDB;

CREATE TABLE movicompra (
  EMPRESA        VARCHAR(3)  NOT NULL,
  PERIODO        INT         NOT NULL,
  LOCAL          INT         NOT NULL,
  TIPODOCUMENTO  VARCHAR(10) NOT NULL,
  NRODOCUMENTO   DOUBLE      NOT NULL,
  RUTPROVEE      INT         NOT NULL,
  LINEA          INT         NOT NULL,
  AFE_CUENTA     DOUBLE, AFE_MONTO DOUBLE, AFE_VCTO DATETIME,
  EXE_CUENTA     DOUBLE, EXE_MONTO DOUBLE,
  TOT_CUENTA     DOUBLE, TOT_MONTO DOUBLE,
  COD_CENCOSTO   INT,    COD_ITEMGASTO VARCHAR(20),
  TIPOORIGEN     VARCHAR(10),
  NROORIGEN      DOUBLE,
  PRIMARY KEY (EMPRESA, PERIODO, LOCAL, TIPODOCUMENTO, NRODOCUMENTO, RUTPROVEE, LINEA)
) ENGINE=InnoDB;

CREATE TABLE libcheques (
  Empresa   VARCHAR(3)  NOT NULL,
  Ano_Mov   INT         NOT NULL,
  Numero    DOUBLE      NOT NULL,
  Tip_Comp  VARCHAR(10) NOT NULL,
  Linea     INT         NOT NULL,
  Debe      DOUBLE,
  Haber     DOUBLE,
  Analisis  VARCHAR(20),
  Conciliacion BIT(1),
  Usua_Crea VARCHAR(30),
  Fech_Crea DATETIME,
  Usua_Modi VARCHAR(30),
  Fech_Modi DATETIME,
  PRIMARY KEY (Empresa, Ano_Mov, Numero, Tip_Comp, Linea)
) ENGINE=InnoDB;

CREATE TABLE libvctos (
  Empresa       VARCHAR(3)  NOT NULL,
  Local         INT         NOT NULL,
  TipoDocumento VARCHAR(10) NOT NULL,
  NroDocumento  DOUBLE      NOT NULL,
  RutProveedor  INT         NOT NULL,
  Fecha         DATETIME    NOT NULL,
  Monto         DOUBLE,
  FechaPago     DATETIME,
  ValorPagado   DOUBLE,
  Marca         BIT(1),
  Comprobante   DOUBLE,
  Cuenta        DOUBLE,
  Nombre        VARCHAR(100),
  CentroCosto   INT,
  PRIMARY KEY (Empresa, Local, TipoDocumento, NroDocumento, RutProveedor, Fecha)
) ENGINE=InnoDB;

CREATE TABLE ctasctes (
  EMPRESA     VARCHAR(3) NOT NULL,
  RUT_CTACTE  INT        NOT NULL,
  DIGITO      VARCHAR(1),
  NOMBRE      VARCHAR(100),
  DIRECCION   VARCHAR(100),
  COMUNA      VARCHAR(50),
  CIUDAD      VARCHAR(50),
  TELEFONO    VARCHAR(50),
  CONTACTO    VARCHAR(50),
  CORREO      VARCHAR(50),
  Banco       VARCHAR(45),
  TipoCta     VARCHAR(20),
  NroCta      VARCHAR(20),
  PRIMARY KEY (EMPRESA, RUT_CTACTE)
) ENGINE=InnoDB;

-- ============================================================
-- FASE H — Configuración de gestión
-- ============================================================

CREATE TABLE paramalarmas (
  Empresa     VARCHAR(3)  NOT NULL,
  TipoAlarma  INT         NOT NULL,
  Dias        INT         NOT NULL,
  Receptor    VARCHAR(40) NOT NULL,
  Descripcion VARCHAR(100),
  PRIMARY KEY (Empresa, TipoAlarma, Dias, Receptor)
) ENGINE=InnoDB;

-- Sin PK formal
CREATE TABLE alarmas (
  Empresa       VARCHAR(3),
  TipoAlarma    INT,
  Dias          INT,
  Receptor      VARCHAR(40),
  Nro_Documento DOUBLE,
  Referencia    VARCHAR(100),
  Fecha_Cierre  DATETIME,
  Equipo        VARCHAR(20),
  Estado        TINYINT,
  Fecha_Estado  DATETIME
) ENGINE=InnoDB;

CREATE TABLE paramcomisiones (
  Empresa   VARCHAR(3)  NOT NULL,
  Producto  VARCHAR(20) NOT NULL,
  Porcentaje DOUBLE,
  PRIMARY KEY (Empresa, Producto)
) ENGINE=InnoDB;

CREATE TABLE paramestresultado (
  Empresa   VARCHAR(3)  NOT NULL,
  Codigo    VARCHAR(20) NOT NULL,
  Descripcion VARCHAR(50),
  PRIMARY KEY (Empresa, Codigo)
) ENGINE=InnoDB;

CREATE TABLE presupestresultado (
  Empresa   VARCHAR(3)  NOT NULL,
  Periodo   INT         NOT NULL,
  Codigo    VARCHAR(20) NOT NULL,
  Monto     DOUBLE,
  PRIMARY KEY (Empresa, Periodo, Codigo)
) ENGINE=InnoDB;

CREATE TABLE paramcorreccion (
  Empresa      VARCHAR(3) NOT NULL,
  Codigo       INT        NOT NULL,
  Descripcion  VARCHAR(100),
  PRIMARY KEY (Empresa, Codigo)
) ENGINE=InnoDB;

CREATE TABLE enc_correccion (
  Empresa        VARCHAR(3) NOT NULL,
  Nro_Correccion DOUBLE     NOT NULL,
  Fecha          DATETIME,
  Estado         INT,
  Descripcion    VARCHAR(100),
  PRIMARY KEY (Empresa, Nro_Correccion)
) ENGINE=InnoDB;

CREATE TABLE det_correccion (
  Empresa        VARCHAR(3) NOT NULL,
  Nro_Correccion DOUBLE     NOT NULL,
  Linea          INT        NOT NULL,
  Codigo         INT,
  Descripcion    VARCHAR(100),
  Valor          DOUBLE,
  PRIMARY KEY (Empresa, Nro_Correccion, Linea)
) ENGINE=InnoDB;

CREATE TABLE pasocomprobante (
  Empresa   VARCHAR(3)  NOT NULL,
  TipoOrig  VARCHAR(10) NOT NULL,
  NroOrig   DOUBLE      NOT NULL,
  TipoDest  VARCHAR(10),
  NroDest   DOUBLE,
  PRIMARY KEY (Empresa, TipoOrig, NroOrig)
) ENGINE=InnoDB;

-- ============================================================
-- FASE I — Evaluaciones y Cotizaciones
-- ============================================================

CREATE TABLE enc_evaluacion (
  Empresa          VARCHAR(3) NOT NULL,
  Nro_Evaluacion   DOUBLE     NOT NULL,
  Fecha            DATETIME,
  Cliente          INT,
  Vendedor         INT,
  Estado           INT,
  CostoHilos       DOUBLE,
  CostoEtiqueta    DOUBLE,
  CostoFlete       DOUBLE,
  CostoMO_sinGrat  DOUBLE,
  CostoMO_conGrat  DOUBLE,
  Fec_Solicitud    DATETIME,
  PorcMargen       DOUBLE,
  PorcComision     DOUBLE,
  PRIMARY KEY (Empresa, Nro_Evaluacion)
) ENGINE=InnoDB;

CREATE TABLE det_evaluacion (
  Empresa        VARCHAR(3) NOT NULL,
  Nro_Evaluacion DOUBLE     NOT NULL,
  Linea          INT        NOT NULL,
  Producto       VARCHAR(50),
  Cantidad       DOUBLE,
  Familia        VARCHAR(20),
  PrecioVenta    DOUBLE,
  CostoCompra    DOUBLE,
  CostoFabric    DOUBLE,
  PrecioBE       DOUBLE,
  CostoTotal     DOUBLE,
  MargenSinCom   DOUBLE,
  MargenConCom   DOUBLE,
  PRIMARY KEY (Empresa, Nro_Evaluacion, Linea)
) ENGINE=InnoDB;

CREATE TABLE enc_cotizacion (
  Empresa        VARCHAR(3) NOT NULL,
  Nro_Cotizacion DOUBLE     NOT NULL,
  Fecha          DATETIME,
  Cliente        INT,
  Vendedor       INT,
  Estado         INT,
  Observaciones  LONGTEXT,
  PRIMARY KEY (Empresa, Nro_Cotizacion)
) ENGINE=InnoDB;

CREATE TABLE det_cotizacion (
  Empresa        VARCHAR(3) NOT NULL,
  Nro_Cotizacion DOUBLE     NOT NULL,
  Linea          INT        NOT NULL,
  Producto       VARCHAR(50),
  Cantidad       DOUBLE,
  PrecioUnitario DOUBLE,
  Total          DOUBLE,
  PRIMARY KEY (Empresa, Nro_Cotizacion, Linea)
) ENGINE=InnoDB;

-- ============================================================
-- TABLAS TEMPORALES (tmp_*) — volátiles, igual que producción
-- ============================================================

CREATE TABLE tmp_balancecompro (
  Empresa  VARCHAR(3),
  Cuenta   DOUBLE,
  Nombre   VARCHAR(50),
  Debe     DOUBLE,
  Haber    DOUBLE,
  Equipo   VARCHAR(30)
) ENGINE=InnoDB;

CREATE TABLE tmp_balancegeneral (
  Empresa    VARCHAR(3),
  Tipo       VARCHAR(20),
  Concepto   VARCHAR(100),
  Monto      DOUBLE,
  Equipo     VARCHAR(30)
) ENGINE=InnoDB;

CREATE TABLE tmp_ctaficha (
  Empresa    VARCHAR(3),
  Cuenta     DOUBLE,
  Periodo    INT,
  Debe       DOUBLE,
  Haber      DOUBLE,
  Equipo     VARCHAR(30)
) ENGINE=InnoDB;

CREATE TABLE tmp_documento (
  Empresa        VARCHAR(3),
  Tipo_Documento VARCHAR(10),
  Folio          DOUBLE,
  Linea          INT,
  Descripcion    VARCHAR(255),
  Cantidad       DOUBLE,
  Precio         DOUBLE,
  Equipo         VARCHAR(30)
) ENGINE=InnoDB;

CREATE TABLE tmp_libromayor (
  Empresa   VARCHAR(3),
  Cuenta    DOUBLE,
  Fecha     DATETIME,
  Glosa     VARCHAR(100),
  Debe      DOUBLE,
  Haber     DOUBLE,
  Saldo     DOUBLE,
  Equipo    VARCHAR(30)
) ENGINE=InnoDB;

CREATE TABLE tmp_saldodocto (
  Empresa        VARCHAR(3),
  Tipo_Documento VARCHAR(10),
  NroDocumento   DOUBLE,
  Saldo          DOUBLE,
  Equipo         VARCHAR(30)
) ENGINE=InnoDB;

CREATE TABLE tmp_saldos (
  Empresa  VARCHAR(3),
  Producto VARCHAR(20),
  Bodega   VARCHAR(20),
  Stock    DOUBLE,
  Equipo   VARCHAR(30)
) ENGINE=InnoDB;

-- ============================================================
-- TABLAS ADICIONALES (del listado tables_list.csv)
-- ============================================================

CREATE TABLE tipopagos (
  EMPRESA     VARCHAR(3)  NOT NULL,
  CODIGO      INT         NOT NULL,
  DESCRIPCION VARCHAR(50),
  PRIMARY KEY (EMPRESA, CODIGO)
) ENGINE=InnoDB;

CREATE TABLE defcentracompra (
  Empresa   VARCHAR(3)  NOT NULL,
  Codigo    VARCHAR(20) NOT NULL,
  Descripcion VARCHAR(50),
  PRIMARY KEY (Empresa, Codigo)
) ENGINE=InnoDB;

CREATE TABLE defcentravta (
  Empresa   VARCHAR(3)  NOT NULL,
  Codigo    VARCHAR(20) NOT NULL,
  Descripcion VARCHAR(50),
  PRIMARY KEY (Empresa, Codigo)
) ENGINE=InnoDB;

CREATE TABLE desrec_compras (
  Empresa    VARCHAR(3) NOT NULL,
  Nro_Compra DOUBLE     NOT NULL,
  Item       INT        NOT NULL,
  Tipo       VARCHAR(20),
  Valor      DOUBLE,
  PRIMARY KEY (Empresa, Nro_Compra, Item)
) ENGINE=InnoDB;

CREATE TABLE enc_compras (
  Empresa         VARCHAR(3) NOT NULL,
  Nro_Compra      DOUBLE     NOT NULL,
  Fecha           DATETIME,
  Rut_Proveedor   INT,
  Total_Documento DOUBLE,
  Estado          INT,
  PRIMARY KEY (Empresa, Nro_Compra)
) ENGINE=InnoDB;

CREATE TABLE det_compras (
  Empresa         VARCHAR(3) NOT NULL,
  Nro_Compra      DOUBLE     NOT NULL,
  Item            INT        NOT NULL,
  Producto        VARCHAR(50),
  Cantidad        DOUBLE,
  Precio_Unitario DOUBLE,
  Total           DOUBLE,
  PRIMARY KEY (Empresa, Nro_Compra, Item)
) ENGINE=InnoDB;

CREATE TABLE pago_compras (
  Empresa    VARCHAR(3) NOT NULL,
  Nro_Compra DOUBLE     NOT NULL,
  Codigo     INT        NOT NULL,
  Monto      DOUBLE,
  Fecha      DATETIME,
  PRIMARY KEY (Empresa, Nro_Compra, Codigo)
) ENGINE=InnoDB;

CREATE TABLE enc_nvtas (
  Empresa    VARCHAR(3) NOT NULL,
  Nro_Nvta   DOUBLE     NOT NULL,
  Fecha      DATETIME,
  Cliente    INT,
  Estado     INT,
  Total      DOUBLE,
  PRIMARY KEY (Empresa, Nro_Nvta)
) ENGINE=InnoDB;

CREATE TABLE det_nvtas (
  Empresa    VARCHAR(3) NOT NULL,
  Nro_Nvta   DOUBLE     NOT NULL,
  Item       INT        NOT NULL,
  Producto   VARCHAR(50),
  Cantidad   DOUBLE,
  Precio     DOUBLE,
  Total      DOUBLE,
  PRIMARY KEY (Empresa, Nro_Nvta, Item)
) ENGINE=InnoDB;

CREATE TABLE desrec_nvtas (
  Empresa  VARCHAR(3) NOT NULL,
  Nro_Nvta DOUBLE     NOT NULL,
  Item     INT        NOT NULL,
  Tipo     VARCHAR(20),
  Valor    DOUBLE,
  PRIMARY KEY (Empresa, Nro_Nvta, Item)
) ENGINE=InnoDB;

CREATE TABLE pago_nvtas (
  Empresa  VARCHAR(3) NOT NULL,
  Nro_Nvta DOUBLE     NOT NULL,
  Codigo   INT        NOT NULL,
  Monto    DOUBLE,
  Fecha    DATETIME,
  PRIMARY KEY (Empresa, Nro_Nvta, Codigo)
) ENGINE=InnoDB;

SELECT 'PARTE 3 OK: Fases E-I + tablas extra + tmp_* creadas.' AS status;
