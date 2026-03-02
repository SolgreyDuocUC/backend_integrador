package backend.com.comercial.domain.ports;

import backend.com.comercial.domain.model.SolicitudCotizacion;

import java.util.Optional;

public interface SolicitudCotizacionRepository {
    SolicitudCotizacion save(SolicitudCotizacion solicitudCotizacion);
    Optional<SolicitudCotizacion> findById(Long id);
}


