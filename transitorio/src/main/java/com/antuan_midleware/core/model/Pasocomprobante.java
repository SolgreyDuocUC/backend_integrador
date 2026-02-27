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
@Table(name = "pasocomprobante")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pasocomprobante implements Serializable {

    @EmbeddedId
    private PasocomprobanteId id;

    @Column(name = "TipoDest", length = 10)
    private String tipodest;

    @Column(name = "NroDest")
    private Long nrodest;

}
