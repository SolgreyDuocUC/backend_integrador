package com.antuan_midleware.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "alarmas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Alarmas implements Serializable {

    @Column(name = "Empresa", length = 3)
    private String empresa;

    @Column(name = "TipoAlarma")
    private Integer tipoalarma;

    @Column(name = "Dias")
    private Integer dias;

    @Column(name = "Receptor", length = 40)
    private String receptor;

    @Column(name = "Nro_Documento")
    private Long nroDocumento;

    @Column(name = "Referencia", length = 100)
    private String referencia;

    @Column(name = "Fecha_Cierre")
    private LocalDateTime fechaCierre;

    @Column(name = "Equipo", length = 20)
    private String equipo;

    @Column(name = "Estado")
    private Integer estado;

    @Column(name = "Fecha_Estado")
    private LocalDateTime fechaEstado;

}
