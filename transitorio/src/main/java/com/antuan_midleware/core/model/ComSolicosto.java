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
@Table(name = "com_solicosto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComSolicosto implements Serializable {

    @EmbeddedId
    private ComSolicostoId id;

    @Column(name = "Fecha")
    private LocalDateTime fecha;

    @Column(name = "CCosto")
    private Integer ccosto;

    @Column(name = "TipoComp", length = 20)
    private String tipocomp;

    @Column(name = "Codigo", length = 20)
    private String codigo;

    @Column(name = "Descrip", length = 100)
    private String descrip;

    @Column(name = "Cant_Soli")
    private Double cantSoli;

    @Column(name = "Prec_Soli")
    private Double precSoli;

    @Column(name = "Cant_Bodega")
    private Double cantBodega;

    @Column(name = "Cant_Comp")
    private Double cantComp;

    @Column(name = "Prec_Comp")
    private Double precComp;

    @Column(name = "Fec_Entrega")
    private LocalDateTime fecEntrega;

    @Column(name = "Proveedor")
    private Long proveedor;

    @Column(name = "OrdCompra")
    private Double ordcompra;

    @Column(name = "LinCompra")
    private Integer lincompra;

    @Lob
    @Column(name = "Comentario")
    private String comentario;

    @Column(name = "Tipo", length = 20)
    private String tipo;

    @Column(name = "Marca_Centra", length = 255)
    private String marcaCentra;

}
