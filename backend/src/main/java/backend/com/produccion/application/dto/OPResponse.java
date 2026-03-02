package backend.com.produccion.application.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class OPResponse {
    private Long id;
    private Long numero;
    private Long notaVentaId;
    private String estado;
    private LocalDate fechaInicio;
    private LocalDate fechaEntregaProgramada;
    private String observaciones;
    private List<OPItemResponse> items;

    @Data
    public static class OPItemResponse {
        private Long id;
        private Long productoId;
        private Integer nroItem;
        private String modelo;
        private Integer cantidad;
    }
}
