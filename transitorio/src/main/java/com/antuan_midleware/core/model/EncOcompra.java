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
@Table(name = "enc_ocompra")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EncOcompra implements Serializable {

    @EmbeddedId
    private EncOcompraId id;

    @Column(name = "Fecha_Documento")
    private LocalDateTime fechaDocumento;

    @Column(name = "Periodo")
    private Integer periodo;

    @Column(name = "Rut_Proveedor")
    private Long rutProveedor;

    @Column(name = "Centro_Costo")
    private Integer centroCosto;

    @Column(name = "Comprador")
    private Integer comprador;

    @Column(name = "Local")
    private Integer local;

    @Column(name = "Moneda", length = 50)
    private String moneda;

    @Column(name = "Paridad", length = 50)
    private String paridad;

    @Column(name = "Condicion_pago", length = 10)
    private String condicionPago;

    @Column(name = "Fec_Entrega")
    private LocalDateTime fecEntrega;

    @Column(name = "Glosa", length = 90)
    private String glosa;

    @Column(name = "Sub_Total")
    private BigDecimal subTotal;

    @Column(name = "Total_Descuentos")
    private BigDecimal totalDescuentos;

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

    @Column(name = "Tipo_Comprobante", length = 1)
    private String tipoComprobante;

    @Column(name = "Nro_Comprobante")
    private Long nroComprobante;

    @Column(name = "Fec_Comprobante")
    private LocalDateTime fecComprobante;

    @Column(name = "Marca_Centra", length = 1)
    private String marcaCentra;

    @Column(name = "Por_Cumplimiento")
    private Double porCumplimiento;

    @Lob
    @Column(name = "Comentario")
    private String comentario;

}
