-- ============================================================
-- SISPRO MIGRATION TEST — PARTE 4: Datos de prueba mínimos
-- Requiere haber ejecutado part1, part2 y part3 primero
-- ============================================================
USE bdsispro_test;

-- ============================================================
-- A. Empresas (5 empresas — refleja producción)
-- ============================================================
INSERT INTO empresas (RUT_EMPRESA, DIGITO, NOMBRE_EMPRESA, CODIGO, SIGLA, GIRO) VALUES
(12345678, '9', 'EMPRESA ANTUAN SA',   'ANT', 'ANTUAN',   'FABRICACION TEXTIL'),
(23456789, '0', 'FILIAL VITACURA',     'VIT', 'VITACURA', 'COMERCIO AL POR MAYOR'),
(34567890, '1', 'FILIAL LINARES',      'LIN', 'LINARES',  'FABRICACION TEXTIL'),
(45678901, '2', 'FILIAL SANTIAGO',     'SAN', 'SANTIAGO',  'COMERCIO AL POR MAYOR'),
(56789012, '3', 'FILIAL TEMUCO',       'TEM', 'TEMUCO',   'FABRICACION TEXTIL');

-- ============================================================
-- B. Parámetros (catálogos genéricos)
-- ============================================================
INSERT INTO parametros (EMPRESA, TIPO, CODIGO, DESCRIPCION, ATRIBUTO1) VALUES
('ANT', 'Modelos',  1, 'POLERA BASICA',     'PB'),
('ANT', 'Modelos',  2, 'PANTALON CARGO',    'PC'),
('ANT', 'Telas',    1, 'JERSEY 20/1',       'JR'),
('ANT', 'Telas',    2, 'DENIM 12OZ',        'DN'),
('ANT', 'Generos',  1, 'MASCULINO',         'M'),
('ANT', 'Generos',  2, 'FEMENINO',          'F'),
('ANT', 'Generos',  3, 'UNISEX',            'U'),
('ANT', 'Colores',  1, 'BLANCO',            '#FFFFFF'),
('ANT', 'Colores',  2, 'NEGRO',             '#000000'),
('ANT', 'Colores',  3, 'AZUL MARINO',       '#001F5B'),
('ANT', 'Aplicaciones', 1, 'SERIGRAFIA',    'SR'),
('ANT', 'Aplicaciones', 2, 'BORDADO',       'BR'),
('ANT', 'Composiciones', 1, '100% ALGODON', 'CO'),
('ANT', 'Composiciones', 2, '50/50 POLY',   'PP'),
('ANT', 'Monedas',  1, 'PESO CHILENO',      'CLP'),
('ANT', 'Monedas',  2, 'DOLAR USA',         'USD');

-- ============================================================
-- C. Configuración del sistema
-- ============================================================
INSERT INTO configuracion_sistema
  (Empresa, Sistema, Por_Iva, Ann_Activo, ControlaInv, CostoFlete, CostoHilos, CostoEtiqueta, CostosinGratif, CostoconGratif)
VALUES
  ('ANT', 'COSTOS', 19, 2026, 1, 1500, 200, 300, 580000, 750000);

-- ============================================================
-- D. Locales y centros de costo
-- ============================================================
INSERT INTO locales (EMPRESA, CODIGO, DESCRIPCION, CIUDAD) VALUES
  ('ANT', 1, 'CASA MATRIZ', 'SANTIAGO');

INSERT INTO cencosto (EMPRESA, CODIGO, DESCRIPCION) VALUES
  ('ANT', 100, 'PRODUCCION GENERAL'),
  ('ANT', 200, 'COMPRAS EXTERNAS'),
  ('ANT', 300, 'ADMINISTRACION');

-- ============================================================
-- E. Artículos
-- ============================================================
INSERT INTO articulos
  (EMPRESA, Codigo_Articulo, Descripcion, Unidad_Medida, Precio_Venta, Costo, Activo)
VALUES
  ('ANT', 'POL-001', 'POLERA JERSEY HOMBRE T.M',   'UN', 8500,  3200, 1),
  ('ANT', 'PAN-001', 'PANTALON CARGO HOMBRE T.30', 'UN', 22000, 9800, 1),
  ('ANT', 'ACC-001', 'BOTON METALICO 18MM',         'GR', 180,   85,  1);

-- ============================================================
-- F. Clientes y Proveedores
-- ============================================================
INSERT INTO clientes
  (EMPRESA, RUT_CLIENTE, DV_RUT, RAZON_SOCIAL, GIRO, CODIGO_VENDEDOR, CONDICION_PAGO)
VALUES
  ('ANT', 76123456, '7', 'COMERCIAL EL GATO LTDA', 'COMERCIO', 1, 30),
  ('ANT', 76234567, '8', 'DISTRIBUIDORA NORTE SA',  'COMERCIO', 2, 60);

INSERT INTO proveedores
  (EMPRESA, RUT_PROVEEDOR, DV_RUT, RAZON_SOCIAL, GIRO, CONDICION_PAGO)
VALUES
  ('ANT', 87654321, '0', 'TEXTILES DEL PACIFICO SA',    'FABRICACION TEXTIL', 30),
  ('ANT', 98765432, '1', 'ACCESORIOS Y BOTONES LTDA',   'COMERCIO ART. TEXTIL', 15);

-- ============================================================
-- G. Usuarios (sin hash — igual que producción, NO migrar clave)
-- ============================================================
INSERT INTO usuarios (EMPRESA, RUT_USUARIO, Sistema, NOMBRE_USUARIO, CLAVE_USUARIO, Vigencia)
VALUES
  ('ANT', '12345678-9', 'COSTOS', 'ADMINISTRADOR', 'admin123', 1),
  ('ANT', '23456789-0', 'COSTOS', 'JEFE COMPRAS',  'compras1', 1),
  ('ANT', '34567890-1', 'COSTOS', 'OPERADOR 01',   'op001',    1);

-- ============================================================
-- H. Solicitudes de costo (Fase C — tablas core)
-- ============================================================
INSERT INTO enc_solicosto
  (Empresa, Nro_Solicitud, Fecha, Cliente, Vendedor, Estado, Articulo,
   Cantidad, Precio, CostoTelas, CostoAccesorios, CostoTotal, Fec_Solicitud,
   Modelo, Tela, Genero, Color, CCosto)
VALUES
  ('ANT', 10001, '2026-01-10', 76123456, 1, 2, 'POL-001',
   1000, 8500, 3200, 400, 3800, '2026-01-10', 'POLERA BASICA', 'JERSEY 20/1', 'MASCULINO', 'BLANCO', 100),
  ('ANT', 10002, '2026-01-15', 76234567, 2, 1, 'PAN-001',
   500, 22000, 9800, 600, 10800, '2026-01-15', 'PANTALON CARGO', 'DENIM 12OZ',  'MASCULINO', 'AZUL MARINO', 100);

INSERT INTO det_solicosto
  (Empresa, Nro_Solicitud, Linea, Telas, Provee_Telas, Precio_Unit, Consumo, Uni_Med, Costo)
VALUES
  ('ANT', 10001, 1, 'JERSEY 20/1', 87654321, 1800, 1.75, 'MT', 3150),
  ('ANT', 10001, 2, 'ELASTICO 2CM', 87654321, 120,  0.40, 'MT', 48),
  ('ANT', 10002, 1, 'DENIM 12OZ',   87654321, 4200, 2.30, 'MT', 9660),
  ('ANT', 10002, 2, 'HILO 20/2',    87654321, 350,  0.40, 'MT', 140);

INSERT INTO acc_solicosto
  (Empresa, Nro_Solicitud, Linea, Accesorio, Proveedor, Precio, Consumo, Unimed, Costo, Codigo)
VALUES
  ('ANT', 10001, 1, 'BOTON METALICO', 98765432, 85, 2, 'UN', 170, 'ACC-001'),
  ('ANT', 10001, 2, 'ETIQUETA MARCA', 98765432, 45, 1, 'UN', 45,  'ACC-002'),
  ('ANT', 10002, 1, 'CIERRE YKK',     98765432, 380, 1, 'UN', 380, 'ACC-003'),
  ('ANT', 10002, 2, 'REMACHE DENIM',  98765432, 65, 6, 'UN', 390, 'ACC-004');

INSERT INTO tal_solicosto (Empresa, Nro_Solicitud, Linea, Talla, Cantidad) VALUES
  ('ANT', 10001, 1, 'S',  200),
  ('ANT', 10001, 2, 'M',  400),
  ('ANT', 10001, 3, 'L',  300),
  ('ANT', 10001, 4, 'XL', 100),
  ('ANT', 10002, 1, '30', 150),
  ('ANT', 10002, 2, '32', 200),
  ('ANT', 10002, 3, '34', 150);

-- ============================================================
-- I. Orden de Compra (Fases D — tablas críticas)
-- ============================================================
INSERT INTO enc_ocompra
  (EMPRESA, Nro_OCompra, Fecha_Documento, Periodo, Rut_Proveedor,
   Moneda, Condicion_pago, Sub_Total, Total_Iva, Total_Documento)
VALUES
  ('ANT', 20001, '2026-01-20', 202601, 87654321,
   'PESO CHILENO', '30', 4760000, 904400, 5664400);

INSERT INTO det_ocompra
  (Empresa, Nro_OCompra, Item, Codigo_Producto, Cantidad, Precio_Unitario, Total_Producto, Modelo, Tela, Genero)
VALUES
  ('ANT', 20001, 1, 'POL-001', 1000, 3200, 3200000, 'POLERA BASICA', 'JERSEY 20/1', 'MASCULINO'),
  ('ANT', 20001, 2, 'ACC-001', 2000, 85,   170000,  'N/A', 'N/A', 'N/A');

INSERT INTO tal_ocompra
  (Empresa, Nro_OCompra, Linea,
   Talla01, Talla02, Talla03, Talla04,
   Talla05, Talla06, Talla07, Talla08,
   Talla09, Talla10, Talla11, Talla12)
VALUES
  ('ANT', 20001, 1, 200, 400, 300, 100, 0, 0, 0, 0, 0, 0, 0, 0);

INSERT INTO pago_ocompra (Empresa, Nro_OCompra, Codigo, Fecha, Vencimiento, Valor)
VALUES
  ('ANT', 20001, 1, '2026-01-20', '2026-02-20', 5664400);

-- ============================================================
-- VERIFICACIÓN FINAL (ejecutar para confirmar estructura)
-- ============================================================
SELECT
  TABLE_NAME           AS tabla,
  TABLE_ROWS           AS filas_estimadas,
  TABLE_COMMENT        AS comentario
FROM information_schema.TABLES
WHERE TABLE_SCHEMA = 'bdsispro_test'
ORDER BY TABLE_NAME;

-- Conteos críticos de datos de prueba
SELECT 'empresas'       AS tabla, COUNT(*) AS registros FROM empresas
UNION ALL
SELECT 'parametros',       COUNT(*) FROM parametros
UNION ALL
SELECT 'articulos',        COUNT(*) FROM articulos
UNION ALL
SELECT 'clientes',         COUNT(*) FROM clientes
UNION ALL
SELECT 'proveedores',      COUNT(*) FROM proveedores
UNION ALL
SELECT 'usuarios',         COUNT(*) FROM usuarios
UNION ALL
SELECT 'enc_solicosto',    COUNT(*) FROM enc_solicosto
UNION ALL
SELECT 'det_solicosto',    COUNT(*) FROM det_solicosto
UNION ALL
SELECT 'acc_solicosto',    COUNT(*) FROM acc_solicosto
UNION ALL
SELECT 'tal_solicosto',    COUNT(*) FROM tal_solicosto
UNION ALL
SELECT 'enc_ocompra',      COUNT(*) FROM enc_ocompra
UNION ALL
SELECT 'det_ocompra',      COUNT(*) FROM det_ocompra
UNION ALL
SELECT 'tal_ocompra',      COUNT(*) FROM tal_ocompra
UNION ALL
SELECT 'pago_ocompra',     COUNT(*) FROM pago_ocompra;

SELECT 'PARTE 4 OK: Datos de prueba insertados y verificacion completada.' AS status;
