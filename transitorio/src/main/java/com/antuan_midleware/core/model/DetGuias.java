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
@Table(name = "det_guias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetGuias implements Serializable {

    @EmbeddedId
    private DetGuiasId id;

    @Column(name = "Producto", length = 50)
    private String producto;

    @Column(name = "Cantidad")
    private Long cantidad;

    @Column(name = "Precio")
    private BigDecimal precio;

    @Column(name = "Total")
    private BigDecimal total;

}
