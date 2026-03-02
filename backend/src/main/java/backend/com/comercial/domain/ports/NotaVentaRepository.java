package backend.com.comercial.domain.ports;

import backend.com.comercial.domain.model.NotaVenta;
import backend.com.shared.valueobjects.DocumentNumber;

import java.util.Optional;

public interface NotaVentaRepository {
    NotaVenta save(NotaVenta notaVenta);
    Optional<NotaVenta> findById(Long id);
    Optional<NotaVenta> findByNumero(DocumentNumber numero);
}


