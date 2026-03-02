package backend.com.comercial.repository.jpa.entity;

import backend.com.shared.model.Cliente;
import backend.com.shared.model.Vendedor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "notas_venta")
@Getter
@Setter
public class NotaVentaJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, precision = 20, nullable = false)
    private Long numero;

    @Column(name = "evaluacion_negocio_id")
    private Long evaluacionNegocioId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendedor_id", nullable = false)
    private Vendedor vendedor;

    @Column(length = 20, nullable = false)
    private String estado;

    @Column(name = "es_kit")
    private Boolean esKit;

    @Column(name = "fecha_emision", nullable = false)
    private LocalDate fechaEmision;

    @Column(name = "fecha_entrega_estimada")
    private LocalDate fechaEntregaEstimada;

    @Column(name = "monto_subtotal", precision = 12, scale = 2)
    private BigDecimal montoSubtotal;

    @Column(length = 3)
    private String monedaSubtotal;

    @Column(name = "monto_iva", precision = 12, scale = 2)
    private BigDecimal montoIva;

    @Column(length = 3)
    private String monedaIva;

    @Column(name = "monto_total", precision = 12, scale = 2)
    private BigDecimal montoTotal;

    @Column(length = 3)
    private String monedaTotal;

    @OneToMany(mappedBy = "notaVenta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NotaVentaItemJpaEntity> items = new ArrayList<>();

    public void addItem(NotaVentaItemJpaEntity item) {
        items.add(item);
        item.setNotaVenta(this);
    }
}
