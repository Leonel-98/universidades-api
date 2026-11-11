package com.universidades.api.infrastructure.adapter.out.persistence;

import com.universidades.api.application.port.out.UniversidadRepositoryPort;
import com.universidades.api.domain.model.Universidad;
import com.universidades.api.infrastructure.adapter.out.persistence.entity.UniversidadEntity;
import com.universidades.api.infrastructure.adapter.out.persistence.repository.SpringDataUniversidadRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class UniversidadPersistenceAdapter implements UniversidadRepositoryPort {
    private final SpringDataUniversidadRepository jpa;

    public UniversidadPersistenceAdapter(SpringDataUniversidadRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Universidad save(Universidad universidad) {
        return toDomain(jpa.save(toEntity(universidad)));
    }

    @Override
    public Optional<Universidad> findById(Long id) {
        return jpa.findById(id).map(this::toDomain);
    }

    @Override
    public List<Universidad> findAll() {
        return jpa.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public boolean existsById(Long id) {
        return jpa.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        jpa.deleteById(id);
    }

    @Override
    public List<Universidad> findByCiudad(String ciudad) {
        return jpa.findByCiudadIgnoreCase(ciudad).stream().map(this::toDomain).toList();
    }

    @Override
    public List<Universidad> findByNumeroCarrerasEntre(int min, int max) {
        return jpa.findByNumeroCarrerasBetween(min, max).stream().map(this::toDomain).toList();
    }

    private UniversidadEntity toEntity(Universidad u) {
        UniversidadEntity e = new UniversidadEntity();
        e.setId(u.id());
        e.setNombre(u.nombre());
        e.setCategoria(u.categoria());
        e.setWeb(u.web());
        e.setRector(u.rector());
        e.setEmail(u.email());
        e.setAcceso(u.acceso());
        e.setTelefono(u.telefono());
        e.setCiudad(u.ciudad());
        e.setNumeroCarreras(u.numeroCarreras());
        e.setNumSedes(u.numSedes());
        e.setPaisId(u.paisId());
        return e;
    }

    private Universidad toDomain(UniversidadEntity e) {
        return new Universidad(e.getId(), e.getNombre(), e.getCategoria(), e.getWeb(), e.getRector(),
                e.getEmail(), e.getAcceso(), e.getTelefono(), e.getCiudad(), e.getNumeroCarreras(),
                e.getNumSedes(), e.getPaisId());
    }
}
