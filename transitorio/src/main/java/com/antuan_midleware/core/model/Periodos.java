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
@Table(name = "periodos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Periodos implements Serializable {

    @EmbeddedId
    private PeriodosId id;

    @Column(name = "MES01")
    private Boolean mes01;

    @Column(name = "MES02")
    private Boolean mes02;

    @Column(name = "MES03")
    private Boolean mes03;

    @Column(name = "MES04")
    private Boolean mes04;

    @Column(name = "MES05")
    private Boolean mes05;

    @Column(name = "MES06")
    private Boolean mes06;

    @Column(name = "MES07")
    private Boolean mes07;

    @Column(name = "MES08")
    private Boolean mes08;

    @Column(name = "MES09")
    private Boolean mes09;

    @Column(name = "MES10")
    private Boolean mes10;

    @Column(name = "MES11")
    private Boolean mes11;

    @Column(name = "MES12")
    private Boolean mes12;

}
