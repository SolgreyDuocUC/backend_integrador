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
@Table(name = "tal_solicostores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TalSolicostores implements Serializable {

    @EmbeddedId
    private TalSolicostoresId id;

    @Column(name = "Talla", length = 50)
    private String talla;

    @Column(name = "Cantidad")
    private Long cantidad;

    @Column(name = "CantOServ")
    private Double cantoserv;

}
