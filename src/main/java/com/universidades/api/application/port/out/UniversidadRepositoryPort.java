package com.universidades.api.application.port.out;

import com.universidades.api.domain.model.Universidad;
import java.util.List;
import java.util.Optional;

public interface UniversidadRepositoryPort {
    Universidad save(Universidad universidad);
    Optional<Universidad> findById(Long id);
    List<Universidad> findAll();
    boolean existsById(Long id);
    void deleteById(Long id);
    List<Universidad> findByCiudad(String ciudad);
    List<Universidad> findByNumeroCarrerasEntre(int min, int max);
}
