package com.antuan_midleware.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "det_documento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetDocumento implements Serializable {

    @EmbeddedId
    private DetDocumentoId id;

    @Column(name = "Codigo_Producto", length = 20)
    private String codigoProducto;

    @Column(name = "Cantidad")
    private Long cantidad;

    @Column(name = "Precio_Unitario")
    private BigDecimal precioUnitario;

    @Column(name = "SubTotal")
    private BigDecimal subtotal;

    @Column(name = "Descuento")
    private BigDecimal descuento;

    @Column(name = "Total_Linea")
    private BigDecimal totalLinea;

    @Column(name = "Glosa", length = 255)
    private String glosa;

    @Column(name = "DocOrigen", length = 10)
    private String docorigen;

    @Column(name = "NroOrigen")
    private Long nroorigen;

    @Column(name = "LinOrigen")
    private Integer linorigen;

}
