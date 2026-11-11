package com.universidades.api.application.port.out;

import com.universidades.api.domain.model.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepositoryPort {
    Usuario save(Usuario usuario);
    Optional<Usuario> findById(Long id);
    Optional<Usuario> findByEmail(String email);
    List<Usuario> findAll();
    boolean existsById(Long id);
    boolean existsByEmail(String email);
    void deleteById(Long id);
    List<Usuario> findByRol(String rol);
    List<Usuario> findByNombreContiene(String texto);
}
