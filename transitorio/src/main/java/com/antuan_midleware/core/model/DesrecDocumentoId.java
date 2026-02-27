package com.antuan_midleware.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
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
public class DesrecDocumentoId implements Serializable {
    @Column(name = "EMPRESA")
    private String empresa;

    @Column(name = "Tipo_Documento")
    private String tipoDocumento;

    @Column(name = "Folio_Inicial")
    private Double folioInicial;

    @Column(name = "Item")
    private Integer item;


}
