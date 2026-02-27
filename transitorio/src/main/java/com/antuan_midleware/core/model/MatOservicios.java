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
@Table(name = "mat_oservicios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatOservicios implements Serializable {

    @EmbeddedId
    private MatOserviciosId id;

    @Column(name = "Cantidad")
    private Long cantidad;

    @Column(name = "Medida", length = 20)
    private String medida;

    @Column(name = "Descripcion", length = 100)
    private String descripcion;

    @Column(name = "Referencias", length = 100)
    private String referencias;

}
