package backend.com.comercial.service.dto;

import lombok.Data;
import java.util.List;

@Data
public class CrearEVNCommand {
    private Long numero;
    private Long clienteId;
    private Long vendedorId;
    private Long costeoId;
    private Long solicitudCotizacionId;
    private List<ItemEVNDTO> items;
}
