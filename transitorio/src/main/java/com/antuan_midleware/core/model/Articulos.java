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
@Table(name = "articulos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Articulos implements Serializable {

    @EmbeddedId
    private ArticulosId id;

    @Column(name = "Descripcion", length = 100)
    private String descripcion;

    @Column(name = "Comentario", length = 255)
    private String comentario;

    @Column(name = "Moneda_Compra", length = 10)
    private String monedaCompra;

    @Column(name = "Moneda_venta", length = 10)
    private String monedaVenta;

    @Column(name = "Unidad_Medida", length = 10)
    private String unidadMedida;

    @Column(name = "Precio_Venta")
    private BigDecimal precioVenta;

    @Column(name = "Costo")
    private BigDecimal costo;

    @Column(name = "Costo_Ult_Compra")
    private BigDecimal costoUltCompra;

    @Column(name = "Fecha_Ult_Compra")
    private LocalDateTime fechaUltCompra;

    @Column(name = "Stock_Minimo")
    private Double stockMinimo;

    @Column(name = "Stock_Maximo")
    private Double stockMaximo;

    @Column(name = "Factor")
    private Double factor;

    @Column(name = "Procedencia", length = 1)
    private String procedencia;

    @Column(name = "Lotes")
    private Boolean lotes;

    @Column(name = "Seriales")
    private Boolean seriales;

    @Column(name = "Compras")
    private Boolean compras;

    @Column(name = "ControlaStock")
    private Boolean controlastock;

    @Column(name = "Plantilla")
    private Integer plantilla;

    @Column(name = "CodigoBarra", length = 50)
    private String codigobarra;

    @Column(name = "Activo")
    private Boolean activo;

}
