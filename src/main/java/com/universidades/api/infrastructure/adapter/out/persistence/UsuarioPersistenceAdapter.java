package com.universidades.api.infrastructure.adapter.out.persistence;

import com.universidades.api.application.port.out.UsuarioRepositoryPort;
import com.universidades.api.domain.model.Usuario;
import com.universidades.api.infrastructure.adapter.out.persistence.entity.UsuarioEntity;
import com.universidades.api.infrastructure.adapter.out.persistence.repository.SpringDataUsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class UsuarioPersistenceAdapter implements UsuarioRepositoryPort {
    private final SpringDataUsuarioRepository jpa;

    public UsuarioPersistenceAdapter(SpringDataUsuarioRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Usuario save(Usuario usuario) {
        return toDomain(jpa.save(toEntity(usuario)));
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return jpa.findById(id).map(this::toDomain);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return jpa.findByEmailIgnoreCase(email).map(this::toDomain);
    }

    @Override
    public List<Usuario> findAll() {
        return jpa.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public boolean existsById(Long id) {
        return jpa.existsById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpa.existsByEmailIgnoreCase(email);
    }

    @Override
    public void deleteById(Long id) {
        jpa.deleteById(id);
    }

    @Override
    public List<Usuario> findByRol(String rol) {
        return jpa.findByRolIgnoreCase(rol).stream().map(this::toDomain).toList();
    }

    @Override
    public List<Usuario> findByNombreContiene(String texto) {
        return jpa.findByNombreContainingIgnoreCase(texto).stream().map(this::toDomain).toList();
    }

    private UsuarioEntity toEntity(Usuario u) {
        UsuarioEntity e = new UsuarioEntity();
        e.setId(u.id());
        e.setNombre(u.nombre());
        e.setEmail(u.email());
        e.setPassword(u.password());
        e.setRol(u.rol());
        return e;
    }

    private Usuario toDomain(UsuarioEntity e) {
        return new Usuario(e.getId(), e.getNombre(), e.getEmail(), e.getPassword(), e.getRol());
    }
}
