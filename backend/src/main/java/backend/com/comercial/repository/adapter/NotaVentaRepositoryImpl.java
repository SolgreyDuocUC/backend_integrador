package backend.com.comercial.repository.adapter;

import backend.com.comercial.domain.model.NotaVenta;
import backend.com.comercial.domain.ports.NotaVentaRepository;
import backend.com.comercial.repository.jpa.entity.NotaVentaJpaEntity;
import backend.com.comercial.repository.jpa.mapper.NotaVentaMapper;
import backend.com.comercial.repository.jpa.spring.NotaVentaJpaRepository;
import backend.com.shared.valueobjects.DocumentNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class NotaVentaRepositoryImpl implements NotaVentaRepository {

    private final NotaVentaJpaRepository jpaRepository;
    private final NotaVentaMapper mapper;

    @Override
    public NotaVenta save(NotaVenta notaVenta) {
        NotaVentaJpaEntity entity = mapper.toEntity(notaVenta);
        if (entity == null) {
            throw new IllegalArgumentException("La entidad de Nota de Venta no puede ser nula");
        }
        NotaVentaJpaEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<NotaVenta> findById(Long id) {
        if (id == null)
            return Optional.empty();
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<NotaVenta> findByNumero(DocumentNumber numero) {
        return jpaRepository.findAll().stream()
                .filter(e -> e.getNumero().equals(numero.getValue()))
                .findFirst()
                .map(mapper::toDomain);
    }
}
