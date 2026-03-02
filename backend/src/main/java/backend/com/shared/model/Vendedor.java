package backend.com.shared.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "vendedor", indexes = {
    @Index(name = "idx_vendedor_run", columnList = "run")
})
@Data
@EqualsAndHashCode(callSuper = true)
public class Vendedor extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 12)
    private String run;

    @Column(name = "usuario_id", unique = true)
    private Long usuarioId; // Mapeo temporal del OneToOne a User
}
