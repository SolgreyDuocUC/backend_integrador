package com.antuan_midleware.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pla_solicosto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaSolicosto implements Serializable {

    @EmbeddedId
    private PlaSolicostoId id;

    @Column(name = "Nombre", length = 255)
    private String nombre;

    @Lob
    @Column(name = "Descripcion")
    private String descripcion;

}
