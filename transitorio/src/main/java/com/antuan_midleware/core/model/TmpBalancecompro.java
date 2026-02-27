package com.antuan_midleware.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tmp_balancecompro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TmpBalancecompro implements Serializable {

    @Id
    private Long id;

    @Column(name = "Empresa", length = 3)
    private String empresa;

    @Column(name = "Cuenta")
    private Double cuenta;

    @Column(name = "Nombre", length = 50)
    private String nombre;

    @Column(name = "Debe")
    private BigDecimal debe;

    @Column(name = "Haber")
    private BigDecimal haber;

    @Column(name = "Equipo", length = 30)
    private String equipo;

}
