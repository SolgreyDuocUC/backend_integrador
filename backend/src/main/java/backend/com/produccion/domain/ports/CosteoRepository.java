package backend.com.produccion.domain.ports;

import backend.com.produccion.domain.model.Costeo;
import java.util.Optional;

public interface CosteoRepository {
    Costeo save(Costeo costeo);

    Optional<Costeo> findBySolicitudCostosId(Long solicitudCostosId);

    Optional<Costeo> findById(Long id);
}
