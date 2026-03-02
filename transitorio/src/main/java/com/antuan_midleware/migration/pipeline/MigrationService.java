package com.antuan_midleware.migration.pipeline;

import com.antuan_midleware.core.model.EncOtrabajo;
import com.antuan_midleware.core.service.*;
import com.antuan_midleware.empresas.model.Empresa;
import com.antuan_midleware.migration.transformer.*;
import com.antuan_midleware.target.model.MigrationLog;
import com.antuan_midleware.target.repository.TargetEmpresaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MigrationService {

    private final EmpresaService legacyEmpresaService;
    private final EmpresaTransformer empresaTransformer;
    private final TargetEmpresaRepository targetEmpresaRepository;

    private final ClientesService legacyClientesService;
    private final ClienteTransformer clienteTransformer;
    private final com.antuan_midleware.target.repository.ClienteRepository targetClienteRepository;

    private final ProveedoresService legacyProveedoresService;
    private final ProveedorTransformer proveedorTransformer;
    private final com.antuan_midleware.target.repository.ProveedorRepository targetProveedorRepository;

    private final ArticulosService legacyArticulosService;
    private final ProductoTransformer productoTransformer;
    private final com.antuan_midleware.target.repository.ProductoRepository targetProductoRepository;

    private final EncOtrabajoService legacyEncOtrabajoService;
    private final DetOtrabajoService legacyDetOtrabajoService;
    private final TalOtrabajoService legacyTalOtrabajoService;
    private final OrdenProduccionTransformer ordenProduccionTransformer;
    private final com.antuan_midleware.target.repository.OrdenProduccionRepository targetOrdenProduccionRepository;

    private final com.antuan_midleware.target.repository.MigrationLogRepository migrationLogRepository;
    private final com.antuan_midleware.migration.validation.MigrationValidator validator;

    // Phase D Dependencies
    private final BancosService legacyBancosService;
    private final com.antuan_midleware.target.repository.BancoRepository targetBancoRepository;
    private final BancoTransformer bancoTransformer;

    private final CencostoService legacyCencostoService;
    private final com.antuan_midleware.target.repository.CentroCostoRepository targetCenCostoRepository;
    private final CentroCostoTransformer cenCostoTransformer;

    private final UsuariosService legacyUsuariosService;
    private final com.antuan_midleware.target.repository.VendedorRepository targetVendedorRepository;
    private final VendedorTransformer vendedorTransformer;

    private final com.antuan_midleware.target.repository.RegionRepository targetRegionRepository;
    private final com.antuan_midleware.target.repository.ComunaRepository targetComunaRepository;

    @Transactional(transactionManager = "erpTransactionManager")
    public void migrateEmpresas() {
        MigrationLog auditLog = new MigrationLog("Empresa");
        try {
            log.info("Starting Empresa migration...");
            List<Empresa> legacyEmpresas = legacyEmpresaService.findAll();

            List<com.antuan_midleware.target.model.Empresa> targetEmpresas = legacyEmpresas.stream()
                    .map(empresaTransformer::transform)
                    .filter(e -> validator.validateRut(e.getRut()).isEmpty())
                    .collect(Collectors.toList());

            targetEmpresaRepository.saveAll(targetEmpresas);

            recordSuccess(auditLog, legacyEmpresas.size(), targetEmpresas.size());
        } catch (Exception e) {
            recordFailure(auditLog, e);
            throw e;
        }
    }

    @Transactional(transactionManager = "erpTransactionManager")
    public void migrateClientes() {
        MigrationLog auditLog = new MigrationLog("Cliente");
        try {
            log.info("Starting Cliente migration...");
            List<com.antuan_midleware.core.model.Clientes> legacyClientes = legacyClientesService.findAll();

            List<com.antuan_midleware.target.model.Cliente> targetClientes = legacyClientes.stream()
                    .map(clienteTransformer::transform)
                    .filter(c -> validator.validateRut(c.getRun()).isEmpty())
                    .collect(Collectors.toList());

            targetClienteRepository.saveAll(targetClientes);
            recordSuccess(auditLog, legacyClientes.size(), targetClientes.size());
        } catch (Exception e) {
            recordFailure(auditLog, e);
            throw e;
        }
    }

    @Transactional(transactionManager = "erpTransactionManager")
    public void migrateProveedores() {
        MigrationLog auditLog = new MigrationLog("Proveedor");
        try {
            log.info("Starting Proveedor migration...");
            List<com.antuan_midleware.core.model.Proveedores> legacyProveedores = legacyProveedoresService.findAll();

            List<com.antuan_midleware.target.model.Proveedor> targetProveedores = legacyProveedores.stream()
                    .map(proveedorTransformer::transform)
                    .filter(p -> validator.validateRut(p.getRut()).isEmpty())
                    .collect(Collectors.toList());

            targetProveedorRepository.saveAll(targetProveedores);
            recordSuccess(auditLog, legacyProveedores.size(), targetProveedores.size());
        } catch (Exception e) {
            recordFailure(auditLog, e);
            throw e;
        }
    }

    @Transactional(transactionManager = "erpTransactionManager")
    public void migrateProductos() {
        MigrationLog auditLog = new MigrationLog("Producto");
        try {
            log.info("Starting Producto migration...");
            List<com.antuan_midleware.core.model.Articulos> legacyArticulos = legacyArticulosService.findAll();

            List<com.antuan_midleware.target.model.Producto> targetProductos = legacyArticulos.stream()
                    .map(productoTransformer::transform)
                    .filter(p -> validator.validateRequired("Codigo", p.getCodigo()).isEmpty())
                    .collect(Collectors.toList());

            targetProductoRepository.saveAll(targetProductos);
            recordSuccess(auditLog, legacyArticulos.size(), targetProductos.size());
        } catch (Exception e) {
            recordFailure(auditLog, e);
            throw e;
        }
    }

    @Transactional(transactionManager = "erpTransactionManager")
    public void migrateProduccion() {
        MigrationLog auditLog = new MigrationLog("Produccion");
        try {
            log.info("Starting Produccion (Phase C) migration...");
            List<EncOtrabajo> headers = legacyEncOtrabajoService.findAll();
            List<com.antuan_midleware.core.model.DetOtrabajo> allDetails = legacyDetOtrabajoService.findAll();
            List<com.antuan_midleware.core.model.TalOtrabajo> allSizes = legacyTalOtrabajoService.findAll();

            Map<Long, List<com.antuan_midleware.core.model.DetOtrabajo>> detailsByFolio = allDetails.stream()
                    .collect(Collectors.groupingBy(d -> d.getId().getFolio()));

            Map<String, com.antuan_midleware.core.model.TalOtrabajo> sizesMap = allSizes.stream()
                    .collect(Collectors.toMap(
                            t -> t.getId().getEmpresa() + "-" + t.getId().getFolio() + "-" + t.getId().getLinea(),
                            Function.identity(),
                            (existing, replacement) -> existing));

            List<com.antuan_midleware.target.model.OrdenProduccion> targetOrders = headers.stream()
                    .map(h -> {
                        List<com.antuan_midleware.core.model.DetOtrabajo> dets = detailsByFolio
                                .getOrDefault(h.getId().getFolio(), new ArrayList<>());
                        return ordenProduccionTransformer.transform(h, dets, sizesMap);
                    })
                    .collect(Collectors.toList());

            targetOrdenProduccionRepository.saveAll(targetOrders);
            recordSuccess(auditLog, headers.size(), targetOrders.size());
        } catch (Exception e) {
            recordFailure(auditLog, e);
            throw e;
        }
    }

    @Transactional(transactionManager = "erpTransactionManager")
    public void migrateMasterData() {
        migrateBancos();
        migrateCentrosCosto();
        migrateVendedores();
        migrateRegionesComunas();
    }

    private void migrateBancos() {
        MigrationLog auditLog = new MigrationLog("Banco (Master Data)");
        try {
            log.info("Starting Bancos (Phase D) migration...");
            List<com.antuan_midleware.core.model.Bancos> legacyData = legacyBancosService.findAll();
            List<com.antuan_midleware.target.model.Banco> targetData = legacyData.stream()
                    .map(bancoTransformer::transform)
                    .collect(Collectors.toList());
            targetBancoRepository.saveAll(targetData);
            recordSuccess(auditLog, legacyData.size(), targetData.size());
        } catch (Exception e) {
            recordFailure(auditLog, e);
        }
    }

    private void migrateCentrosCosto() {
        MigrationLog auditLog = new MigrationLog("CentroCosto (Master Data)");
        try {
            log.info("Starting Centros Costo (Phase D) migration...");
            List<com.antuan_midleware.core.model.Cencosto> legacyData = legacyCencostoService.findAll();
            List<com.antuan_midleware.target.model.CentroCosto> targetData = legacyData.stream()
                    .map(cenCostoTransformer::transform)
                    .collect(Collectors.toList());
            targetCenCostoRepository.saveAll(targetData);
            recordSuccess(auditLog, legacyData.size(), targetData.size());
        } catch (Exception e) {
            recordFailure(auditLog, e);
        }
    }

    private void migrateVendedores() {
        MigrationLog auditLog = new MigrationLog("Vendedor (Master Data)");
        try {
            log.info("Starting Vendedores (Phase D) migration...");
            List<com.antuan_midleware.core.model.Usuarios> legacyData = legacyUsuariosService.findAll();
            List<com.antuan_midleware.target.model.Vendedor> targetData = legacyData.stream()
                    .map(vendedorTransformer::transform)
                    .collect(Collectors.toList());
            targetVendedorRepository.saveAll(targetData);
            recordSuccess(auditLog, legacyData.size(), targetData.size());
        } catch (Exception e) {
            recordFailure(auditLog, e);
        }
    }

    private void migrateRegionesComunas() {
        MigrationLog auditLog = new MigrationLog("Regiones-Comunas (Master Data)");
        try {
            log.info("Starting Regiones y Comunas (Phase D) migration...");
            // Extract unique regions and comunas from client and provider master data
            // Create default Region if not exists
            com.antuan_midleware.target.model.Region rm = targetRegionRepository.findByNombre("Región Metropolitana")
                    .orElseGet(() -> {
                        com.antuan_midleware.target.model.Region r = new com.antuan_midleware.target.model.Region();
                        r.setNombre("Región Metropolitana");
                        return targetRegionRepository.save(r);
                    });

            // Extract unique comunas from Clientes
            List<String> comunasClientes = legacyClientesService.findAll().stream()
                    .map(com.antuan_midleware.core.model.Clientes::getComuna)
                    .filter(c -> c != null && !c.isBlank())
                    .distinct()
                    .collect(Collectors.toList());

            int processedComunas = 0;
            for (String nombreComuna : comunasClientes) {
                if (targetComunaRepository.findByNombre(nombreComuna).isEmpty()) {
                    com.antuan_midleware.target.model.Comuna c = new com.antuan_midleware.target.model.Comuna();
                    c.setNombre(nombreComuna);
                    c.setRegion(rm);
                    targetComunaRepository.save(c);
                    processedComunas++;
                }
            }
            recordSuccess(auditLog, comunasClientes.size(), processedComunas);
        } catch (Exception e) {
            recordFailure(auditLog, e);
        }
    }

    private void recordSuccess(MigrationLog logEntry, int total, int success) {
        logEntry.setStatus("SUCCESS");
        logEntry.setRecordsProcessed(total);
        logEntry.setRecordsSuccess(success);
        logEntry.setRecordsFailed(total - success);
        migrationLogRepository.save(logEntry);
        log.info("{} migration completed: {} success, {} failed", logEntry.getEntityName(), success, total - success);
    }

    private void recordFailure(MigrationLog logEntry, Exception e) {
        logEntry.setStatus("FAILURE");
        logEntry.setErrorMessage(e.getMessage());
        migrationLogRepository.save(logEntry);
        log.error("Error during {} migration: {}", logEntry.getEntityName(), e.getMessage());
    }
}
