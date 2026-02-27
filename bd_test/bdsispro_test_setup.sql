-- ============================================================
-- SISPRO MIGRATION TEST — RUNNER PRINCIPAL
-- Ejecuta los 4 scripts en orden para crear bdsispro_test
--
-- USO (desde línea de comandos MySQL):
--   mysql -u root -p < bdsispro_test_setup.sql
--
-- USO (desde MySQL Workbench o HeidiSQL):
--   Abrir este archivo y ejecutar con F5
--
-- PREREQUISITO: MySQL local o servidor de prueba distinto a 192.168.2.3
-- NO TOCA bdsispro NI NINGÚN ARCHIVO DE MenuCosto
-- ============================================================

-- Paso 1: Estructura base + Fases A y B (maestros)
SOURCE bdsispro_test_part1.sql;

-- Paso 2: Fases C y D (core MenuCosto: costeo y órdenes)
SOURCE bdsispro_test_part2.sql;

-- Paso 3: Fases E-I + tablas extra + temporales
SOURCE bdsispro_test_part3.sql;

-- Paso 4: Datos de prueba mínimos + verificación
SOURCE bdsispro_test_part4.sql;

SELECT '================================================' AS '';
SELECT 'bdsispro_test LISTA PARA PRUEBAS DE MIGRACIÓN'  AS '';
SELECT '================================================' AS '';
