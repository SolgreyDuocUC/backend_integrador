package com.antuan_midleware.core.model;

import jakarta.persistence.Column;
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
@Table(name = "det_compras")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetCompras implements Serializable {

    @EmbeddedId
    private DetComprasId id;

    @Column(name = "Producto", length = 50)
    private String producto;

    @Column(name = "Cantidad")
    private Long cantidad;

    @Column(name = "Precio_Unitario")
    private BigDecimal precioUnitario;

    @Column(name = "Total")
    private BigDecimal total;

}
