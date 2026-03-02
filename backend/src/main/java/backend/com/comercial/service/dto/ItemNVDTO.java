package backend.com.comercial.service.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class ItemNVDTO {
    private Long productoId;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private String modelo;
    private String color;
    private String talla;
    private String genero;
    private Boolean llevaLogo;
    private List<TallaDTO> tallas;
    private String itemType; // OP, SC, o SCI

    @Data
    public static class TallaDTO {
        private String talla;
        private Integer cantidad;
    }
}
