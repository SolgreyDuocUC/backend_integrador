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
@Table(name = "det_correccion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetCorreccion implements Serializable {

    @EmbeddedId
    private DetCorreccionId id;

    @Column(name = "Codigo")
    private Long codigo;

    @Column(name = "Descripcion", length = 100)
    private String descripcion;

    @Column(name = "Valor")
    private BigDecimal valor;

}
