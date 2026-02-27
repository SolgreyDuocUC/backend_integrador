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
public class ComSolicostoId implements Serializable {
    @Column(name = "Empresa")
    private String empresa;

    @Column(name = "Numero_OP")
    private Long numeroOp;

    @Column(name = "Linea")
    private Integer linea;


}
