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
@Table(name = "secuencia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Secuencia implements Serializable {

    @Id
    private Long id;

    @Column(name = "EMPRESA", length = 3)
    private String empresa;

    @Column(name = "TIPOCOMP", length = 10)
    private String tipocomp;

    @Column(name = "DESDE")
    private Integer desde;

    @Column(name = "HASTA")
    private Integer hasta;

}
