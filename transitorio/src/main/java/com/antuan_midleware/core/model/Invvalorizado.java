package com.antuan_midleware.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "invvalorizado")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Invvalorizado implements Serializable {

    @Id
    private Long id;

    @Column(name = "Equipo", length = 20)
    private String equipo;

    @Column(name = "Empresa", length = 3)
    private String empresa;

    @Column(name = "Articulo", length = 20)
    private String articulo;

    @Column(name = "Descripcion", length = 60)
    private String descripcion;

    @Column(name = "Bodega", length = 20)
    private String bodega;

    @Column(name = "Stock")
    private Double stock;

    @Column(name = "Costo")
    private BigDecimal costo;

    @Column(name = "UltCosto")
    private BigDecimal ultcosto;

    @Column(name = "FechaUComp")
    private LocalDateTime fechaucomp;

    @Column(name = "MayorPrecCom")
    private Double mayorpreccom;

}
