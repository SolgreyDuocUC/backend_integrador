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
@Table(name = "evaluaciones_negocio")
@Getter
@Setter
public class EvaluacionNegocioJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, precision = 20, nullable = false)
    private Long numero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendedor_id", nullable = false)
    private Vendedor vendedor;

    @Column(length = 20, nullable = false)
    private String estado;

    @Column(name = "fecha_evaluacion", nullable = false)
    private LocalDate fechaEvaluacion;

    @Column(name = "monto_total", precision = 12, scale = 2)
    private BigDecimal montoTotal;

    @Column(length = 3)
    private String monedaMontoTotal;

    @Column(name = "margen_ganancia", precision = 5, scale = 2)
    private BigDecimal margenGanancia;

    @Column(name = "rentabilidad_esperada", precision = 12, scale = 2)
    private BigDecimal rentabilidadEsperada;

    @Column(length = 3)
    private String monedaRentabilidad;

    @Column(name = "costeo_id")
    private Long costeoId;

    @Column(name = "solicitud_cotizacion_id")
    private Long solicitudCotizacionId;

    // Costos Adicionales
    @Column(name = "garantia_seriedad", precision = 12, scale = 2)
    private BigDecimal garantiaSeriedad;

    @Column(name = "garantia_fiel", precision = 12, scale = 2)
    private BigDecimal garantiaFiel;

    @Column(name = "flete_especial", precision = 12, scale = 2)
    private BigDecimal fleteEspecial;

    @Column(name = "modificacion_prenda", precision = 12, scale = 2)
    private BigDecimal modificacionPrenda;

    @Column(name = "toma_tallaje", precision = 12, scale = 2)
    private BigDecimal tomaTallaje;

    @Column(name = "certificacion", precision = 12, scale = 2)
    private BigDecimal certificacion;

    @Column(name = "muestras_fisicas", precision = 12, scale = 2)
    private BigDecimal muestrasFisicas;

    @OneToMany(mappedBy = "evaluacionNegocio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EvaluacionNegocioItemJpaEntity> items = new ArrayList<>();

    public void addItem(EvaluacionNegocioItemJpaEntity item) {
        items.add(item);
        item.setEvaluacionNegocio(this);
    }
}
