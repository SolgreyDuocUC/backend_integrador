package com.antuan_midleware.empresas.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "Parametro")
public class Parametro implements Serializable {

    @EmbeddedId
    private ParametroId id;

    @Column(name = "Valor", length = 255)
    private String valor;

    @Column(name = "Comentario", length = 255)
    private String comentario;

    public Parametro() {
    }

    public Parametro(ParametroId id, String valor, String comentario) {
        this.id = id;
        this.valor = valor;
        this.comentario = comentario;
    }

    public ParametroId getId() {
        return id;
    }

    public void setId(ParametroId id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
