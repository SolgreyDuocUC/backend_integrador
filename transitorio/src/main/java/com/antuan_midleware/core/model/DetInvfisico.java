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
@Table(name = "det_invfisico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetInvfisico implements Serializable {

    @Id
    private Long id;

    @Column(name = "Empresa", length = 3)
    private String empresa;

    @Column(name = "Nro_Toma")
    private Long nroToma;

    @Column(name = "Producto", length = 20)
    private String producto;

    @Column(name = "Periodo")
    private Integer periodo;

    @Column(name = "Bodega", length = 20)
    private String bodega;

    @Column(name = "Stock")
    private Double stock;

    @Column(name = "Contado")
    private Double contado;

    @Column(name = "Diferencia")
    private Double diferencia;

    @Column(name = "Tipo", length = 10)
    private String tipo;

}
