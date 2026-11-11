package com.universidades.api.application.service;

import com.universidades.api.application.port.in.UniversidadUseCase;
import com.universidades.api.application.port.out.PaisRepositoryPort;
import com.universidades.api.application.port.out.UniversidadRepositoryPort;
import com.universidades.api.domain.exception.RecursoNoEncontradoException;
import com.universidades.api.domain.model.Universidad;
import java.util.List;

public class UniversidadService implements UniversidadUseCase {
    private final UniversidadRepositoryPort repository;
    private final PaisRepositoryPort paisRepository;

    public UniversidadService(UniversidadRepositoryPort repository, PaisRepositoryPort paisRepository) {
        this.repository = repository;
        this.paisRepository = paisRepository;
    }

    @Override
    public Universidad crear(Universidad universidad) {
        validarPais(universidad.paisId());
        return repository.save(universidad.conId(null));
    }

    @Override
    public Universidad obtener(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Universidad no encontrada: " + id));
    }

    @Override
    public List<Universidad> listar() {
        return repository.findAll();
    }

    @Override
    public Universidad actualizar(Long id, Universidad universidad) {
        if (!repository.existsById(id)) {
            throw new RecursoNoEncontradoException("Universidad no encontrada: " + id);
        }
        validarPais(universidad.paisId());
        return repository.save(universidad.conId(id));
    }

    @Override
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RecursoNoEncontradoException("Universidad no encontrada: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public List<Universidad> reportePorCiudad(String ciudad) {
        return repository.findByCiudad(ciudad);
    }

    @Override
    public List<Universidad> reportePorRangoCarreras(int min, int max) {
        return repository.findByNumeroCarrerasEntre(min, max);
    }

    /** La universidad debe pertenecer a un país que exista. */
    private void validarPais(Long paisId) {
        if (paisId == null || !paisRepository.existsById(paisId)) {
            throw new RecursoNoEncontradoException("País no encontrado: " + paisId);
        }
    }
}
