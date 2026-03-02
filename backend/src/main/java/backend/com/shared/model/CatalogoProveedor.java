package backend.com.shared.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "shared_catalogo_proveedores")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class CatalogoProveedor extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proveedor_id", nullable = false)
    private Proveedor proveedor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @Column(name = "precio_unitario", precision = 12, scale = 2, nullable = false)
    private BigDecimal precioUnitario;

    @Column(length = 10, nullable = false)
    private String moneda = "CLP";

    @Column(name = "fecha_vigencia", nullable = false)
    private LocalDate fechaVigencia;

    private boolean activo = true;
}
