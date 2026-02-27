package com.antuan_midleware.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tal_solicompra")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TalSolicompra implements Serializable {

    @EmbeddedId
    private TalSolicompraId id;

    @Column(name = "Talla01")
    private Integer talla01;

    @Column(name = "Talla02")
    private Integer talla02;

    @Column(name = "Talla03")
    private Integer talla03;

    @Column(name = "Talla04")
    private Integer talla04;

    @Column(name = "Talla05")
    private Integer talla05;

    @Column(name = "Talla06")
    private Integer talla06;

    @Column(name = "Talla07")
    private Integer talla07;

    @Column(name = "Talla08")
    private Integer talla08;

    @Column(name = "Talla09")
    private Integer talla09;

    @Column(name = "Talla10")
    private Integer talla10;

    @Column(name = "Talla11")
    private Integer talla11;

    @Column(name = "Talla12")
    private Integer talla12;

    @Column(name = "Talla13")
    private Integer talla13;

    @Column(name = "Talla14")
    private Integer talla14;

    @Column(name = "Talla15")
    private Integer talla15;

    @Column(name = "Talla16")
    private Integer talla16;

    @Column(name = "Talla17")
    private Integer talla17;

    @Column(name = "Talla18")
    private Integer talla18;

    @Column(name = "Talla19")
    private Integer talla19;

    @Column(name = "Talla20")
    private Integer talla20;

    @Column(name = "Talla21")
    private Integer talla21;

    @Column(name = "Talla22")
    private Integer talla22;

    @Column(name = "Talla23")
    private Integer talla23;

    @Column(name = "Talla24")
    private Integer talla24;

    @Column(name = "Talla25")
    private Integer talla25;

    @Column(name = "Talla26")
    private Integer talla26;

    @Column(name = "Talla27")
    private Integer talla27;

    @Column(name = "Talla28")
    private Integer talla28;

    @Column(name = "Talla29")
    private Integer talla29;

    @Column(name = "Talla30")
    private Integer talla30;

    @Column(name = "PrecCompra")
    private Double preccompra;

}
