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
@Table(name = "libvctos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Libvctos implements Serializable {

    @EmbeddedId
    private LibvctosId id;

    @Column(name = "Monto")
    private BigDecimal monto;

    @Column(name = "FechaPago")
    private LocalDateTime fechapago;

    @Column(name = "ValorPagado")
    private BigDecimal valorpagado;

    @Column(name = "Marca")
    private Boolean marca;

    @Column(name = "Comprobante")
    private Double comprobante;

    @Column(name = "Cuenta")
    private Double cuenta;

    @Column(name = "Nombre", length = 100)
    private String nombre;

    @Column(name = "CentroCosto")
    private Integer centrocosto;

}
