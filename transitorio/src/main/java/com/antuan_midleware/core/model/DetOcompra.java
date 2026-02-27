package com.antuan_midleware.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "det_ocompra")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetOcompra implements Serializable {

    @EmbeddedId
    private DetOcompraId id;

    @Column(name = "Fecha")
    private LocalDateTime fecha;

    @Column(name = "Codigo_Producto", length = 20)
    private String codigoProducto;

    @Column(name = "Cantidad")
    private Long cantidad;

    @Column(name = "Precio_Unitario")
    private BigDecimal precioUnitario;

    @Column(name = "SubTotal")
    private BigDecimal subtotal;

    @Column(name = "Porcentaje_Des_Rec")
    private Double porcentajeDesRec;

    @Column(name = "Valor_Des_Rec")
    private BigDecimal valorDesRec;

    @Column(name = "Total_Producto")
    private BigDecimal totalProducto;

    @Column(name = "Fec_Entrega")
    private LocalDateTime fecEntrega;

    @Column(name = "Cant_Entregada")
    private Double cantEntregada;

    @Column(name = "Glosa", length = 255)
    private String glosa;

    @Column(name = "Tipo", length = 20)
    private String tipo;

    @Column(name = "Total_Recargos")
    private BigDecimal totalRecargos;

    @Column(name = "Total_Afecto")
    private BigDecimal totalAfecto;

    @Column(name = "Total_Exento")
    private BigDecimal totalExento;

    @Column(name = "Total_Iva")
    private BigDecimal totalIva;

    @Column(name = "Total_Documento")
    private BigDecimal totalDocumento;

    @Column(name = "Tipo_Comprobante", length = 255)
    private String tipoComprobante;

    @Column(name = "Nro_Comprobante")
    private Long nroComprobante;

    @Column(name = "Fec_Comprobante")
    private LocalDateTime fecComprobante;

    @Column(name = "Marca_Centra", length = 255)
    private String marcaCentra;

    @Column(name = "Por_Cumplimiento")
    private Double porCumplimiento;

    @Lob
    @Column(name = "Comentario")
    private String comentario;

    @Column(name = "Concepto")
    private Double concepto;

    @Column(name = "Modelo", length = 45)
    private String modelo;

    @Column(name = "Tela", length = 45)
    private String tela;

    @Column(name = "Composicion", length = 45)
    private String composicion;

    @Column(name = "Genero", length = 45)
    private String genero;

    @Column(name = "Color", length = 45)
    private String color;

    @Column(name = "Aplicacion", length = 45)
    private String aplicacion;

}
