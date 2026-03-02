package backend.com.shared.service;

import backend.com.shared.model.Departamento;
import backend.com.shared.repository.DepartamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DepartamentoService {

    private final DepartamentoRepository departamentoRepository;

    public List<Departamento> findAll() {
        return departamentoRepository.findAll();
    }

    public Optional<Departamento> findById(Long id) {
        if (id == null)
            return Optional.empty();
        return departamentoRepository.findById(id);
    }

    public Optional<Departamento> findByNombre(String nombre) {
        if (nombre == null || nombre.isBlank())
            return Optional.empty();
        return departamentoRepository.findByNombre(nombre);
    }

    @Transactional
    public Departamento save(Departamento departamento) {
        if (departamento == null) {
            throw new IllegalArgumentException("El departamento no puede ser nulo");
        }
        return departamentoRepository.save(departamento);
    }

    @Transactional
    public void deleteById(Long id) {
        if (id != null) {
            departamentoRepository.deleteById(id);
        }
    }
}
