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
@Table(name = "documento_inv")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentoInv implements Serializable {

    @EmbeddedId
    private DocumentoInvId id;

    @Column(name = "Tipo_GF", length = 5)
    private String tipoGf;

    @Column(name = "Bodega", length = 20)
    private String bodega;

    @Column(name = "Codigo_Producto", length = 20)
    private String codigoProducto;

    @Column(name = "Cantidad")
    private Long cantidad;

    @Column(name = "Precio")
    private BigDecimal precio;

    @Column(name = "Costo")
    private BigDecimal costo;

    @Column(name = "Lote", length = 30)
    private String lote;

    @Column(name = "Vencimiento")
    private LocalDateTime vencimiento;

}
