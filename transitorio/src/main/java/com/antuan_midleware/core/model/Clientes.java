package com.antuan_midleware.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Clientes implements Serializable {

    @EmbeddedId
    private ClientesId id;

    @Column(name = "DV_RUT", length = 1)
    private String dvRut;

    @Column(name = "RAZON_SOCIAL", length = 100)
    private String razonSocial;

    @Column(name = "SIGLA", length = 60)
    private String sigla;

    @Column(name = "GIRO", length = 50)
    private String giro;

    @Column(name = "DIRECCION", length = 100)
    private String direccion;

    @Column(name = "COMUNA", length = 50)
    private String comuna;

    @Column(name = "CIUDAD", length = 50)
    private String ciudad;

    @Column(name = "TELEFONO", length = 15)
    private String telefono;

    @Column(name = "FAX", length = 15)
    private String fax;

    @Column(name = "CORREO", length = 50)
    private String correo;

    @Column(name = "CODIGO_VENDEDOR")
    private Long codigoVendedor;

    @Column(name = "CONDICION_PAGO")
    private Integer condicionPago;

    @Column(name = "LISTA_PRECIO")
    private Integer listaPrecio;

    @Column(name = "CONTACTO", length = 60)
    private String contacto;

    @Column(name = "RUT_COMPRADOR", length = 12)
    private String rutComprador;

    @Column(name = "NOMBRE_COMPRADOR", length = 50)
    private String nombreComprador;

    @Column(name = "CRED_MAXIMO")
    private Double credMaximo;

    @Column(name = "VIGEN_CREDITO")
    private LocalDateTime vigenCredito;

    @Column(name = "POR_DEUDA")
    private Double porDeuda;

    @Column(name = "MAX_RETRASO")
    private Integer maxRetraso;

    @Column(name = "ULT_COMPRA")
    private LocalDateTime ultCompra;

    @Column(name = "BODEGA", length = 20)
    private String bodega;

}
