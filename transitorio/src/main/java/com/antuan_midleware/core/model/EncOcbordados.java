package com.antuan_midleware.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "enc_ocbordados")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EncOcbordados implements Serializable {

    @EmbeddedId
    private EncOcbordadosId id;

    @Column(name = "Fecha")
    private LocalDateTime fecha;

    @Column(name = "Proveedor")
    private Long proveedor;

    @Column(name = "Estado")
    private Integer estado;

    @Lob
    @Column(name = "Observaciones")
    private String observaciones;

    @Column(name = "Muestra", length = 100)
    private String muestra;

    @Lob
    @Column(name = "Especificacion")
    private String especificacion;

    @Column(name = "Ficha", length = 100)
    private String ficha;

}
