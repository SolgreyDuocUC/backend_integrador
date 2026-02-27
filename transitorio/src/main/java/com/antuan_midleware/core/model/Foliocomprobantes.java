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
@Table(name = "foliocomprobantes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Foliocomprobantes implements Serializable {

    @EmbeddedId
    private FoliocomprobantesId id;

    @Column(name = "Estado")
    private Integer estado;

    @Column(name = "Usuario", length = 30)
    private String usuario;

    @Column(name = "Timestamp")
    private LocalDateTime timestamp;

    @Column(name = "Equipo", length = 30)
    private String equipo;

}
