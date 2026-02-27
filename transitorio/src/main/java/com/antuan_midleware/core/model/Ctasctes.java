package com.antuan_midleware.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ctasctes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ctasctes implements Serializable {

    @EmbeddedId
    private CtasctesId id;

    @Column(name = "DIGITO", length = 1)
    private String digito;

    @Column(name = "NOMBRE", length = 100)
    private String nombre;

    @Column(name = "DIRECCION", length = 100)
    private String direccion;

    @Column(name = "COMUNA", length = 50)
    private String comuna;

    @Column(name = "CIUDAD", length = 50)
    private String ciudad;

    @Column(name = "TELEFONO", length = 50)
    private String telefono;

    @Column(name = "CONTACTO", length = 50)
    private String contacto;

    @Column(name = "CORREO", length = 50)
    private String correo;

    @Column(name = "Banco", length = 45)
    private String banco;

    @Column(name = "TipoCta", length = 20)
    private String tipocta;

    @Column(name = "NroCta", length = 20)
    private String nrocta;

}
