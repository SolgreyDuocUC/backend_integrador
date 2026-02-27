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
@Table(name = "req_oservicios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReqOservicios implements Serializable {

    @EmbeddedId
    private ReqOserviciosId id;

    @Column(name = "Cantidad")
    private Long cantidad;

    @Lob
    @Column(name = "Descripcion")
    private String descripcion;

    @Column(name = "Talla", length = 20)
    private String talla;

    @Column(name = "Vinculos", length = 100)
    private String vinculos;

}
