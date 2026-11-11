package com.universidades.api.infrastructure.adapter.in.web.dto;

import com.universidades.api.domain.model.Universidad;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UniversidadRequest(
    @NotBlank String nombre,
    String categoria,
    String web,
    String rector,
    @Email String email,
    String acceso,
    String telefono,
    String ciudad,
    @Min(0) Integer numeroCarreras,
    @Min(0) Integer numSedes,
    @NotNull Long paisId) {

    public Universidad toDomain() {
        return new Universidad(null, nombre, categoria, web, rector, email,
                acceso, telefono, ciudad, numeroCarreras, numSedes, paisId);
    }
}
