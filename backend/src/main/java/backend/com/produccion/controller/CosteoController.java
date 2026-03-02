package backend.com.produccion.controller;

import backend.com.produccion.application.usecase.GestionarCosteoUseCase;
import backend.com.produccion.domain.model.Costeo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/produccion/costeos")
@RequiredArgsConstructor
public class CosteoController {

    private final GestionarCosteoUseCase gestionarCosteoUseCase;

    @GetMapping("/scos/{scosId}")
    public ResponseEntity<Costeo> getBySCOS(@PathVariable Long scosId) {
        return gestionarCosteoUseCase.obtenerPorSCOS(scosId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Costeo crear(@RequestBody Costeo costeo) {
        return gestionarCosteoUseCase.registrarCosteo(costeo);
    }
}
