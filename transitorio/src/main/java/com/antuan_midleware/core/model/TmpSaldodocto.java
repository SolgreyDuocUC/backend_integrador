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
@Table(name = "tmp_saldodocto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TmpSaldodocto implements Serializable {

    @Id
    private Long id;

    @Column(name = "Empresa", length = 3)
    private String empresa;

    @Column(name = "Tipo_Documento", length = 10)
    private String tipoDocumento;

    @Column(name = "NroDocumento")
    private Long nrodocumento;

    @Column(name = "Saldo")
    private BigDecimal saldo;

    @Column(name = "Equipo", length = 30)
    private String equipo;

}
