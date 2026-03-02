package backend.com.comercial.domain.model;

import backend.com.shared.valueobjects.Money;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class ItemEVN {
    Long productoId;
    Long proveedorId;
    Integer cantidad;
    Money precioUnitario;
    Money costoUnitario;
    String tipoItem;
    String referenciaTexto;
    BigDecimal margenItem;
    Money total;
}
