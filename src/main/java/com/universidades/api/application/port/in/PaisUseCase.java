package com.universidades.api.application.port.in;

import com.universidades.api.domain.model.Pais;
import java.util.List;

public interface PaisUseCase {
    Pais crear(Pais pais);
    Pais obtener(Long id);
    List<Pais> listar();
    Pais actualizar(Long id, Pais pais);
    void eliminar(Long id);
    // Reportes parametrizados
    List<Pais> reportePorNombre(String texto);
    List<Pais> reportePorIdioma(String idioma);
}
