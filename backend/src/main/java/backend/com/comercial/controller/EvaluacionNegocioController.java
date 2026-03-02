package backend.com.comercial.controller;

import backend.com.comercial.service.dto.CrearEVNCommand;
import backend.com.comercial.service.dto.EVNResponse;
import backend.com.comercial.service.CrearEVNUseCase;
import backend.com.comercial.domain.ports.EvaluacionNegocioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comercial/evaluaciones")
@RequiredArgsConstructor
public class EvaluacionNegocioController {

    private final CrearEVNUseCase crearEVNUseCase;
    private final EvaluacionNegocioRepository repository;

    @PostMapping
    public ResponseEntity<EVNResponse> crear(@RequestBody CrearEVNCommand command) {
        return ResponseEntity.ok(crearEVNUseCase.ejecutar(command));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EVNResponse> obtenerPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(EVNResponse::fromDomain)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
