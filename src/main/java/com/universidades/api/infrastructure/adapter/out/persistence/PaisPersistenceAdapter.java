package com.universidades.api.infrastructure.adapter.out.persistence;

import com.universidades.api.application.port.out.PaisRepositoryPort;
import com.universidades.api.domain.model.Pais;
import com.universidades.api.infrastructure.adapter.out.persistence.entity.PaisEntity;
import com.universidades.api.infrastructure.adapter.out.persistence.repository.SpringDataPaisRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class PaisPersistenceAdapter implements PaisRepositoryPort {
    private final SpringDataPaisRepository jpa;

    public PaisPersistenceAdapter(SpringDataPaisRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Pais save(Pais pais) {
        return toDomain(jpa.save(toEntity(pais)));
    }

    @Override
    public Optional<Pais> findById(Long id) {
        return jpa.findById(id).map(this::toDomain);
    }

    @Override
    public List<Pais> findAll() {
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
    public List<Pais> findByNombreContiene(String texto) {
        return jpa.findByNombreContainingIgnoreCase(texto).stream().map(this::toDomain).toList();
    }

    @Override
    public List<Pais> findByIdioma(String idioma) {
        return jpa.findByIdiomaIgnoreCase(idioma).stream().map(this::toDomain).toList();
    }

    private PaisEntity toEntity(Pais p) {
        PaisEntity e = new PaisEntity();
        e.setId(p.id());
        e.setNombre(p.nombre());
        e.setCodigo(p.codigo());
        e.setIdioma(p.idioma());
        e.setMoneda(p.moneda());
        return e;
    }

    private Pais toDomain(PaisEntity e) {
        return new Pais(e.getId(), e.getNombre(), e.getCodigo(), e.getIdioma(), e.getMoneda());
    }
}
