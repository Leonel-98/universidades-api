package com.universidades.api.infrastructure.adapter.out.persistence.repository;

import com.universidades.api.infrastructure.adapter.out.persistence.entity.UniversidadEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataUniversidadRepository extends JpaRepository<UniversidadEntity, Long> {
    List<UniversidadEntity> findByCiudadIgnoreCase(String ciudad);
    List<UniversidadEntity> findByNumeroCarrerasBetween(Integer min, Integer max);
}
