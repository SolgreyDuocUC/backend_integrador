package com.antuan_midleware.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "configuracuenta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Configuracuenta implements Serializable {

    @Column(name = "Empresa", length = 3)
    private String empresa;

    @Column(name = "NivelNombre1", length = 30)
    private String nivelnombre1;

    @Column(name = "NivelLargo1")
    private Integer nivellargo1;

    @Column(name = "NivelNombre2", length = 30)
    private String nivelnombre2;

    @Column(name = "NivelLargo2")
    private Integer nivellargo2;

    @Column(name = "NivelNombre3", length = 30)
    private String nivelnombre3;

    @Column(name = "NivelLargo3")
    private Integer nivellargo3;

    @Column(name = "NivelNombre4", length = 30)
    private String nivelnombre4;

    @Column(name = "NivelLargo4")
    private Integer nivellargo4;

    @Column(name = "NivelNombre5", length = 30)
    private String nivelnombre5;

    @Column(name = "NivelLargo5")
    private Integer nivellargo5;

}
