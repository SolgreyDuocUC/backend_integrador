package backend.com.comercial.service.dto;

import backend.com.comercial.domain.model.NotaVenta;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class NVResponse {
    private Long id;
    private Long numero;
    private Long evaluacionNegocioId;
    private Long clienteId;
    private Long vendedorId;
    private String estado;
    private Boolean esKit;
    private LocalDate fechaEmision;
    private LocalDate fechaEntregaEstimada;
    private BigDecimal montoSubtotal;
    private BigDecimal montoIva;
    private BigDecimal montoTotal;
    private List<ItemNVResponse> items;

    @Data
    public static class ItemNVResponse {
        private Integer nroItem;
        private Long productoId;
        private String modelo;
        private String color;
        private String talla;
        private Integer cantidad;
        private BigDecimal precioUnitario;
        private BigDecimal total;
    }

    public static NVResponse fromDomain(NotaVenta domain) {
        NVResponse response = new NVResponse();
        response.setId(domain.getId());
        response.setNumero(domain.getNumero().getValue());
        response.setEvaluacionNegocioId(domain.getEvaluacionNegocioId());
        response.setClienteId(domain.getClienteId());
        response.setVendedorId(domain.getVendedorId());
        response.setEstado(domain.getEstado().name());
        response.setEsKit(domain.getEsKit());
        response.setFechaEmision(domain.getFechaEmision());
        response.setFechaEntregaEstimada(domain.getFechaEntregaEstimada());
        response.setMontoSubtotal(domain.getMontoSubtotal().getAmount());
        response.setMontoIva(domain.getMontoIva().getAmount());
        response.setMontoTotal(domain.getMontoTotal().getAmount());

        response.setItems(domain.getItems().stream().map(item -> {
            ItemNVResponse itemResponse = new ItemNVResponse();
            itemResponse.setNroItem(item.getNroItem());
            itemResponse.setProductoId(item.getProductoId());
            itemResponse.setModelo(item.getModelo());
            itemResponse.setColor(item.getColor());
            itemResponse.setTalla(item.getTalla());
            itemResponse.setCantidad(item.getCantidad());
            itemResponse.setPrecioUnitario(item.getPrecioUnitario().getAmount());
            itemResponse.setTotal(item.getTotal().getAmount());
            return itemResponse;
        }).collect(Collectors.toList()));

        return response;
    }
}
