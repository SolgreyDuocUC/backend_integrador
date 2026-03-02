package com.antuan_midleware.target.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "shared_proveedores")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Proveedor extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true, length = 100, nullable = false)
    private String nombre;

    @Column(length = 12)
    private String rut;

    @Column(length = 150)
    private String direccion;

    @Column(length = 20)
    private String telefono;

    @Column(length = 100)
    private String contacto;
}
