package com.antuan_midleware.target.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "produccion_orden_items")
@Getter
@Setter
public class OrdenProduccionItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orden_produccion_id", nullable = false)
    private OrdenProduccion ordenProduccion;

    @Column(name = "producto_id", nullable = false)
    private Long productoId;

    @Column(name = "nro_item")
    private Integer nroItem;

    @Column(length = 100)
    private String modelo;

    @Column(length = 100)
    private String tela;

    @Column(length = 255)
    private String composicion;

    @Column(length = 50)
    private String color;

    @Column(length = 20)
    private String talla;

    @Column(length = 20)
    private String genero;

    @Column(length = 50)
    private String codigo;

    private Boolean llevaLogo = false;

    @Column(nullable = false)
    private Integer cantidad;
}
