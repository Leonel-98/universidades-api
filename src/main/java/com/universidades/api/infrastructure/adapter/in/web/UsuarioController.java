package com.universidades.api.infrastructure.adapter.in.web;

import com.universidades.api.application.port.in.UsuarioUseCase;
import com.universidades.api.infrastructure.adapter.in.web.dto.UsuarioRequest;
import com.universidades.api.infrastructure.adapter.in.web.dto.UsuarioResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioUseCase useCase;

    public UsuarioController(UsuarioUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> crear(@Valid @RequestBody UsuarioRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(UsuarioResponse.from(useCase.crear(request.toDomain())));
    }

    @GetMapping
    public List<UsuarioResponse> listar() {
        return useCase.listar().stream().map(UsuarioResponse::from).toList();
    }

    @GetMapping("/{id}")
    public UsuarioResponse obtener(@PathVariable Long id) {
        return UsuarioResponse.from(useCase.obtener(id));
    }

    @PutMapping("/{id}")
    public UsuarioResponse actualizar(@PathVariable Long id, @Valid @RequestBody UsuarioRequest request) {
        return UsuarioResponse.from(useCase.actualizar(id, request.toDomain()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        useCase.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // ---------- Reportes parametrizados ----------
    @GetMapping("/reportes/por-rol")
    public List<UsuarioResponse> porRol(@RequestParam String rol) {
        return useCase.reportePorRol(rol).stream().map(UsuarioResponse::from).toList();
    }

    @GetMapping("/reportes/por-nombre")
    public List<UsuarioResponse> porNombre(@RequestParam String texto) {
        return useCase.reportePorNombre(texto).stream().map(UsuarioResponse::from).toList();
    }
}
