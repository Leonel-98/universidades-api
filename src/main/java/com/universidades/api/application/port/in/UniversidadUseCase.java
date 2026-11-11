package com.universidades.api.application.port.in;

import com.universidades.api.domain.model.Universidad;
import java.util.List;

public interface UniversidadUseCase {
    Universidad crear(Universidad universidad);
    Universidad obtener(Long id);
    List<Universidad> listar();
    Universidad actualizar(Long id, Universidad universidad);
    void eliminar(Long id);
    // Reportes parametrizados
    List<Universidad> reportePorCiudad(String ciudad);
    List<Universidad> reportePorRangoCarreras(int min, int max);
}
