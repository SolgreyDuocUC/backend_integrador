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
public class FoliocomprobantesId implements Serializable {
    @Column(name = "Empresa")
    private String empresa;

    @Column(name = "NumComp")
    private Long numcomp;

    @Column(name = "TipoComp")
    private String tipocomp;

    @Column(name = "Ann_Comp")
    private Integer annComp;


}
