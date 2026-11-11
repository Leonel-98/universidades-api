package com.universidades.api.application.service;

import com.universidades.api.application.port.in.UsuarioUseCase;
import com.universidades.api.application.port.out.PasswordEncoderPort;
import com.universidades.api.application.port.out.UsuarioRepositoryPort;
import com.universidades.api.domain.exception.RecursoDuplicadoException;
import com.universidades.api.domain.exception.RecursoNoEncontradoException;
import com.universidades.api.domain.model.Usuario;
import java.util.List;

public class UsuarioService implements UsuarioUseCase {
    private final UsuarioRepositoryPort repository;
    private final PasswordEncoderPort encoder;

    public UsuarioService(UsuarioRepositoryPort repository, PasswordEncoderPort encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public Usuario crear(Usuario usuario) {
        if (usuario.password() == null || usuario.password().isBlank()) {
            throw new IllegalArgumentException("La contraseña es obligatoria");
        }
        if (repository.existsByEmail(usuario.email())) {
            throw new RecursoDuplicadoException("Ya existe un usuario con ese email");
        }
        return repository.save(usuario.conId(null).conPassword(encoder.encode(usuario.password())));
    }

    @Override
    public Usuario obtener(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado: " + id));
    }

    @Override
    public List<Usuario> listar() {
        return repository.findAll();
    }

    @Override
    public Usuario actualizar(Long id, Usuario usuario) {
        Usuario existente = obtener(id);
        repository.findByEmail(usuario.email())
            .filter(otro -> !otro.id().equals(id))
            .ifPresent(otro -> {
                throw new RecursoDuplicadoException("Ya existe un usuario con ese email");
            });
        String hash = (usuario.password() == null || usuario.password().isBlank())
            ? existente.password()
            : encoder.encode(usuario.password());
        return repository.save(usuario.conId(id).conPassword(hash));
    }

    @Override
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RecursoNoEncontradoException("Usuario no encontrado: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public List<Usuario> reportePorRol(String rol) {
        return repository.findByRol(rol);
    }

    @Override
    public List<Usuario> reportePorNombre(String texto) {
        return repository.findByNombreContiene(texto);
    }
}
