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
@Table(name = "det_evaluacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetEvaluacion implements Serializable {

    @EmbeddedId
    private DetEvaluacionId id;

    @Column(name = "Producto", length = 50)
    private String producto;

    @Column(name = "Cantidad")
    private Long cantidad;

    @Column(name = "Familia", length = 20)
    private String familia;

    @Column(name = "PrecioVenta")
    private BigDecimal precioventa;

    @Column(name = "CostoCompra")
    private BigDecimal costocompra;

    @Column(name = "CostoFabric")
    private BigDecimal costofabric;

    @Column(name = "PrecioBE")
    private BigDecimal preciobe;

    @Column(name = "CostoTotal")
    private BigDecimal costototal;

    @Column(name = "MargenSinCom")
    private Double margensincom;

    @Column(name = "MargenConCom")
    private Double margenconcom;

}
