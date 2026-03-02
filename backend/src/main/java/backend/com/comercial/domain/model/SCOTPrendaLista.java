package backend.com.comercial.domain.model;

import lombok.Getter;

@Getter
public class SCOTPrendaLista {
    private Long productoId;
    private String nombre;
    private Integer cantidad;
    private String talla;
    private String color;
    private String proveedorReferencia;
    private String linkReferencia;
    private String composicion;
    private String peso;
    private String observaciones;

    public SCOTPrendaLista(Long productoId, String nombre, Integer cantidad, String talla, String color, 
                           String proveedorReferencia, String linkReferencia, String composicion, 
                           String peso, String observaciones) {
        this.productoId = productoId;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.talla = talla;
        this.color = color;
        this.proveedorReferencia = proveedorReferencia;
        this.linkReferencia = linkReferencia;
        this.composicion = composicion;
        this.peso = peso;
        this.observaciones = observaciones;
    }
}
