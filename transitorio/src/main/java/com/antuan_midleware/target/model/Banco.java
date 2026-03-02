package com.antuan_midleware.target.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bancos")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Banco extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(length = 20)
    private String codigo;

    @Column(name = "empresa_original", length = 10)
    private String empresaOriginal;
}
