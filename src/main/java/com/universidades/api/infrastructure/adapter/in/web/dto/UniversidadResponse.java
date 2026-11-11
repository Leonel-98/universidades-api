package com.universidades.api.infrastructure.adapter.in.web.dto;

import com.universidades.api.domain.model.Universidad;

public record UniversidadResponse(
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

    public static UniversidadResponse from(Universidad u) {
        return new UniversidadResponse(u.id(), u.nombre(), u.categoria(), u.web(), u.rector(),
                u.email(), u.acceso(), u.telefono(), u.ciudad(), u.numeroCarreras(), u.numSedes(), u.paisId());
    }
}
