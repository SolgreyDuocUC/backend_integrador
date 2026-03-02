package backend.com.produccion.infrastructure.persistence.jpa.mapper;

import backend.com.produccion.domain.model.OrdenTrabajo;
import backend.com.produccion.infrastructure.persistence.jpa.entity.OrdenTrabajoJpaEntity;
import backend.com.shared.valueobjects.DocumentNumber;
import org.springframework.stereotype.Component;

@Component
public class OrdenTrabajoMapper {

    public OrdenTrabajo toDomain(OrdenTrabajoJpaEntity entity) {
        if (entity == null)
            return null;

        return new OrdenTrabajo(
                entity.getId(),
                new DocumentNumber(entity.getNumero()),
                entity.getNotaVentaId(),
                entity.getNroItem(),
                entity.getTipo(),
                entity.getEstado(),
                entity.getObservaciones());
    }

    public OrdenTrabajoJpaEntity toJpaEntity(OrdenTrabajo domain) {
        if (domain == null)
            return null;

        OrdenTrabajoJpaEntity entity = new OrdenTrabajoJpaEntity();
        entity.setId(domain.getId());
        entity.setNumero(domain.getNumero().getValue());
        entity.setNotaVentaId(domain.getNotaVentaId());
        entity.setNroItem(domain.getNroItem());
        entity.setTipo(domain.getTipo());
        entity.setEstado(domain.getEstado());
        entity.setObservaciones(domain.getObservaciones());

        return entity;
    }
}
