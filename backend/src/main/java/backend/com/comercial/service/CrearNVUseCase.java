package backend.com.comercial.service;

import backend.com.adquisiciones.application.dto.CrearSCCommand;
import backend.com.adquisiciones.application.dto.SCItemDTO;
import backend.com.adquisiciones.application.usecase.CrearSCUseCase;
import backend.com.produccion.application.usecase.CrearOrdenProduccionUseCase;
import backend.com.comercial.service.dto.CrearNVCommand;
import backend.com.comercial.service.dto.NVResponse;
import backend.com.comercial.domain.model.ItemNV;
import backend.com.comercial.domain.model.ItemNVTalla;
import backend.com.comercial.domain.model.NotaVenta;
import backend.com.comercial.domain.ports.NotaVentaRepository;
import backend.com.shared.valueobjects.DocumentNumber;
import backend.com.shared.valueobjects.Money;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CrearNVUseCase {

    private final NotaVentaRepository nvRepository;
    private final CrearSCUseCase crearSCUseCase;
    private final CrearOrdenProduccionUseCase crearOPUseCase;

    @Transactional
    public NVResponse ejecutar(CrearNVCommand command) {
        NotaVenta nv = NotaVenta.crear(
                new DocumentNumber(command.getNumero()),
                command.getEvaluacionNegocioId(),
                command.getClienteId(),
                command.getVendedorId(),
                command.getEsKit(),
                command.getFechaEntregaEstimada());

        List<SCItemDTO> itemsParaAdquisicion = new ArrayList<>();

        if (command.getItems() != null) {
            for (int i = 0; i < command.getItems().size(); i++) {
                var dto = command.getItems().get(i);

                List<ItemNVTalla> tallas = (dto.getTallas() == null) ? null
                        : dto.getTallas().stream()
                                .map(t -> new ItemNVTalla(t.getTalla(), t.getCantidad()))
                                .collect(Collectors.toList());

                ItemNV item = new ItemNV(
                        i + 1,
                        dto.getProductoId(),
                        dto.getModelo(),
                        null, null, // tela, composicion
                        dto.getColor(),
                        dto.getTalla(),
                        dto.getGenero(),
                        null, // codigo
                        null, // proveedor
                        dto.getLlevaLogo(),
                        dto.getItemType() != null ? dto.getItemType() : "OP",
                        null, // isPersonalized
                        null, // detalleOt
                        null, // logoDetalle
                        dto.getCantidad(),
                        new Money(dto.getPrecioUnitario(), "CLP"),
                        tallas);
                nv.addItem(item);

                // Check if this item generates a Solicitud de Compra (SC o SCI)
                if ("SC".equals(dto.getItemType()) || "SCI".equals(dto.getItemType())) {
                    SCItemDTO scItem = new SCItemDTO();
                    scItem.setProductoId(dto.getProductoId());
                    scItem.setDescripcionProducto(dto.getModelo() + " (" + dto.getColor() + ")");
                    scItem.setCantidad(dto.getCantidad());
                    scItem.setPrecioEstimadoUnitario(dto.getPrecioUnitario());
                    scItem.setMoneda("CLP");
                    scItem.setTipo(dto.getItemType());
                    itemsParaAdquisicion.add(scItem);
                }
            }
        }

        NotaVenta guardada = nvRepository.save(nv);

        // Automaticaly generate SolicitudCompra if items are marked
        if (!itemsParaAdquisicion.isEmpty()) {
            CrearSCCommand scCommand = new CrearSCCommand();
            scCommand.setNotaVentaId(guardada.getId());
            scCommand.setTenantId(1L); // Standard for now
            scCommand.setItems(itemsParaAdquisicion);
            crearSCUseCase.ejecutar(scCommand);
        }

        // Automaticaly generate OrdenProduccion
        crearOPUseCase.execute(guardada);

        return NVResponse.fromDomain(guardada);
    }
}
