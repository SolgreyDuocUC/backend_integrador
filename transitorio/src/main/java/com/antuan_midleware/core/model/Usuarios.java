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
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuarios implements Serializable {

    @EmbeddedId
    private UsuariosId id;

    @Column(name = "DIGITO", length = 1)
    private String digito;

    @Column(name = "NOMBRE_USUARIO", length = 50)
    private String nombreUsuario;

    @Column(name = "CLAVE_USUARIO", length = 50)
    private String claveUsuario;

    @Column(name = "igual")
    private String igual;

}
