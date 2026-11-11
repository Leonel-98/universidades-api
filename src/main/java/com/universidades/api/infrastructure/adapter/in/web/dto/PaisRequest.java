package com.universidades.api.infrastructure.adapter.in.web.dto;

import com.universidades.api.domain.model.Pais;
import jakarta.validation.constraints.NotBlank;

public record PaisRequest(
    @NotBlank String nombre,
    @NotBlank String codigo,
    String idioma,
    String moneda) {

    public Pais toDomain() {
        return new Pais(null, nombre, codigo, idioma, moneda);
    }
}
