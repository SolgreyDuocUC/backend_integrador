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
@Table(name = "bancos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bancos implements Serializable {

    @Id
    @Column(name = "IdBanco")
    private Long idbanco;

    @Column(name = "Empresa", length = 3)
    private String empresa;

    @Column(name = "Nombre", length = 60)
    private String nombre;

    @Column(name = "Codigo", length = 10)
    private String codigo;

}
