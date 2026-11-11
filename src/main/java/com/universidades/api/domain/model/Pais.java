package com.universidades.api.domain.model;

public record Pais(
    Long id,
    String nombre,
    String codigo,
    String idioma,
    String moneda) {

    public Pais conId(Long nuevoId) {
        return new Pais(nuevoId, nombre, codigo, idioma, moneda);
    }
}
