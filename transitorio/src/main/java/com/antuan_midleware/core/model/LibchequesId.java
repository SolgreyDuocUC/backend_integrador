package com.antuan_midleware.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class LibchequesId implements Serializable {
    @Column(name = "Empresa")
    private String empresa;

    @Column(name = "Ano_Mov")
    private Integer anoMov;

    @Column(name = "Numero")
    private Long numero;

    @Column(name = "Tip_Comp")
    private String tipComp;

    @Column(name = "Linea")
    private Integer linea;


}
