package backend.com.comercial.repository.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "solicitudes_cotizacion")
@Getter
@Setter
public class SolicitudCotizacionJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "venta_asociada_id")
    private Long ventaAsociadaId;

    @Column(length = 20)
    private String estado;

    @OneToMany(mappedBy = "solicitudCotizacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SCOTPrendaListaJpaEntity> prendas = new ArrayList<>();

    public void addPrenda(SCOTPrendaListaJpaEntity prenda) {
        prendas.add(prenda);
        prenda.setSolicitudCotizacion(this);
    }
}


