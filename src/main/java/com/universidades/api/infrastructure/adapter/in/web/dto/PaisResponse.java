package com.universidades.api.infrastructure.adapter.in.web.dto;

import com.universidades.api.domain.model.Pais;

public record PaisResponse(
    Long id,
    String nombre,
    String codigo,
    String idioma,
    String moneda) {

    public static PaisResponse from(Pais p) {
        return new PaisResponse(p.id(), p.nombre(), p.codigo(), p.idioma(), p.moneda());
    }
}
