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
@Table(name = "enc_documento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EncDocumento implements Serializable {

    @EmbeddedId
    private EncDocumentoId id;

    @Column(name = "Fecha")
    private LocalDateTime fecha;

    @Column(name = "Rut_Cliente")
    private Long rutCliente;

    @Column(name = "Vendedor")
    private Integer vendedor;

    @Column(name = "Estado")
    private Integer estado;

    @Column(name = "Sol_Compra")
    private Double solCompra;

    @Column(name = "Sol_Costo")
    private BigDecimal solCosto;

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

    @Column(name = "Glosa", length = 200)
    private String glosa;

    @Column(name = "Nro_Comprobante")
    private Long nroComprobante;

}
