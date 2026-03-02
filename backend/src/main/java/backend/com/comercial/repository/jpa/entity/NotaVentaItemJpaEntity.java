package backend.com.comercial.repository.jpa.entity;

import backend.com.shared.model.Producto;
import backend.com.shared.model.Proveedor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "notas_venta_items")
@Getter
@Setter
public class NotaVentaItemJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nro_item")
    private Integer nroItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nota_venta_id", nullable = false)
    private NotaVentaJpaEntity notaVenta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @Column(length = 100)
    private String modelo;

    @Column(length = 100)
    private String tela;

    @Column(length = 255)
    private String composicion;

    @Column(length = 50)
    private String color;

    @Column(length = 20)
    private String talla;

    @Column(length = 20)
    private String genero;

    @Column(length = 50)
    private String codigo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proveedor_id")
    private Proveedor proveedor;

    @Column(name = "lleva_logo")
    private Boolean llevaLogo;

    @Column(name = "item_type", length = 30)
    private String itemType;

    @Column(name = "is_personalized")
    private Boolean isPersonalized;

    @Column(name = "detalle_ot", columnDefinition = "TEXT")
    private String detalleOt;

    @Column(name = "logo_detalle", columnDefinition = "TEXT")
    private String logoDetalle;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(name = "precio_unitario", precision = 12, scale = 2)
    private BigDecimal precioUnitario;
    
    @Column(length = 3)
    private String monedaPrecioUnitario;

    @Column(precision = 12, scale = 2)
    private BigDecimal total;

    @Column(length = 3)
    private String monedaTotal;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NotaVentaItemTallaJpaEntity> tallas = new ArrayList<>();

    public void addTalla(NotaVentaItemTallaJpaEntity talla) {
        tallas.add(talla);
        talla.setItem(this);
    }
}


