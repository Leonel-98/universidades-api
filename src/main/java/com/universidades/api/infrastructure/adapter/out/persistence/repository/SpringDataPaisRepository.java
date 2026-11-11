package com.universidades.api.infrastructure.adapter.out.persistence.repository;

import com.universidades.api.infrastructure.adapter.out.persistence.entity.PaisEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataPaisRepository extends JpaRepository<PaisEntity, Long> {
    List<PaisEntity> findByNombreContainingIgnoreCase(String texto);
    List<PaisEntity> findByIdiomaIgnoreCase(String idioma);
}
