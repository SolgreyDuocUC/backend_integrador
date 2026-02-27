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
@Table(name = "enc_guias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EncGuias implements Serializable {

    @EmbeddedId
    private EncGuiasId id;

    @Column(name = "Fecha")
    private LocalDateTime fecha;

    @Column(name = "Destinatario", length = 100)
    private String destinatario;

    @Column(name = "Estado")
    private Integer estado;

}
