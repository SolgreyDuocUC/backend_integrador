package backend.com.comercial.service;

import backend.com.comercial.service.dto.CrearEVNCommand;
import backend.com.comercial.service.dto.EVNResponse;
import backend.com.comercial.domain.model.EvaluacionNegocio;
import backend.com.comercial.domain.model.ItemEVN;
import backend.com.comercial.domain.ports.EvaluacionNegocioRepository;
import backend.com.shared.valueobjects.DocumentNumber;
import backend.com.shared.valueobjects.Money;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CrearEVNUseCase {

    private final EvaluacionNegocioRepository evnRepository;

    @Transactional
    public EVNResponse ejecutar(CrearEVNCommand command) {
        EvaluacionNegocio evn = EvaluacionNegocio.crear(
                new DocumentNumber(command.getNumero()),
                command.getClienteId(),
                command.getVendedorId(),
                command.getCosteoId(),
                command.getSolicitudCotizacionId());

        if (command.getItems() != null) {
            command.getItems().forEach(dto -> {
                ItemEVN item = new ItemEVN(
                        dto.getProductoId(),
                        dto.getProveedorId(),
                        dto.getCantidad(),
                        new Money(dto.getPrecioUnitario(), "CLP"),
                        new Money(dto.getCostoUnitario(), "CLP"),
                        dto.getTipoItem(),
                        dto.getReferenciaTexto(),
                        BigDecimal.ZERO, // Margen se calcula en el Aggregate
                        new Money(dto.getPrecioUnitario().multiply(new BigDecimal(dto.getCantidad())), "CLP"));
                evn.addItem(item);
            });
        }

        EvaluacionNegocio guardada = evnRepository.save(evn);
        return EVNResponse.fromDomain(guardada);
    }
}
