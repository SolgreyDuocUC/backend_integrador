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
@Table(name = "enc_cotizacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EncCotizacion implements Serializable {

    @EmbeddedId
    private EncCotizacionId id;

    @Column(name = "Fecha")
    private LocalDateTime fecha;

    @Column(name = "Cliente")
    private Long cliente;

    @Column(name = "Vendedor")
    private Integer vendedor;

    @Column(name = "Estado")
    private Integer estado;

    @Lob
    @Column(name = "Observaciones")
    private String observaciones;

}
