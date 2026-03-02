package backend.com.comercial.service.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ItemEVNDTO {
    private Long productoId;
    private Long proveedorId;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal costoUnitario;
    private String tipoItem;
    private String referenciaTexto;
}
