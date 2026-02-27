package com.antuan_midleware.empresas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "Empresa")
public class Empresa implements Serializable {

    @Id
    @Column(name = "Id_Empresa")
    private Long idEmpresa;

    @Column(name = "Razon_Social", length = 200)
    private String razonSocial;

    @Column(name = "Rut", length = 20)
    private String rut;

    @Column(name = "Activa")
    private Boolean activa;

    public Empresa() {
    }

    public Empresa(Long idEmpresa, String razonSocial, String rut, Boolean activa) {
        this.idEmpresa = idEmpresa;
        this.razonSocial = razonSocial;
        this.rut = rut;
        this.activa = activa;
    }

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public Boolean getActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }
}
