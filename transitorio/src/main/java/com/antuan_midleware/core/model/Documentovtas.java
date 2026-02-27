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
@Table(name = "documentovtas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Documentovtas implements Serializable {

    @EmbeddedId
    private DocumentovtasId id;

    @Column(name = "DESCRIPCION", length = 60)
    private String descripcion;

    @Column(name = "APLICA_IVA")
    private Boolean aplicaIva;

    @Column(name = "ES_FACTURA")
    private Boolean esFactura;

    @Column(name = "ES_BOLETA")
    private Boolean esBoleta;

    @Column(name = "ES_NOTA")
    private Boolean esNota;

}
