package com.antuan_midleware.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "configuracion_sistema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConfiguracionSistema implements Serializable {

    @EmbeddedId
    private ConfiguracionSistemaId id;

    @Column(name = "Por_Iva")
    private BigDecimal porIva;

    @Column(name = "Ann_Activo")
    private Integer annActivo;

    @Column(name = "ControlaInv")
    private Boolean controlainv;

    @Column(name = "Rut_Nulos")
    private Long rutNulos;

    @Column(name = "Rut_Boletas")
    private Long rutBoletas;

    @Column(name = "NombreFormato", length = 50)
    private String nombreformato;

    @Column(name = "Primera")
    private LocalDateTime primera;

    @Column(name = "Segunda")
    private LocalDateTime segunda;

    @Column(name = "Tercera")
    private LocalDateTime tercera;

    @Column(name = "Local")
    private Integer local;

    @Column(name = "Bodega", length = 20)
    private String bodega;

    @Column(name = "Vendedor")
    private Integer vendedor;

    @Column(name = "PorCostoBE")
    private BigDecimal porcostobe;

    @Column(name = "Dias_Compra1")
    private Integer diasCompra1;

    @Column(name = "Dias_Compra2")
    private Integer diasCompra2;

    @Column(name = "Dias_Corte1")
    private Integer diasCorte1;

    @Column(name = "Dias_Corte2")
    private Integer diasCorte2;

    @Column(name = "Dias_Termino1")
    private Integer diasTermino1;

    @Column(name = "Dias_Termino2")
    private Integer diasTermino2;

    @Column(name = "CostoFlete")
    private BigDecimal costoflete;

    @Column(name = "CostoHilos")
    private BigDecimal costohilos;

    @Column(name = "CostoFijo")
    private BigDecimal costofijo;

    @Column(name = "CostoEtiqueta")
    private BigDecimal costoetiqueta;

    @Column(name = "CostoEmbalaje")
    private BigDecimal costoembalaje;

    @Column(name = "ValorFijo")
    private BigDecimal valorfijo;

    @Column(name = "CostosinGratif")
    private BigDecimal costosingratif;

    @Column(name = "CostoconGratif")
    private BigDecimal costocongratif;

}
