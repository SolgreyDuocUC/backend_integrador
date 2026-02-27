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
@Table(name = "det_ocbordados")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetOcbordados implements Serializable {

    @EmbeddedId
    private DetOcbordadosId id;

    @Column(name = "Descripcion", length = 100)
    private String descripcion;

    @Column(name = "Cantidad")
    private Long cantidad;

    @Column(name = "Precio")
    private BigDecimal precio;

    @Column(name = "Total")
    private BigDecimal total;

    @Column(name = "Tipo", length = 20)
    private String tipo;

    @Column(name = "Numero")
    private Long numero;

}
