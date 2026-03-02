package backend.com.comercial.domain.model;

import backend.com.shared.valueobjects.Money;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class SCOSAccesorio {
    private Long accesorioId;
    private String descripcion;
    private Long proveedorId;
    private BigDecimal consumo;
    private String unidadMedida;
    private Money precioUnitario;
    private Money costoTotal;

    public SCOSAccesorio(Long accesorioId, String descripcion, Long proveedorId, BigDecimal consumo, 
                         String unidadMedida, Money precioUnitario) {
        this.accesorioId = accesorioId;
        this.descripcion = descripcion;
        this.proveedorId = proveedorId;
        this.consumo = consumo != null ? consumo : BigDecimal.ZERO;
        this.unidadMedida = unidadMedida != null ? unidadMedida : "un";
        this.precioUnitario = precioUnitario;
        calcularCostoTotal();
    }

    private void calcularCostoTotal() {
        if (this.precioUnitario != null && this.consumo != null) {
            this.costoTotal = this.precioUnitario.multiply(this.consumo);
        } else {
            this.costoTotal = new Money(BigDecimal.ZERO, "CLP");
        }
    }
}
