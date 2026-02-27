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
@Table(name = "enc_evaluacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EncEvaluacion implements Serializable {

    @EmbeddedId
    private EncEvaluacionId id;

    @Column(name = "Fecha")
    private LocalDateTime fecha;

    @Column(name = "Cliente")
    private Long cliente;

    @Column(name = "Vendedor")
    private Integer vendedor;

    @Column(name = "Estado")
    private Integer estado;

    @Column(name = "CostoHilos")
    private BigDecimal costohilos;

    @Column(name = "CostoEtiqueta")
    private BigDecimal costoetiqueta;

    @Column(name = "CostoFlete")
    private BigDecimal costoflete;

    @Column(name = "CostoMO_sinGrat")
    private BigDecimal costomoSingrat;

    @Column(name = "CostoMO_conGrat")
    private BigDecimal costomoCongrat;

    @Column(name = "Fec_Solicitud")
    private LocalDateTime fecSolicitud;

    @Column(name = "PorcMargen")
    private Double porcmargen;

    @Column(name = "PorcComision")
    private Double porccomision;

}
