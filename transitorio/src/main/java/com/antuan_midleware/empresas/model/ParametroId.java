package com.antuan_midleware.empresas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParametroId implements Serializable {

    @Column(name = "EMPRESA")
    private String empresa;

    @Column(name = "TIPO")
    private String tipo;

    @Column(name = "CODIGO")
    private Integer codigo;
}
