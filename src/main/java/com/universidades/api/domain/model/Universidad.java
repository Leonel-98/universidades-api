package com.universidades.api.domain.model;

public record Universidad(
    Long id,
    String nombre,
    String categoria,
    String web,
    String rector,
    String email,
    String acceso,
    String telefono,
    String ciudad,
    Integer numeroCarreras,
    Integer numSedes,
    Long paisId) {

    public Universidad conId(Long nuevoId) {
        return new Universidad(nuevoId, nombre, categoria, web, rector, email,
                acceso, telefono, ciudad, numeroCarreras, numSedes, paisId);
    }
}
