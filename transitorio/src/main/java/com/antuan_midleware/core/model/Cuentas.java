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
@Table(name = "cuentas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cuentas implements Serializable {

    @EmbeddedId
    private CuentasId id;

    @Column(name = "NOMBRE", length = 50)
    private String nombre;

    @Column(name = "TIPO", length = 1)
    private String tipo;

    @Column(name = "NATURALEZA", length = 50)
    private String naturaleza;

    @Column(name = "ANALISIS", length = 50)
    private String analisis;

    @Column(name = "CENTROCOSTO")
    private Boolean centrocosto;

    @Column(name = "CONCILIACION")
    private Boolean conciliacion;

    @Column(name = "CUENTACLAS")
    private Double cuentaclas;

    @Column(name = "CUENTARESU")
    private Double cuentaresu;

}
