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
@Table(name = "locales")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Locales implements Serializable {

    @EmbeddedId
    private LocalesId id;

    @Column(name = "DESCRIPCION", length = 50)
    private String descripcion;

    @Column(name = "DIRECCION", length = 100)
    private String direccion;

    @Column(name = "COMUNA", length = 50)
    private String comuna;

    @Column(name = "CIUDAD", length = 50)
    private String ciudad;

    @Column(name = "TELEFONO", length = 20)
    private String telefono;

}
