package com.antuan_midleware.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "det_occliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetOccliente implements Serializable {

    @EmbeddedId
    private DetOcclienteId id;

    @Column(name = "Producto", length = 50)
    private String producto;

    @Column(name = "Cantidad")
    private Long cantidad;

    @Column(name = "Logo", length = 1)
    private String logo;

    @Column(name = "Nro_OPSC")
    private Long nroOpsc;

    @Column(name = "Linea_OPSC")
    private Integer lineaOpsc;

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

    @Column(name = "ProveedorAscii", length = 45)
    private String proveedorascii;

    @Column(name = "PUnitario")
    private Double punitario;

    @Column(name = "Totallin")
    private BigDecimal totallin;

    @Column(name = "CantFacturada")
    private Double cantfacturada;

    @Column(name = "ReqOT", length = 1)
    private String reqot;

    @Lob
    @Column(name = "espelineal")
    private String espelineal;

}
