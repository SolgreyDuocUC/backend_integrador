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
@Table(name = "receptores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Receptores implements Serializable {

    @EmbeddedId
    private ReceptoresId id;

    @Column(name = "Correo", length = 80)
    private String correo;

    @Column(name = "Equipo", length = 20)
    private String equipo;

}
