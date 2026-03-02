package backend.com.comercial.domain.ports;

import backend.com.comercial.domain.model.SolicitudCostos;
import backend.com.shared.valueobjects.DocumentNumber;

import java.util.Optional;

public interface SolicitudCostosRepository {
    SolicitudCostos save(SolicitudCostos solicitudCostos);
    Optional<SolicitudCostos> findById(Long id);
    Optional<SolicitudCostos> findByNumero(DocumentNumber numero);
}


