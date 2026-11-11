package com.universidades.api.application.service;

import com.universidades.api.application.port.in.PaisUseCase;
import com.universidades.api.application.port.out.PaisRepositoryPort;
import com.universidades.api.domain.exception.RecursoNoEncontradoException;
import com.universidades.api.domain.model.Pais;
import java.util.List;

public class PaisService implements PaisUseCase {
    private final PaisRepositoryPort repository;

    public PaisService(PaisRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Pais crear(Pais pais) {
        return repository.save(pais.conId(null));
    }

    @Override
    public Pais obtener(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("País no encontrado: " + id));
    }

    @Override
    public List<Pais> listar() {
        return repository.findAll();
    }

    @Override
    public Pais actualizar(Long id, Pais pais) {
        if (!repository.existsById(id)) {
            throw new RecursoNoEncontradoException("País no encontrado: " + id);
        }
        return repository.save(pais.conId(id));
    }

    @Override
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RecursoNoEncontradoException("País no encontrado: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public List<Pais> reportePorNombre(String texto) {
        return repository.findByNombreContiene(texto);
    }

    @Override
    public List<Pais> reportePorIdioma(String idioma) {
        return repository.findByIdioma(idioma);
    }
}
