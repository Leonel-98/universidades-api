package com.universidades.api.infrastructure.adapter.in.web.dto;

import com.universidades.api.domain.model.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioRequest(
    @NotBlank String nombre,
    @NotBlank @Email String email,
    @Size(min = 6) String password,
    @NotBlank String rol) {

    public Usuario toDomain() {
        return new Usuario(null, nombre, email, password, rol);
    }
}
