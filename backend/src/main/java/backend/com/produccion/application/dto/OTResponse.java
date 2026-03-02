package backend.com.produccion.application.dto;

import lombok.Data;

@Data
public class OTResponse {
    private Long id;
    private Long numero;
    private Long notaVentaId;
    private Integer nroItem;
    private String tipo;
    private String estado;
    private String observaciones;
}
