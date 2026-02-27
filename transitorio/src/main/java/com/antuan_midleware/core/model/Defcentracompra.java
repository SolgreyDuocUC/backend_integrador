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
@Table(name = "defcentracompra")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Defcentracompra implements Serializable {

    @EmbeddedId
    private DefcentracompraId id;

    @Column(name = "Descripcion", length = 50)
    private String descripcion;

}
