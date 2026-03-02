package backend.com.comercial.repository.jpa.mapper;

import backend.com.comercial.domain.model.SCOTPrendaLista;
import backend.com.comercial.domain.model.SolicitudCotizacion;
import backend.com.comercial.repository.jpa.entity.SCOTPrendaListaJpaEntity;
import backend.com.comercial.repository.jpa.entity.SolicitudCotizacionJpaEntity;
import backend.com.shared.model.Producto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class SolicitudCotizacionMapper {

    public SolicitudCotizacion toDomain(SolicitudCotizacionJpaEntity entity) {
        if (entity == null) return null;

        return new SolicitudCotizacion(
                entity.getId(),
                entity.getVentaAsociadaId(),
                entity.getEstado(),
                entity.getPrendas().stream().map(this::toPrendaDomain).collect(Collectors.toList())
        );
    }

    private SCOTPrendaLista toPrendaDomain(SCOTPrendaListaJpaEntity entity) {
        if (entity == null) return null;
        return new SCOTPrendaLista(
                entity.getProducto() != null ? entity.getProducto().getId() : null,
                entity.getNombre(),
                entity.getCantidad(),
                entity.getTalla(),
                entity.getColor(),
                entity.getProveedorReferencia(),
                entity.getLinkReferencia(),
                entity.getComposicion(),
                entity.getPeso(),
                entity.getObservaciones()
        );
    }

    public SolicitudCotizacionJpaEntity toEntity(SolicitudCotizacion domain) {
        if (domain == null) return null;

        SolicitudCotizacionJpaEntity entity = new SolicitudCotizacionJpaEntity();
        if (domain.getId() != null) {
            entity.setId(domain.getId());
        }
        entity.setVentaAsociadaId(domain.getVentaAsociadaId());
        entity.setEstado(domain.getEstado());

        domain.getPrendas().forEach(p -> {
            SCOTPrendaListaJpaEntity pe = new SCOTPrendaListaJpaEntity();
            if (p.getProductoId() != null) {
                Producto prod = new Producto(); prod.setId(p.getProductoId());
                pe.setProducto(prod);
            }
            pe.setNombre(p.getNombre());
            pe.setCantidad(p.getCantidad());
            pe.setTalla(p.getTalla());
            pe.setColor(p.getColor());
            pe.setProveedorReferencia(p.getProveedorReferencia());
            pe.setLinkReferencia(p.getLinkReferencia());
            pe.setComposicion(p.getComposicion());
            pe.setPeso(p.getPeso());
            pe.setObservaciones(p.getObservaciones());
            entity.addPrenda(pe);
        });

        return entity;
    }
}


