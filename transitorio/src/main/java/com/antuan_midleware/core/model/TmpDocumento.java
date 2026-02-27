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
@Table(name = "tmp_documento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TmpDocumento implements Serializable {

    @Column(name = "Empresa", length = 3)
    private String empresa;

    @Column(name = "Tipo_Documento", length = 10)
    private String tipoDocumento;

    @Column(name = "Folio")
    private Double folio;

    @Column(name = "Linea")
    private Integer linea;

    @Column(name = "Descripcion", length = 255)
    private String descripcion;

    @Column(name = "Cantidad")
    private Long cantidad;

    @Column(name = "Precio")
    private BigDecimal precio;

    @Column(name = "Equipo", length = 30)
    private String equipo;

}
