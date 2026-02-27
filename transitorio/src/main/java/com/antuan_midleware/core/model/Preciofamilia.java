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
@Table(name = "preciofamilia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Preciofamilia implements Serializable {

    @EmbeddedId
    private PreciofamiliaId id;

    @Column(name = "Precio")
    private BigDecimal precio;

    @Column(name = "Costo")
    private BigDecimal costo;

}
