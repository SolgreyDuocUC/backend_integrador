package com.antuan_midleware.core.model;

import jakarta.persistence.Column;
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
@Table(name = "corrcomprobante")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Corrcomprobante implements Serializable {

    @Column(name = "EMPRESA", length = 3)
    private String empresa;

    @Column(name = "NUMCOMP")
    private Long numcomp;

    @Column(name = "TIPOCOMP", length = 10)
    private String tipocomp;

    @Column(name = "LINEACOM")
    private Integer lineacom;

    @Column(name = "ANN_COMP")
    private Integer annComp;

    @Column(name = "CUENTA")
    private Double cuenta;

    @Column(name = "DEBE")
    private BigDecimal debe;

    @Column(name = "HABER")
    private BigDecimal haber;

    @Column(name = "USUA_CREA", length = 30)
    private String usuaCrea;

    @Column(name = "FECH_CREA")
    private LocalDateTime fechCrea;

}
