package backend.com.shared.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "accesorio")
@Data
@EqualsAndHashCode(callSuper = true)
public class Accesorio extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 200)
    private String caracteristica;

    @Column(length = 100)
    private String ubicacion;

    @Column(length = 50)
    private String tamano;

    @Column(length = 50)
    private String color;
}
