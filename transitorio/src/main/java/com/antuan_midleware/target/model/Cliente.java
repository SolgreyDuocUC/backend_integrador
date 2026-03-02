package com.antuan_midleware.target.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "cliente", indexes = {
        @Index(name = "idx_cliente_run", columnList = "run")
})
@Data
@EqualsAndHashCode(callSuper = true)
public class Cliente extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String apellido;

    @Column(length = 12)
    private String run;

    @Email
    @Column(length = 150)
    private String correo;

    @Column(length = 20)
    private String telefono;
}
