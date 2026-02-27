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
@Table(name = "movicompra")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movicompra implements Serializable {

    @EmbeddedId
    private MovicompraId id;

    @Column(name = "AFE_CUENTA")
    private Double afeCuenta;

    @Column(name = "AFE_MONTO")
    private BigDecimal afeMonto;

    @Column(name = "AFE_VCTO")
    private LocalDateTime afeVcto;

    @Column(name = "EXE_CUENTA")
    private Double exeCuenta;

    @Column(name = "EXE_MONTO")
    private BigDecimal exeMonto;

    @Column(name = "TOT_CUENTA")
    private BigDecimal totCuenta;

    @Column(name = "TOT_MONTO")
    private BigDecimal totMonto;

    @Column(name = "COD_CENCOSTO")
    private Integer codCencosto;

    @Column(name = "COD_ITEMGASTO", length = 20)
    private String codItemgasto;

    @Column(name = "TIPOORIGEN", length = 10)
    private String tipoorigen;

    @Column(name = "NROORIGEN")
    private Long nroorigen;

}
