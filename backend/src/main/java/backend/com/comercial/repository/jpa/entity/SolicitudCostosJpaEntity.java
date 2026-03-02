package backend.com.comercial.repository.jpa.entity;

import backend.com.shared.model.Cliente;
import backend.com.shared.model.EspecificacionTecnica;
import backend.com.shared.model.Vendedor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "solicitudes_costos")
@Getter
@Setter
public class SolicitudCostosJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, precision = 20, nullable = false)
    private Long numero;

    @Column(length = 20)
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendedor_id", nullable = false)
    private Vendedor vendedor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "especificacion_tecnica_id")
    private EspecificacionTecnica especificacionTecnica;

    @Column(name = "articulo_descripcion", nullable = false)
    private String articuloDescripcion;

    @Column(name = "es_muestra")
    private Boolean esMuestra;

    private Integer cantidad;

    private LocalDate fecha;

    @Column(name = "costo_total_calculado", precision = 14, scale = 2)
    private BigDecimal costoTotalCalculado;

    @Column(length = 3)
    private String monedaCostoTotal;

    @OneToMany(mappedBy = "solicitudCostos", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SCOSTelaJpaEntity> telas = new ArrayList<>();

    @OneToMany(mappedBy = "solicitudCostos", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SCOSAccesorioJpaEntity> accesorios = new ArrayList<>();

    @OneToOne(mappedBy = "solicitudCostos", cascade = CascadeType.ALL)
    private SCOSCostoFijoJpaEntity costoFijo;

    public void addTela(SCOSTelaJpaEntity tela) {
        telas.add(tela);
        tela.setSolicitudCostos(this);
    }

    public void addAccesorio(SCOSAccesorioJpaEntity accesorio) {
        accesorios.add(accesorio);
        accesorio.setSolicitudCostos(this);
    }

    public void setCostoFijo(SCOSCostoFijoJpaEntity costoFijo) {
        this.costoFijo = costoFijo;
        if (costoFijo != null) {
            costoFijo.setSolicitudCostos(this);
        }
    }
}
