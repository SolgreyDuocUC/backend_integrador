package backend.com.shared.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "costo_fijo")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class CostoFijo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(precision = 12, scale = 2)
    private BigDecimal hilo = BigDecimal.ZERO;

    @Column(name = "mano_obra_simple", precision = 12, scale = 2)
    private BigDecimal manoObraSimple = BigDecimal.ZERO;

    @Column(name = "mano_obra_gratificacion", precision = 12, scale = 2)
    private BigDecimal manoObraGratificacion = BigDecimal.ZERO;

    @Column(precision = 12, scale = 2)
    private BigDecimal etiqueta = BigDecimal.ZERO;

    @Column(precision = 12, scale = 2)
    private BigDecimal embalaje = BigDecimal.ZERO;

    @Column(precision = 12, scale = 2)
    private BigDecimal flete = BigDecimal.ZERO;
}
