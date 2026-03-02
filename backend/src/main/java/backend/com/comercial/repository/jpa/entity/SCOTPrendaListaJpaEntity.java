package backend.com.comercial.repository.jpa.entity;

import backend.com.shared.model.Producto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "scot_prendas")
@Getter
@Setter
public class SCOTPrendaListaJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "solicitud_id", nullable = false)
    private SolicitudCotizacionJpaEntity solicitudCotizacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @Column(length = 200, nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(length = 20)
    private String talla;

    @Column(length = 50)
    private String color;

    @Column(name = "proveedor_referencia", length = 200)
    private String proveedorReferencia;

    @Column(name = "link_referencia", length = 500)
    private String linkReferencia;

    @Column(length = 200)
    private String composicion;

    @Column(length = 50)
    private String peso;

    @Column(columnDefinition = "TEXT")
    private String observaciones;
}


