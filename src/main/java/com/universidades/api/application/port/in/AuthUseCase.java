package com.universidades.api.application.port.in;

import com.universidades.api.domain.model.Usuario;

public interface AuthUseCase {
    Usuario login(String email, String password);
    void recuperarClave(String email);
}
