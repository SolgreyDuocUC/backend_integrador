package com.antuan_midleware.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tmp_saldos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TmpSaldos implements Serializable {

    @Id
    private Long id;

    @Column(name = "Empresa", length = 3)
    private String empresa;

    @Column(name = "Producto", length = 20)
    private String producto;

    @Column(name = "Bodega", length = 20)
    private String bodega;

    @Column(name = "Stock")
    private Double stock;

    @Column(name = "Equipo", length = 30)
    private String equipo;

}
