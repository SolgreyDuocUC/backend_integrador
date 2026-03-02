package backend.com.adquisiciones.application.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class SCItemDTO {
    private Long productoId;
    private String descripcionProducto;
    private Integer cantidad;
    private BigDecimal precioEstimadoUnitario;
    private String moneda;
    private String tipo;
}
