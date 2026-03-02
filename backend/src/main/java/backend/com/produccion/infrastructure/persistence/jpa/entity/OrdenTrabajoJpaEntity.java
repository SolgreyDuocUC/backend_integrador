package backend.com.produccion.infrastructure.persistence.jpa.entity;

import backend.com.produccion.domain.model.EstadoOT;
import backend.com.produccion.domain.model.TipoOT;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "produccion_orden_trabajo")
@Getter
@Setter
public class OrdenTrabajoJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, precision = 20, nullable = false)
    private Long numero;

    @Column(name = "nota_venta_id", nullable = false)
    private Long notaVentaId;

    @Column(name = "nro_item")
    private Integer nroItem;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private TipoOT tipo;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private EstadoOT estado;

    @Column(columnDefinition = "TEXT")
    private String observaciones;
}
