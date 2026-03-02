package backend.com.comercial.domain.model;

import backend.com.shared.events.DomainEvent;
import backend.com.shared.valueobjects.DocumentNumber;
import backend.com.shared.valueobjects.Money;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class EvaluacionNegocio {
    private Long id;
    private DocumentNumber numero;
    private Long clienteId;
    private Long vendedorId;
    private EstadoEVN estado;
    private LocalDate fechaEvaluacion;
    private Money montoTotal;
    private BigDecimal margenGanancia;
    private Money rentabilidadEsperada;
    private Long costeoId;
    private Long solicitudCotizacionId;

    // Costos adicionales
    private Money garantiaSeriedad;
    private Money garantiaFiel;
    private Money fleteEspecial;
    private Money modificacionPrenda;
    private Money tomaTallaje;
    private Money certificacion;
    private Money muestrasFisicas;

    private List<ItemEVN> items = new ArrayList<>();

    private transient List<DomainEvent> domainEvents = new ArrayList<>();

    private EvaluacionNegocio(DocumentNumber numero, Long clienteId, Long vendedorId) {
        this.numero = numero;
        this.clienteId = clienteId;
        this.vendedorId = vendedorId;
        this.estado = EstadoEVN.BORRADOR;
        this.fechaEvaluacion = LocalDate.now();

        // Init zero amounts
        this.montoTotal = new Money(BigDecimal.ZERO, "CLP");
        this.margenGanancia = BigDecimal.ZERO;
        this.rentabilidadEsperada = new Money(BigDecimal.ZERO, "CLP");
        this.garantiaSeriedad = new Money(BigDecimal.ZERO, "CLP");
        this.garantiaFiel = new Money(BigDecimal.ZERO, "CLP");
        this.fleteEspecial = new Money(BigDecimal.ZERO, "CLP");
        this.modificacionPrenda = new Money(BigDecimal.ZERO, "CLP");
        this.tomaTallaje = new Money(BigDecimal.ZERO, "CLP");
        this.certificacion = new Money(BigDecimal.ZERO, "CLP");
        this.muestrasFisicas = new Money(BigDecimal.ZERO, "CLP");
    }

    public EvaluacionNegocio(Long id, DocumentNumber numero, Long clienteId, Long vendedorId,
            EstadoEVN estado, LocalDate fechaEvaluacion,
            Money montoTotal, BigDecimal margenGanancia, Money rentabilidadEsperada,
            Money garantiaSeriedad, Money garantiaFiel, Money fleteEspecial,
            Money modificacionPrenda, Money tomaTallaje, Money certificacion,
            Money muestrasFisicas, List<ItemEVN> items,
            Long costeoId, Long solicitudCotizacionId) {
        this.id = id;
        this.numero = numero;
        this.clienteId = clienteId;
        this.vendedorId = vendedorId;
        this.estado = estado;
        this.fechaEvaluacion = fechaEvaluacion;
        this.montoTotal = montoTotal;
        this.margenGanancia = margenGanancia;
        this.rentabilidadEsperada = rentabilidadEsperada;
        this.garantiaSeriedad = garantiaSeriedad;
        this.garantiaFiel = garantiaFiel;
        this.fleteEspecial = fleteEspecial;
        this.modificacionPrenda = modificacionPrenda;
        this.tomaTallaje = tomaTallaje;
        this.certificacion = certificacion;
        this.muestrasFisicas = muestrasFisicas;
        this.costeoId = costeoId;
        this.solicitudCotizacionId = solicitudCotizacionId;
        if (items != null) {
            this.items.addAll(items);
        }
    }

    public static EvaluacionNegocio crear(DocumentNumber numero, Long clienteId, Long vendedorId,
            Long costeoId, Long solicitudCotizacionId) {
        EvaluacionNegocio evn = new EvaluacionNegocio(numero, clienteId, vendedorId);
        evn.costeoId = costeoId;
        evn.solicitudCotizacionId = solicitudCotizacionId;
        return evn;
    }

    public void addItem(ItemEVN item) {
        this.items.add(item);
        calcularTotales();
    }

    public void setCostosAdicionales(Money garantiaSeriedad, Money garantiaFiel,
            Money fleteEspecial, Money modificacionPrenda,
            Money tomaTallaje, Money certificacion,
            Money muestrasFisicas) {
        this.garantiaSeriedad = garantiaSeriedad != null ? garantiaSeriedad : this.garantiaSeriedad;
        this.garantiaFiel = garantiaFiel != null ? garantiaFiel : this.garantiaFiel;
        this.fleteEspecial = fleteEspecial != null ? fleteEspecial : this.fleteEspecial;
        this.modificacionPrenda = modificacionPrenda != null ? modificacionPrenda : this.modificacionPrenda;
        this.tomaTallaje = tomaTallaje != null ? tomaTallaje : this.tomaTallaje;
        this.certificacion = certificacion != null ? certificacion : this.certificacion;
        this.muestrasFisicas = muestrasFisicas != null ? muestrasFisicas : this.muestrasFisicas;
        calcularTotales();
    }

    public void calcularTotales() {
        Money totalItems = new Money(BigDecimal.ZERO, "CLP");
        Money costoItems = new Money(BigDecimal.ZERO, "CLP");

        for (ItemEVN item : items) {
            totalItems = totalItems.add(item.getTotal());
            costoItems = costoItems.add(item.getCostoUnitario().multiply(new BigDecimal(item.getCantidad())));
        }

        this.montoTotal = totalItems;

        Money totalCostosAdicionales = garantiaSeriedad
                .add(garantiaFiel)
                .add(fleteEspecial)
                .add(modificacionPrenda)
                .add(tomaTallaje)
                .add(certificacion)
                .add(muestrasFisicas);

        Money costoTotalGeneral = costoItems.add(totalCostosAdicionales);

        // Rentabilidad Esperada = Monto Total Venta - Costos Totales (Items +
        // Adicionales)
        BigDecimal rentabilidadValor = this.montoTotal.getAmount().subtract(costoTotalGeneral.getAmount());
        this.rentabilidadEsperada = new Money(rentabilidadValor, "CLP");

        // Margen Ganancia = (Rentabilidad Esperada / Monto Total) * 100
        if (this.montoTotal.getAmount().compareTo(BigDecimal.ZERO) > 0) {
            this.margenGanancia = rentabilidadValor
                    .divide(this.montoTotal.getAmount(), 4, java.math.RoundingMode.HALF_UP)
                    .multiply(new BigDecimal("100"));
        } else {
            this.margenGanancia = BigDecimal.ZERO;
        }
    }

    public void aprobar() {
        if (this.estado != EstadoEVN.BORRADOR && this.estado != EstadoEVN.EVALUACION) {
            throw new IllegalStateException("La EVN solo puede ser aprobada si estÃ¡ en Borrador o EvaluaciÃ³n");
        }
        this.estado = EstadoEVN.APROBADA;
    }

    public void rechazar() {
        if (this.estado != EstadoEVN.BORRADOR && this.estado != EstadoEVN.EVALUACION) {
            throw new IllegalStateException("La EVN solo puede ser rechazada si estÃ¡ en Borrador o EvaluaciÃ³n");
        }
        this.estado = EstadoEVN.RECHAZADA;
    }

    protected void addDomainEvent(DomainEvent event) {
        this.domainEvents.add(event);
    }

    public void clearDomainEvents() {
        this.domainEvents.clear();
    }

    public List<DomainEvent> getDomainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }
}
