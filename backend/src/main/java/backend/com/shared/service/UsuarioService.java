package backend.com.shared.service;

import backend.com.shared.model.Usuario;
import backend.com.shared.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(Long id) {
        if (id == null)
            return Optional.empty();
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> findByUsername(String username) {
        if (username == null || username.isBlank())
            return Optional.empty();
        return usuarioRepository.findByUsername(username);
    }

    @Transactional
    public Usuario save(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo");
        }
        // En una implementación real con Security, aquí se hashearía la password
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void deleteById(Long id) {
        if (id != null) {
            usuarioRepository.deleteById(id);
        }
    }
}
