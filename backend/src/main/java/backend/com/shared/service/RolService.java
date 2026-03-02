package backend.com.shared.service;

import backend.com.shared.model.Rol;
import backend.com.shared.repository.RolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RolService {

    private final RolRepository rolRepository;

    public List<Rol> findAll() {
        return rolRepository.findAll();
    }

    public Optional<Rol> findById(Long id) {
        if (id == null)
            return Optional.empty();
        return rolRepository.findById(id);
    }

    public Optional<Rol> findByNombre(String nombre) {
        if (nombre == null || nombre.isBlank())
            return Optional.empty();
        return rolRepository.findByNombre(nombre);
    }

    @Transactional
    public Rol save(Rol rol) {
        if (rol == null) {
            throw new IllegalArgumentException("El rol no puede ser nulo");
        }
        return rolRepository.save(rol);
    }

    @Transactional
    public void deleteById(Long id) {
        if (id != null) {
            rolRepository.deleteById(id);
        }
    }
}
