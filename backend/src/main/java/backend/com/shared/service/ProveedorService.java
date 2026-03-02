package backend.com.shared.service;

import backend.com.shared.model.Proveedor;
import backend.com.shared.repository.ProveedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProveedorService {

    private final ProveedorRepository proveedorRepository;

    public List<Proveedor> findAll() {
        return proveedorRepository.findAll();
    }

    public Optional<Proveedor> findById(Long id) {
        if (id == null)
            return Optional.empty();
        return proveedorRepository.findById(id);
    }

    @Transactional
    public Proveedor save(Proveedor proveedor) {
        if (proveedor == null) {
            throw new IllegalArgumentException("El proveedor no puede ser nulo");
        }
        return proveedorRepository.save(proveedor);
    }

    @Transactional
    public void deleteById(Long id) {
        if (id != null) {
            proveedorRepository.deleteById(id);
        }
    }
}
