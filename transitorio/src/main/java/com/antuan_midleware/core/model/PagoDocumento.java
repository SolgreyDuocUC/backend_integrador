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
@Table(name = "pago_documento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PagoDocumento implements Serializable {

    @EmbeddedId
    private PagoDocumentoId id;

    @Column(name = "FormaPago", length = 30)
    private String formapago;

    @Column(name = "Monto")
    private BigDecimal monto;

    @Column(name = "Vencimiento")
    private LocalDateTime vencimiento;

}
