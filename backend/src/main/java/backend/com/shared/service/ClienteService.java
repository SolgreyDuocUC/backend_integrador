package backend.com.shared.service;

import backend.com.shared.model.Cliente;
import backend.com.shared.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> findById(Long id) {
        if (id == null)
            return Optional.empty();
        return clienteRepository.findById(id);
    }

    @Transactional
    public Cliente save(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo");
        }
        return clienteRepository.save(cliente);
    }

    @Transactional
    public void deleteById(Long id) {
        if (id != null) {
            clienteRepository.deleteById(id);
        }
    }
}
