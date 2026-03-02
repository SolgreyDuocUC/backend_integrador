package backend.com.produccion.domain.model;

import backend.com.shared.valueobjects.DocumentNumber;
import lombok.Getter;

@Getter
public class OrdenTrabajo {
    private Long id;
    private DocumentNumber numero;
    private Long notaVentaId;
    private Integer nroItem; // Referencia al ítem específico de la NV
    private TipoOT tipo;
    private EstadoOT estado;
    private String observaciones;

    public OrdenTrabajo(Long id, DocumentNumber numero, Long notaVentaId, Integer nroItem,
            TipoOT tipo, EstadoOT estado, String observaciones) {
        this.id = id;
        this.numero = numero;
        this.notaVentaId = notaVentaId;
        this.nroItem = nroItem;
        this.tipo = tipo != null ? tipo : TipoOT.INTERNA;
        this.estado = estado != null ? estado : EstadoOT.PENDIENTE;
        this.observaciones = observaciones;
    }

    public static OrdenTrabajo crearParaItem(DocumentNumber numero, Long notaVentaId, Integer nroItem, String obs) {
        return new OrdenTrabajo(null, numero, notaVentaId, nroItem, TipoOT.INTERNA, EstadoOT.PENDIENTE, obs);
    }

    public void iniciar() {
        if (this.estado != EstadoOT.PENDIENTE) {
            throw new IllegalStateException("Solo se pueden iniciar OTs pendientes");
        }
        this.estado = EstadoOT.EN_PROCESO;
    }

    public void finalizar() {
        this.estado = EstadoOT.FINALIZADA;
    }
}
