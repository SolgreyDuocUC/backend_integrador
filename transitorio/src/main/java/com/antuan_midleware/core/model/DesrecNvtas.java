package com.antuan_midleware.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "desrec_nvtas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DesrecNvtas implements Serializable {

    @EmbeddedId
    private DesrecNvtasId id;

    @Column(name = "Tipo", length = 20)
    private String tipo;

    @Column(name = "Valor")
    private BigDecimal valor;

}
