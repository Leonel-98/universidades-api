package com.universidades.api.infrastructure.adapter.in.web;

import com.universidades.api.application.port.in.UniversidadUseCase;
import com.universidades.api.infrastructure.adapter.in.web.dto.UniversidadRequest;
import com.universidades.api.infrastructure.adapter.in.web.dto.UniversidadResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/universidades")
public class UniversidadController {
    private final UniversidadUseCase useCase;

    public UniversidadController(UniversidadUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<UniversidadResponse> crear(@Valid @RequestBody UniversidadRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(UniversidadResponse.from(useCase.crear(request.toDomain())));
    }

    @GetMapping
    public List<UniversidadResponse> listar() {
        return useCase.listar().stream().map(UniversidadResponse::from).toList();
    }

    @GetMapping("/{id}")
    public UniversidadResponse obtener(@PathVariable Long id) {
        return UniversidadResponse.from(useCase.obtener(id));
    }

    @PutMapping("/{id}")
    public UniversidadResponse actualizar(@PathVariable Long id, @Valid @RequestBody UniversidadRequest request) {
        return UniversidadResponse.from(useCase.actualizar(id, request.toDomain()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        useCase.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // ---------- Reportes parametrizados ----------
    @GetMapping("/reportes/por-ciudad")
    public List<UniversidadResponse> porCiudad(@RequestParam String ciudad) {
        return useCase.reportePorCiudad(ciudad).stream().map(UniversidadResponse::from).toList();
    }

    @GetMapping("/reportes/por-carreras")
    public List<UniversidadResponse> porCarreras(@RequestParam int min, @RequestParam int max) {
        return useCase.reportePorRangoCarreras(min, max).stream().map(UniversidadResponse::from).toList();
    }
}
