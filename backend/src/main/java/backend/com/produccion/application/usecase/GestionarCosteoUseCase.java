package backend.com.produccion.application.usecase;

import backend.com.produccion.domain.model.Costeo;
import backend.com.produccion.domain.ports.CosteoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GestionarCosteoUseCase {

    private final CosteoRepository repository;

    @Transactional
    public Costeo registrarCosteo(Costeo costeo) {
        if (costeo == null)
            throw new IllegalArgumentException("El costeo no puede ser nulo");
        return repository.save(costeo);
    }

    public Optional<Costeo> obtenerPorSCOS(Long scosId) {
        return repository.findBySolicitudCostosId(scosId);
    }
}
