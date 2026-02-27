package com.antuan_midleware.core.model;

import jakarta.persistence.Column;
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
@Table(name = "tmp_libromayor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TmpLibromayor implements Serializable {

    @Column(name = "Empresa", length = 3)
    private String empresa;

    @Column(name = "Cuenta")
    private Double cuenta;

    @Column(name = "Fecha")
    private LocalDateTime fecha;

    @Column(name = "Glosa", length = 100)
    private String glosa;

    @Column(name = "Debe")
    private BigDecimal debe;

    @Column(name = "Haber")
    private BigDecimal haber;

    @Column(name = "Saldo")
    private BigDecimal saldo;

    @Column(name = "Equipo", length = 30)
    private String equipo;

}
