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
@Table(name = "proveebordado")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Proveebordado implements Serializable {

    @Id
    private Long id;

    @Column(name = "EMPRESA", length = 3)
    private String empresa;

    @Column(name = "RUT")
    private Long rut;

    @Column(name = "DV", length = 1)
    private String dv;

    @Column(name = "NOMBRE", length = 100)
    private String nombre;

    @Column(name = "BANCO", length = 50)
    private String banco;

    @Column(name = "TIPO_CUENTA", length = 20)
    private String tipoCuenta;

    @Column(name = "NRO_CUENTA", length = 30)
    private String nroCuenta;

    @Column(name = "CONTACTO", length = 60)
    private String contacto;

    @Column(name = "TELEFONO", length = 20)
    private String telefono;

    @Column(name = "CORREO", length = 80)
    private String correo;

}
