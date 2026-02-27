package com.antuan_midleware.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ocrecuperada")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ocrecuperada implements Serializable {

    @EmbeddedId
    private OcrecuperadaId id;

    @Column(name = "Codigo", length = 20)
    private String codigo;

    @Column(name = "Descripcion", length = 100)
    private String descripcion;

    @Column(name = "ProductoCli", length = 50)
    private String productocli;

    @Column(name = "Talla", length = 20)
    private String talla;

    @Column(name = "Genero", length = 20)
    private String genero;

    @Column(name = "Color", length = 20)
    private String color;

    @Column(name = "Logo", length = 1)
    private String logo;

    @Column(name = "Umed", length = 10)
    private String umed;

    @Column(name = "FecEntrega")
    private LocalDateTime fecentrega;

    @Column(name = "Cantidad")
    private Long cantidad;

    @Column(name = "PreUnitario")
    private Double preunitario;

    @Column(name = "Total")
    private BigDecimal total;

    @Column(name = "NroNota", length = 30)
    private String nronota;

}
