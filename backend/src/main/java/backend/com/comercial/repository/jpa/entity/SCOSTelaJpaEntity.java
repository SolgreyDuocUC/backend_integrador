package backend.com.comercial.repository.jpa.entity;

import backend.com.shared.model.Proveedor;
import backend.com.shared.model.Tela;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "scos_telas")
@Getter
@Setter
public class SCOSTelaJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "solicitud_costos_id", nullable = false)
    private SolicitudCostosJpaEntity solicitudCostos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tela_id")
    private Tela tela;

    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proveedor_id")
    private Proveedor proveedor;

    @Column(precision = 10, scale = 4)
    private BigDecimal consumo;

    @Column(name = "unidad_medida", length = 20)
    private String unidadMedida;

    @Column(name = "precio_unitario", precision = 12, scale = 2)
    private BigDecimal precioUnitario;
    
    @Column(length = 3)
    private String monedaPrecioUnitario;

    @Column(name = "costo_total", precision = 12, scale = 2)
    private BigDecimal costoTotal;
    
    @Column(length = 3)
    private String monedaCostoTotal;
}


