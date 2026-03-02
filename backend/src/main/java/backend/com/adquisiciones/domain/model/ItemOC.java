package backend.com.adquisiciones.domain.model;

import backend.com.shared.valueobjects.Money;
import lombok.Value;

@Value
public class ItemOC {
    Long productoId;
    String descripcionProducto;
    Integer cantidad;
    Money precioUnitario;
}
