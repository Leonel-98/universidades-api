package com.universidades.api.infrastructure.adapter.in.web;

import com.universidades.api.application.port.in.AuthUseCase;
import com.universidades.api.infrastructure.adapter.in.web.dto.LoginRequest;
import com.universidades.api.infrastructure.adapter.in.web.dto.MensajeResponse;
import com.universidades.api.infrastructure.adapter.in.web.dto.RecuperarRequest;
import com.universidades.api.infrastructure.adapter.in.web.dto.UsuarioResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthUseCase useCase;

    public AuthController(AuthUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping("/login")
    public UsuarioResponse login(@Valid @RequestBody LoginRequest request) {
        return UsuarioResponse.from(useCase.login(request.email(), request.password()));
    }

    @PostMapping("/recuperar")
    public MensajeResponse recuperar(@Valid @RequestBody RecuperarRequest request) {
        useCase.recuperarClave(request.email());
        return new MensajeResponse("Si el email existe, recibirás una contraseña temporal en tu correo");
    }
}
