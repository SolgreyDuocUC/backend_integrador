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
@Table(name = "enc_oservicios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EncOservicios implements Serializable {

    @EmbeddedId
    private EncOserviciosId id;

    @Column(name = "Fecha")
    private LocalDateTime fecha;

    @Column(name = "Rut_Taller")
    private Long rutTaller;

    @Column(name = "Nro_OP")
    private Long nroOp;

    @Column(name = "TipoDoc", length = 20)
    private String tipodoc;

    @Column(name = "Fec_Entrega")
    private LocalDateTime fecEntrega;

    @Column(name = "Valor")
    private BigDecimal valor;

    @Lob
    @Column(name = "Obser")
    private String obser;

    @Column(name = "ContactoTaller", length = 60)
    private String contactotaller;

    @Column(name = "TelefonoTaller", length = 20)
    private String telefonotaller;

    @Column(name = "CortesPrendas")
    private Double cortesprendas;

    @Column(name = "TipoOrigen", length = 20)
    private String tipoorigen;

    @Column(name = "Cliente")
    private Long cliente;

    @Column(name = "Prenda", length = 50)
    private String prenda;

    @Column(name = "Por_Cumplimiento")
    private Double porCumplimiento;

}
