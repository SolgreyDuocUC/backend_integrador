package backend.com.shared.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "especificacion_tecnica")
@Data
@EqualsAndHashCode(callSuper = true)
public class EspecificacionTecnica extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @Column(length = 200)
    private String composicion;

    @Column(length = 50)
    private String peso;

    @Column(length = 50)
    private String forro;

    @Column(length = 50)
    private String relleno;

    @Column(length = 50)
    private String gorro;

    @Column(length = 50)
    private String cuello;

    @Column(length = 50)
    private String cierre;
}
