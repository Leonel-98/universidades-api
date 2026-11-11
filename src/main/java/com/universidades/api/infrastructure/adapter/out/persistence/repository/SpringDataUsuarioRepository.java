package com.universidades.api.infrastructure.adapter.out.persistence.repository;

import com.universidades.api.infrastructure.adapter.out.persistence.entity.UsuarioEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataUsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByEmailIgnoreCase(String email);
    boolean existsByEmailIgnoreCase(String email);
    List<UsuarioEntity> findByRolIgnoreCase(String rol);
    List<UsuarioEntity> findByNombreContainingIgnoreCase(String texto);
}
