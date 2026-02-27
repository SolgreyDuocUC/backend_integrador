package com.antuan_midleware.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "enc_compras")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EncCompras implements Serializable {

    @EmbeddedId
    private EncComprasId id;

    @Column(name = "Fecha")
    private LocalDateTime fecha;

    @Column(name = "Rut_Proveedor")
    private Long rutProveedor;

    @Column(name = "Total_Documento")
    private BigDecimal totalDocumento;

    @Column(name = "Estado")
    private Integer estado;

}
