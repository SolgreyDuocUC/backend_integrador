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
@Table(name = "enc_inventario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EncInventario implements Serializable {

    @EmbeddedId
    private EncInventarioId id;

    @Column(name = "Fecha")
    private LocalDateTime fecha;

    @Column(name = "Bodega_Origen", length = 20)
    private String bodegaOrigen;

    @Column(name = "Bodega_Destino", length = 20)
    private String bodegaDestino;

    @Column(name = "Referencia", length = 100)
    private String referencia;

    @Column(name = "Responsable", length = 60)
    private String responsable;

    @Column(name = "Estado")
    private Integer estado;

}
