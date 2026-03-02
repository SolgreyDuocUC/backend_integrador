package backend.com.adquisiciones.domain.model;

import backend.com.shared.events.DomainEvent;
import backend.com.shared.valueobjects.DocumentNumber;
import backend.com.shared.valueobjects.TenantId;
import backend.com.adquisiciones.domain.events.SCCreadaEvent;
import backend.com.adquisiciones.domain.events.SCAprobadaEvent;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class SolicitudCompra {
    private Long id;
    private DocumentNumber numero;
    private TenantId tenantId;
    private Long notaVentaId;
    private EstadoSC estado;
    private LocalDate fechaEmision;
    private List<ItemSC> items = new ArrayList<>();
    
    private transient List<DomainEvent> domainEvents = new ArrayList<>();

    private SolicitudCompra(DocumentNumber numero, TenantId tenantId, Long notaVentaId, List<ItemSC> items) {
        this.numero = numero;
        this.tenantId = tenantId;
        this.notaVentaId = notaVentaId;
        this.estado = EstadoSC.PENDIENTE;
        this.fechaEmision = LocalDate.now();
        if (items != null) {
            this.items.addAll(items);
        }
    }

    public SolicitudCompra(Long id, DocumentNumber numero, TenantId tenantId, Long notaVentaId, EstadoSC estado, LocalDate fechaEmision, List<ItemSC> items) {
        this.id = id;
        this.numero = numero;
        this.tenantId = tenantId;
        this.notaVentaId = notaVentaId;
        this.estado = estado;
        this.fechaEmision = fechaEmision;
        if (items != null) {
            this.items.addAll(items);
        }
    }

    public static SolicitudCompra crear(DocumentNumber numero, TenantId tenantId, Long notaVentaId, List<ItemSC> items) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("La Solicitud de Compra debe tener al menos un item");
        }
        SolicitudCompra sc = new SolicitudCompra(numero, tenantId, notaVentaId, items);
        sc.addDomainEvent(new SCCreadaEvent(sc.getId()));
        return sc;
    }

    public void aprobar() {
        if (this.estado != EstadoSC.PENDIENTE) {
            throw new IllegalStateException("Solo las Solicitudes Pendientes pueden ser aprobadas");
        }
        this.estado = EstadoSC.APROBADA;
        this.addDomainEvent(new SCAprobadaEvent(this.id));
    }

    public boolean puedeGenerarOC() {
        return this.estado == EstadoSC.APROBADA || this.estado == EstadoSC.PARCIAL;
    }

    private void addDomainEvent(DomainEvent event) {
        this.domainEvents.add(event);
    }

    public void clearDomainEvents() {
        this.domainEvents.clear();
    }

    public List<DomainEvent> getDomainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }
}
