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
@Table(name = "docucompra")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Docucompra implements Serializable {

    @EmbeddedId
    private DocucompraId id;

    @Column(name = "DESCRIPCION", length = 60)
    private String descripcion;

    @Column(name = "APLICA_IVA")
    private Boolean aplicaIva;

}
