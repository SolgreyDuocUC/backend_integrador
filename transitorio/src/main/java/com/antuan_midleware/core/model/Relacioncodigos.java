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
@Table(name = "relacioncodigos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Relacioncodigos implements Serializable {

    @EmbeddedId
    private RelacioncodigosId id;

    @Column(name = "DescripCliente", length = 100)
    private String descripcliente;

    @Column(name = "CodigoInterno", length = 20)
    private String codigointerno;

    @Column(name = "Modelo", length = 45)
    private String modelo;

    @Column(name = "Composicion", length = 45)
    private String composicion;

    @Column(name = "Tela", length = 45)
    private String tela;

}
