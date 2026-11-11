package com.universidades.api.domain.model;

public record Usuario(
    Long id,
    String nombre,
    String email,
    String password,
    String rol) {

    public Usuario conId(Long nuevoId) {
        return new Usuario(nuevoId, nombre, email, password, rol);
    }

    public Usuario conPassword(String nuevaPassword) {
        return new Usuario(id, nombre, email, nuevaPassword, rol);
    }
}
