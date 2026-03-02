package com.antuan_midleware.target.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "centros_costo")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class CentroCosto extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_original", length = 20)
    private String codigoOriginal;

    @NotBlank
    @Column(length = 100, nullable = false)
    private String descripcion;
}
