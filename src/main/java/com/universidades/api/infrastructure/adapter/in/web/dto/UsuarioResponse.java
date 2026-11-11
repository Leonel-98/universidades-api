package com.universidades.api.infrastructure.adapter.in.web.dto;

import com.universidades.api.domain.model.Usuario;

public record UsuarioResponse(Long id, String nombre, String email, String rol) {
    public static UsuarioResponse from(Usuario u) {
        return new UsuarioResponse(u.id(), u.nombre(), u.email(), u.rol());
    }
}
