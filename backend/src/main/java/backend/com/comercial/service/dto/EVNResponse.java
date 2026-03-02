package backend.com.comercial.service.dto;

import backend.com.comercial.domain.model.EvaluacionNegocio;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class EVNResponse {
    private Long id;
    private Long numero;
    private Long clienteId;
    private Long vendedorId;
    private String estado;
    private LocalDate fechaEvaluacion;
    private BigDecimal montoTotal;
    private BigDecimal margenGanancia;
    private BigDecimal rentabilidadEsperada;
    private List<ItemEVNResponse> items;

    @Data
    public static class ItemEVNResponse {
        private Long productoId;
        private Long proveedorId;
        private Integer cantidad;
        private BigDecimal precioUnitario;
        private BigDecimal costoUnitario;
        private String tipoItem;
        private BigDecimal total;
    }

    public static EVNResponse fromDomain(EvaluacionNegocio domain) {
        EVNResponse response = new EVNResponse();
        response.setId(domain.getId());
        response.setNumero(domain.getNumero().getValue());
        response.setClienteId(domain.getClienteId());
        response.setVendedorId(domain.getVendedorId());
        response.setEstado(domain.getEstado().name());
        response.setFechaEvaluacion(domain.getFechaEvaluacion());
        response.setMontoTotal(domain.getMontoTotal().getAmount());
        response.setMargenGanancia(domain.getMargenGanancia());
        response.setRentabilidadEsperada(domain.getRentabilidadEsperada().getAmount());

        response.setItems(domain.getItems().stream().map(item -> {
            ItemEVNResponse itemResponse = new ItemEVNResponse();
            itemResponse.setProductoId(item.getProductoId());
            itemResponse.setProveedorId(item.getProveedorId());
            itemResponse.setCantidad(item.getCantidad());
            itemResponse.setPrecioUnitario(item.getPrecioUnitario().getAmount());
            itemResponse.setCostoUnitario(item.getCostoUnitario().getAmount());
            itemResponse.setTipoItem(item.getTipoItem());
            itemResponse.setTotal(item.getTotal().getAmount());
            return itemResponse;
        }).collect(Collectors.toList()));

        return response;
    }
}
