package backend.com.comercial.repository.adapter;

import backend.com.comercial.domain.model.SolicitudCotizacion;
import backend.com.comercial.domain.ports.SolicitudCotizacionRepository;
import backend.com.comercial.repository.jpa.entity.SolicitudCotizacionJpaEntity;
import backend.com.comercial.repository.jpa.mapper.SolicitudCotizacionMapper;
import backend.com.comercial.repository.jpa.spring.SolicitudCotizacionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SolicitudCotizacionRepositoryImpl implements SolicitudCotizacionRepository {

    private final SolicitudCotizacionJpaRepository jpaRepository;
    private final SolicitudCotizacionMapper mapper;

    @Override
    public SolicitudCotizacion save(SolicitudCotizacion solicitudCotizacion) {
        SolicitudCotizacionJpaEntity entity = mapper.toEntity(solicitudCotizacion);
        if (entity == null) {
            throw new IllegalArgumentException("La entidad de Solicitud de Cotización no puede ser nula");
        }
        SolicitudCotizacionJpaEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<SolicitudCotizacion> findById(Long id) {
        if (id == null)
            return Optional.empty();
        return jpaRepository.findById(id).map(mapper::toDomain);
    }
}
