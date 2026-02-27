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
@Table(name = "pago_ocompra")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PagoOcompra implements Serializable {

    @EmbeddedId
    private PagoOcompraId id;

    @Column(name = "Fecha")
    private LocalDateTime fecha;

    @Column(name = "Vencimiento")
    private LocalDateTime vencimiento;

    @Column(name = "Valor")
    private BigDecimal valor;

}
