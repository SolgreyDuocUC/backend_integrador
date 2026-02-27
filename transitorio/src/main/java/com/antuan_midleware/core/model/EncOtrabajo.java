package com.antuan_midleware.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "enc_otrabajo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EncOtrabajo implements Serializable {

    @EmbeddedId
    private EncOtrabajoId id;

    @Column(name = "Fecha")
    private LocalDateTime fecha;

    @Column(name = "Cliente")
    private Long cliente;

    @Column(name = "Vendedor")
    private Integer vendedor;

    @Column(name = "Estado")
    private Integer estado;

    @Column(name = "OC_Cliente", length = 50)
    private String ocCliente;

    @Column(name = "Nro_Solicitud")
    private Long nroSolicitud;

    @Column(name = "Fecha_Entrega")
    private LocalDateTime fechaEntrega;

    @Column(name = "CCosto")
    private Integer ccosto;

    @Column(name = "FechaHoraTer")
    private LocalDateTime fechahorater;

    @Lob
    @Column(name = "Especificaciones")
    private String especificaciones;

    @Column(name = "Referencia", length = 50)
    private String referencia;

}
