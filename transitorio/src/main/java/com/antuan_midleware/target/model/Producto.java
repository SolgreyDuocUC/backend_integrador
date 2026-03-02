package com.antuan_midleware.target.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "producto", indexes = {
        @Index(name = "idx_producto_codigo", columnList = "codigo")
})
@Data
@EqualsAndHashCode(callSuper = true)
public class Producto extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String codigo;

    @NotBlank
    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(length = 300)
    private String descripcion;

    @Column(length = 30)
    private String genero;

    @Column(length = 50)
    private String color;
}
