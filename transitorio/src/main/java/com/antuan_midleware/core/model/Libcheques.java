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
@Table(name = "libcheques")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Libcheques implements Serializable {

    @EmbeddedId
    private LibchequesId id;

    @Column(name = "Debe")
    private BigDecimal debe;

    @Column(name = "Haber")
    private BigDecimal haber;

    @Column(name = "Analisis", length = 20)
    private String analisis;

    @Column(name = "Conciliacion")
    private Boolean conciliacion;

    @Column(name = "Usua_Crea", length = 30)
    private String usuaCrea;

    @Column(name = "Fech_Crea")
    private LocalDateTime fechCrea;

    @Column(name = "Usua_Modi", length = 30)
    private String usuaModi;

    @Column(name = "Fech_Modi")
    private LocalDateTime fechModi;

}
