package com.universidades.api.application.port.out;

import com.universidades.api.domain.model.Pais;
import java.util.List;
import java.util.Optional;

public interface PaisRepositoryPort {
    Pais save(Pais pais);
    Optional<Pais> findById(Long id);
    List<Pais> findAll();
    boolean existsById(Long id);
    void deleteById(Long id);
    List<Pais> findByNombreContiene(String texto);
    List<Pais> findByIdioma(String idioma);
}
