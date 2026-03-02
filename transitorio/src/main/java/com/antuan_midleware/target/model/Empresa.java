package com.antuan_midleware.target.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "empresa")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Empresa extends BaseEntity {

    @Id
    @Column(length = 12)
    private String rut;

    @NotBlank
    @Column(name = "razon_social", length = 150, nullable = false)
    private String razonSocial;

    @Email
    @Column(length = 150)
    private String correo;

    @Column(length = 20)
    private String telefono;
}
