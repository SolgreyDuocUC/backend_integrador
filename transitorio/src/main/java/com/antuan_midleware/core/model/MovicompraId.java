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
public class MovicompraId implements Serializable {
    @Column(name = "EMPRESA")
    private String empresa;

    @Column(name = "PERIODO")
    private Integer periodo;

    @Column(name = "LOCAL")
    private Integer local;

    @Column(name = "TIPODOCUMENTO")
    private String tipodocumento;

    @Column(name = "NRODOCUMENTO")
    private Long nrodocumento;

    @Column(name = "RUTPROVEE")
    private Long rutprovee;

    @Column(name = "LINEA")
    private Integer linea;


}
