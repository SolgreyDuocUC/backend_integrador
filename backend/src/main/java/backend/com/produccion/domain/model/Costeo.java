package backend.com.produccion.domain.model;

import backend.com.shared.valueobjects.DocumentNumber;
import backend.com.shared.valueobjects.Money;
import lombok.Getter;

import java.math.BigDecimal;

import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Costeo {
    private Long id;
    private DocumentNumber numero;
    private Long solicitudCostosId;

    // Detalle de costos (Money para precios, BigDecimal para cantidades)
    private Money costoHilos;
    private Money costoManoObra;
    private Money costoEtiquetas;
    private Money costoEmbalaje;
    private Money costoFlete;

    private BigDecimal porcentajeCostoFijo;

    // Cintas y Vivos
    private Money precioCinta1;
    private BigDecimal cantidadCinta1;
    private Money precioCinta2;
    private BigDecimal cantidadCinta2;
    private Money vivoReflectivo;
    private BigDecimal cantidadVivo;

    private Money costoTotalMateriaPrima;
    private BigDecimal margenBrutoSugerido;
    private Money precioVentaSugerido;

    public Costeo(Long id, DocumentNumber numero, Long solicitudCostosId, Money costoHilos, Money costoManoObra,
            Money costoEtiquetas, Money costoEmbalaje, Money costoFlete,
            BigDecimal porcentajeCostoFijo, Money precioCinta1, BigDecimal cantidadCinta1,
            Money precioCinta2, BigDecimal cantidadCinta2, Money vivoReflectivo,
            BigDecimal cantidadVivo, Money costoTotalMateriaPrima,
            BigDecimal margenBrutoSugerido, Money precioVentaSugerido) {
        this.id = id;
        this.numero = numero;
        this.solicitudCostosId = solicitudCostosId;
        this.costoHilos = costoHilos;
        this.costoManoObra = costoManoObra;
        this.costoEtiquetas = costoEtiquetas;
        this.costoEmbalaje = costoEmbalaje;
        this.costoFlete = costoFlete;
        this.porcentajeCostoFijo = porcentajeCostoFijo;
        this.precioCinta1 = precioCinta1;
        this.cantidadCinta1 = cantidadCinta1;
        this.precioCinta2 = precioCinta2;
        this.cantidadCinta2 = cantidadCinta2;
        this.vivoReflectivo = vivoReflectivo;
        this.cantidadVivo = cantidadVivo;
        this.costoTotalMateriaPrima = costoTotalMateriaPrima;
        this.margenBrutoSugerido = margenBrutoSugerido;
        this.precioVentaSugerido = precioVentaSugerido;
    }
}
