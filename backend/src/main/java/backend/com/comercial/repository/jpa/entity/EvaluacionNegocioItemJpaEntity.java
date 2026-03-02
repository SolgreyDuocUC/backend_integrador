package backend.com.comercial.repository.jpa.entity;

import backend.com.shared.model.Producto;
import backend.com.shared.model.Proveedor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "evaluacion_negocio_items")
@Getter
@Setter
public class EvaluacionNegocioItemJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evaluacion_negocio_id", nullable = false)
    private EvaluacionNegocioJpaEntity evaluacionNegocio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proveedor_id")
    private Proveedor proveedor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(name = "precio_unitario", precision = 12, scale = 2, nullable = false)
    private BigDecimal precioUnitario;
    
    @Column(length = 3)
    private String monedaPrecioUnitario;

    @Column(name = "costo_unitario", precision = 12, scale = 2)
    private BigDecimal costoUnitario;
    
    @Column(length = 3)
    private String monedaCostoUnitario;

    @Column(name = "tipo_item", length = 30)
    private String tipoItem;

    @Column(name = "referencia_texto", length = 255)
    private String referenciaTexto;

    @Column(name = "margen_item", precision = 5, scale = 2)
    private BigDecimal margenItem;

    @Column(precision = 12, scale = 2)
    private BigDecimal total;
    
    @Column(length = 3)
    private String monedaTotal;
}


