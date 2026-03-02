package backend.com.comercial.domain.model;

import backend.com.shared.events.DomainEvent;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class SolicitudCotizacion {
    private Long id;
    private Long ventaAsociadaId; // NotaVenta Reference, if any
    private String estado;
    
    // In the legacy code, the mapping was exactly SCOT_PrendaLista -> SolicitudCotizacion
    private List<SCOTPrendaLista> prendas = new ArrayList<>();
    
    private transient List<DomainEvent> domainEvents = new ArrayList<>();

    public SolicitudCotizacion(Long ventaAsociadaId) {
        this.ventaAsociadaId = ventaAsociadaId;
        this.estado = "PENDIENTE";
    }

    public SolicitudCotizacion(Long id, Long ventaAsociadaId, String estado, List<SCOTPrendaLista> prendas) {
        this.id = id;
        this.ventaAsociadaId = ventaAsociadaId;
        this.estado = estado;
        if (prendas != null) {
            this.prendas.addAll(prendas);
        }
    }

    public void addPrenda(SCOTPrendaLista prenda) {
        this.prendas.add(prenda);
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
