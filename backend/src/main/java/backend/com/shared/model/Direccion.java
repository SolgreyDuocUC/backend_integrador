package backend.com.shared.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "direccion")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Direccion extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_postal", length = 10)
    private String codigoPostal;

    @NotBlank
    @Column(length = 150, nullable = false)
    private String calle;

    @NotBlank
    @Column(length = 20, nullable = false)
    private String numero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comuna_id", nullable = false)
    private Comuna comuna;
}
