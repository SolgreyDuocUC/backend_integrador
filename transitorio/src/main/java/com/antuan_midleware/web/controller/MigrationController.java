package com.antuan_midleware.web.controller;

import com.antuan_midleware.migration.pipeline.MigrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/migration")
@RequiredArgsConstructor
public class MigrationController {

    private final MigrationService migrationService;

    @PostMapping("/empresas")
    public ResponseEntity<String> migrateEmpresas() {
        try {
            migrationService.migrateEmpresas();
            return ResponseEntity.ok("Empresa migration executed successfully.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error during Empresa migration: " + e.getMessage());
        }
    }

    @PostMapping("/clientes")
    public ResponseEntity<String> migrateClientes() {
        try {
            migrationService.migrateClientes();
            return ResponseEntity.ok("Cliente migration executed successfully.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error during Cliente migration: " + e.getMessage());
        }
    }

    @PostMapping("/proveedores")
    public ResponseEntity<String> migrateProveedores() {
        try {
            migrationService.migrateProveedores();
            return ResponseEntity.ok("Proveedor migration executed successfully.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error during Proveedor migration: " + e.getMessage());
        }
    }

    @PostMapping("/productos")
    public ResponseEntity<String> migrateProductos() {
        try {
            migrationService.migrateProductos();
            return ResponseEntity.ok("Producto migration executed successfully.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error during Producto migration: " + e.getMessage());
        }
    }

    @PostMapping("/produccion")
    public ResponseEntity<String> migrateProduccion() {
        try {
            migrationService.migrateProduccion();
            return ResponseEntity.ok("Production migration executed successfully.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error during Production migration: " + e.getMessage());
        }
    }

    @PostMapping("/master-data")
    public ResponseEntity<String> migrateMasterData() {
        try {
            migrationService.migrateMasterData();
            return ResponseEntity.ok("Master Data (Phase D) migration executed successfully.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error during Master Data migration: " + e.getMessage());
        }
    }
}
