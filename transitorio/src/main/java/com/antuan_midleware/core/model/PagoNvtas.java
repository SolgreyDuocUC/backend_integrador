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
@Table(name = "pago_nvtas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PagoNvtas implements Serializable {

    @EmbeddedId
    private PagoNvtasId id;

    @Column(name = "Monto")
    private BigDecimal monto;

    @Column(name = "Fecha")
    private LocalDateTime fecha;

}
