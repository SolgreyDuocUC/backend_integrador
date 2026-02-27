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
public class FoliosId implements Serializable {
    @Column(name = "EMPRESA")
    private String empresa;

    @Column(name = "TIPO")
    private String tipo;

    @Column(name = "EQUIPO")
    private String equipo;


}
