package backend.com.comercial.repository.adapter;

import backend.com.comercial.domain.model.SolicitudCostos;
import backend.com.comercial.domain.ports.SolicitudCostosRepository;
import backend.com.comercial.repository.jpa.entity.SolicitudCostosJpaEntity;
import backend.com.comercial.repository.jpa.mapper.SolicitudCostosMapper;
import backend.com.comercial.repository.jpa.spring.SolicitudCostosJpaRepository;
import backend.com.shared.valueobjects.DocumentNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SolicitudCostosRepositoryImpl implements SolicitudCostosRepository {

    private final SolicitudCostosJpaRepository jpaRepository;
    private final SolicitudCostosMapper mapper;

    @Override
    public SolicitudCostos save(SolicitudCostos solicitudCostos) {
        SolicitudCostosJpaEntity entity = mapper.toEntity(solicitudCostos);
        if (entity == null) {
            throw new IllegalArgumentException("La entidad de Solicitud de Costos no puede ser nula");
        }
        SolicitudCostosJpaEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<SolicitudCostos> findById(Long id) {
        if (id == null)
            return Optional.empty();
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<SolicitudCostos> findByNumero(DocumentNumber numero) {
        return jpaRepository.findAll().stream()
                .filter(e -> e.getNumero().equals(numero.getValue()))
                .findFirst()
                .map(mapper::toDomain);
    }
}
