package com.antuan_midleware.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "det_otrabajo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetOtrabajo implements Serializable {

    @EmbeddedId
    private DetOtrabajoId id;

    @Column(name = "Producto", length = 50)
    private String producto;

    @Column(name = "Proveedor")
    private Long proveedor;

    @Column(name = "Cant_Solicitada")
    private Double cantSolicitada;

    @Column(name = "Cant_Bodega")
    private Double cantBodega;

    @Column(name = "Cant_Comprada")
    private Double cantComprada;

    @Column(name = "Modelo", length = 45)
    private String modelo;

    @Column(name = "Tela", length = 45)
    private String tela;

    @Column(name = "Composicion", length = 45)
    private String composicion;

    @Column(name = "Genero", length = 45)
    private String genero;

    @Column(name = "Color", length = 45)
    private String color;

    @Column(name = "Aplicacion", length = 45)
    private String aplicacion;

    @Column(name = "Logo", length = 1)
    private String logo;

    @Column(name = "ReqOT", length = 1)
    private String reqot;

}
