package backend.com.adquisiciones.controller;

import backend.com.adquisiciones.application.dto.CrearSCCommand;
import backend.com.adquisiciones.application.dto.SCResponse;
import backend.com.adquisiciones.application.usecase.CrearSCUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/adquisiciones/solicitudes-compra")
@RequiredArgsConstructor
@Tag(name = "Adquisiciones - Solicitud de Compra", description = "Operaciones sobre Solicitudes de Compra")
public class SolicitudCompraController {

    private final CrearSCUseCase crearSCUseCase;

    @PostMapping
    @Operation(summary = "Crear Solicitud de Compra", description = "Crea una SC aislada mediante DDD.")
    public ResponseEntity<SCResponse> crear(@RequestBody CrearSCCommand command) {
        SCResponse response = crearSCUseCase.ejecutar(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
