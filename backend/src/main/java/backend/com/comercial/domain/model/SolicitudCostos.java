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
public class SolicitudCostos {
    private Long id;
    private DocumentNumber numero;
    private String estado;
    private Long clienteId;
    private Long vendedorId;
    private Long especificacionTecnicaId;
    private String articuloDescripcion;
    private Boolean esMuestra;
    private Integer cantidad;
    private LocalDate fecha;
    private Money costoTotalCalculado;

    private List<SCOSTela> telas = new ArrayList<>();
    private List<SCOSAccesorio> accesorios = new ArrayList<>();
    private SCOSCostoFijo costoFijo;

    private transient List<DomainEvent> domainEvents = new ArrayList<>();

    private SolicitudCostos(DocumentNumber numero, Long clienteId, Long vendedorId, 
                            Long especificacionTecnicaId, String articuloDescripcion, 
                            Boolean esMuestra, Integer cantidad) {
        this.numero = numero;
        this.clienteId = clienteId;
        this.vendedorId = vendedorId;
        this.especificacionTecnicaId = especificacionTecnicaId;
        this.articuloDescripcion = articuloDescripcion;
        this.esMuestra = esMuestra != null ? esMuestra : false;
        this.cantidad = cantidad != null ? cantidad : 0;
        this.estado = "PENDIENTE";
        this.fecha = LocalDate.now();
        this.costoTotalCalculado = new Money(BigDecimal.ZERO, "CLP");
    }

    public SolicitudCostos(Long id, DocumentNumber numero, String estado, Long clienteId, 
                           Long vendedorId, Long especificacionTecnicaId, String articuloDescripcion, 
                           Boolean esMuestra, Integer cantidad, LocalDate fecha, Money costoTotalCalculado,
                           List<SCOSTela> telas, List<SCOSAccesorio> accesorios, SCOSCostoFijo costoFijo) {
        this.id = id;
        this.numero = numero;
        this.estado = estado;
        this.clienteId = clienteId;
        this.vendedorId = vendedorId;
        this.especificacionTecnicaId = especificacionTecnicaId;
        this.articuloDescripcion = articuloDescripcion;
        this.esMuestra = esMuestra;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.costoTotalCalculado = costoTotalCalculado;
        if (telas != null) this.telas.addAll(telas);
        if (accesorios != null) this.accesorios.addAll(accesorios);
        this.costoFijo = costoFijo;
    }

    public static SolicitudCostos crear(DocumentNumber numero, Long clienteId, Long vendedorId, 
                                        Long especificacionTecnicaId, String articuloDescripcion, 
                                        Boolean esMuestra, Integer cantidad) {
        return new SolicitudCostos(numero, clienteId, vendedorId, especificacionTecnicaId, 
                                   articuloDescripcion, esMuestra, cantidad);
    }

    public void addTela(SCOSTela tela) {
        this.telas.add(tela);
        calcularCostoTotal();
    }

    public void addAccesorio(SCOSAccesorio accesorio) {
        this.accesorios.add(accesorio);
        calcularCostoTotal();
    }

    public void setCostoFijo(SCOSCostoFijo costoFijo) {
        this.costoFijo = costoFijo;
        calcularCostoTotal();
    }

    public void calcularCostoTotal() {
        Money total = new Money(BigDecimal.ZERO, "CLP");

        for (SCOSTela tela : telas) {
            total = total.add(tela.getCostoTotal());
        }
        for (SCOSAccesorio acc : accesorios) {
            total = total.add(acc.getCostoTotal());
        }
        if (costoFijo != null) {
            total = total.add(costoFijo.getTotal());
        }

        // Si la solicitud es para X prendas, multiplicamos el costo base por ese nÃºmero de prendas.
        if (this.cantidad != null && this.cantidad > 0) {
            total = total.multiply(new BigDecimal(this.cantidad));
        }

        this.costoTotalCalculado = total;
    }

    public void aprobar() {
        this.estado = "APROBADA";
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
