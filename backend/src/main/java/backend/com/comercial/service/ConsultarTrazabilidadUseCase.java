package backend.com.comercial.service;

import backend.com.adquisiciones.domain.model.SolicitudCompra;
import backend.com.adquisiciones.domain.ports.SolicitudCompraRepository;
import backend.com.comercial.domain.model.NotaVenta;
import backend.com.comercial.domain.ports.EvaluacionNegocioRepository;
import backend.com.comercial.domain.ports.NotaVentaRepository;
import backend.com.comercial.domain.ports.SolicitudCostosRepository;
import backend.com.produccion.domain.model.OrdenProduccion;
import backend.com.produccion.domain.ports.CosteoRepository;
import backend.com.produccion.domain.ports.OrdenProduccionRepository;
import backend.com.shared.dto.DocumentTraceDTO;
import backend.com.shared.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultarTrazabilidadUseCase {

    private final NotaVentaRepository nvRepository;
    private final EvaluacionNegocioRepository evnRepository;
    private final CosteoRepository costeoRepository;
    private final SolicitudCostosRepository scosRepository;
    private final OrdenProduccionRepository opRepository;
    private final SolicitudCompraRepository scRepository;

    @Transactional(readOnly = true)
    public List<DocumentTraceDTO> ejecutar(Long notaVentaId) {
        NotaVenta nv = nvRepository.findById(notaVentaId)
                .orElseThrow(() -> new EntityNotFoundException("Nota de Venta no encontrada: " + notaVentaId));

        List<DocumentTraceDTO> trazabilidad = new ArrayList<>();

        // 1. Reconstruir hacia atrás (Ancestros)
        if (nv.getEvaluacionNegocioId() != null) {
            evnRepository.findById(nv.getEvaluacionNegocioId()).ifPresent(evn -> {

                // Buscar Costeo si existe
                if (evn.getCosteoId() != null) {
                    costeoRepository.findById(evn.getCosteoId()).ifPresent(costeo -> {

                        // Buscar SCOS si existe
                        if (costeo.getSolicitudCostosId() != null) {
                            scosRepository.findById(costeo.getSolicitudCostosId()).ifPresent(scos -> {
                                trazabilidad.add(mapToDto("Solicitud Costos", scos.getId(),
                                        scos.getNumero().getValue().toString(), scos.getEstado(), scos.getFecha()));
                            });
                        }

                        trazabilidad.add(mapToDto("Costeo", costeo.getId(), costeo.getNumero().getValue().toString(),
                                "COMPLETO", null));
                    });
                }

                trazabilidad.add(mapToDto("Evaluación Negocio", evn.getId(), evn.getNumero().getValue().toString(),
                        evn.getEstado().name(), evn.getFechaEvaluacion()));
            });
        }

        // 2. Nota de Venta (Eje Central)
        trazabilidad.add(mapToDto("Nota Venta", nv.getId(), nv.getNumero().getValue().toString(), nv.getEstado().name(),
                nv.getFechaEmision()));

        // 3. Reconstruir hacia adelante (Sucesores)

        // Buscar OPs asociadas
        List<OrdenProduccion> ops = opRepository.findByNotaVentaId(notaVentaId);
        for (OrdenProduccion op : ops) {
            trazabilidad.add(mapToDto("Orden Producción", op.getId(), op.getNumero().getValue().toString(),
                    op.getEstado().name(), op.getFechaInicio()));
        }

        // Buscar SCs asociadas
        List<SolicitudCompra> scs = scRepository.findByNotaVentaId(notaVentaId);
        for (SolicitudCompra sc : scs) {
            trazabilidad.add(mapToDto("Solicitud Compra", sc.getId(), sc.getNumero().getValue().toString(),
                    sc.getEstado().name(), sc.getFechaEmision()));
        }

        return trazabilidad;
    }

    private DocumentTraceDTO mapToDto(String tipo, Long id, String numero, String estado, java.time.LocalDate fecha) {
        return DocumentTraceDTO.builder()
                .tipoDocumento(tipo)
                .id(id)
                .numero(numero)
                .estado(estado)
                .fecha(fecha)
                .build();
    }
}
