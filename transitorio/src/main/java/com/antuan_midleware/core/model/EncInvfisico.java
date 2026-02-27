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
@Table(name = "enc_invfisico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EncInvfisico implements Serializable {

    @EmbeddedId
    private EncInvfisicoId id;

    @Column(name = "Fecha")
    private LocalDateTime fecha;

    @Column(name = "Bodega", length = 20)
    private String bodega;

    @Column(name = "Estado")
    private Integer estado;

    @Column(name = "Responsable", length = 60)
    private String responsable;

}
