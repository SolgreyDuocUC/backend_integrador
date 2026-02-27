package com.antuan_midleware.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "desrec_guias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DesrecGuias implements Serializable {

    @Column(name = "Empresa", length = 3)
    private String empresa;

    @Column(name = "Nro_Guia")
    private Long nroGuia;

    @Column(name = "Item")
    private Integer item;

    @Column(name = "Tipo", length = 20)
    private String tipo;

    @Column(name = "Valor")
    private BigDecimal valor;

}
