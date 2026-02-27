package com.antuan_midleware.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class LibvctosId implements Serializable {
    @Column(name = "Empresa")
    private String empresa;

    @Column(name = "Local")
    private Integer local;

    @Column(name = "TipoDocumento")
    private String tipodocumento;

    @Column(name = "NroDocumento")
    private Long nrodocumento;

    @Column(name = "RutProveedor")
    private Long rutproveedor;

    @Column(name = "Fecha")
    private LocalDateTime fecha;


}
