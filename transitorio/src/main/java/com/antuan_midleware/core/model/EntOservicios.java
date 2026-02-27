package com.antuan_midleware.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ent_oservicios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EntOservicios implements Serializable {

    @EmbeddedId
    private EntOserviciosId id;

    @Column(name = "Cantidad")
    private Long cantidad;

    @Column(name = "Fecha")
    private LocalDateTime fecha;

    @Column(name = "Descripcion", length = 100)
    private String descripcion;

}
