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
@Table(name = "enc_nvtas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EncNvtas implements Serializable {

    @EmbeddedId
    private EncNvtasId id;

    @Column(name = "Fecha")
    private LocalDateTime fecha;

    @Column(name = "Cliente")
    private Long cliente;

    @Column(name = "Estado")
    private Integer estado;

    @Column(name = "Total")
    private BigDecimal total;

}
