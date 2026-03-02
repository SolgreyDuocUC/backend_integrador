package backend.com.shared.service;

import backend.com.shared.model.Producto;
import backend.com.shared.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductoService {

    private final ProductoRepository productoRepository;

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    public Optional<Producto> findById(Long id) {
        if (id == null)
            return Optional.empty();
        return productoRepository.findById(id);
    }

    @Transactional
    public Producto save(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }
        return productoRepository.save(producto);
    }

    @Transactional
    public void deleteById(Long id) {
        if (id != null) {
            productoRepository.deleteById(id);
        }
    }
}
