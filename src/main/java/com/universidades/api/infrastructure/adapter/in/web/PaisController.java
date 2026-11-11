package com.universidades.api.infrastructure.adapter.in.web;

import com.universidades.api.application.port.in.PaisUseCase;
import com.universidades.api.infrastructure.adapter.in.web.dto.PaisRequest;
import com.universidades.api.infrastructure.adapter.in.web.dto.PaisResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/paises")
public class PaisController {
    private final PaisUseCase useCase;

    public PaisController(PaisUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<PaisResponse> crear(@Valid @RequestBody PaisRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(PaisResponse.from(useCase.crear(request.toDomain())));
    }

    @GetMapping
    public List<PaisResponse> listar() {
        return useCase.listar().stream().map(PaisResponse::from).toList();
    }

    @GetMapping("/{id}")
    public PaisResponse obtener(@PathVariable Long id) {
        return PaisResponse.from(useCase.obtener(id));
    }

    @PutMapping("/{id}")
    public PaisResponse actualizar(@PathVariable Long id, @Valid @RequestBody PaisRequest request) {
        return PaisResponse.from(useCase.actualizar(id, request.toDomain()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        useCase.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // ---------- Reportes parametrizados ----------
    @GetMapping("/reportes/por-nombre")
    public List<PaisResponse> porNombre(@RequestParam String texto) {
        return useCase.reportePorNombre(texto).stream().map(PaisResponse::from).toList();
    }

    @GetMapping("/reportes/por-idioma")
    public List<PaisResponse> porIdioma(@RequestParam String idioma) {
        return useCase.reportePorIdioma(idioma).stream().map(PaisResponse::from).toList();
    }
}
