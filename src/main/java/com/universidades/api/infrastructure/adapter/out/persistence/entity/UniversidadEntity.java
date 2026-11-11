package com.universidades.api.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "universidades")
public class UniversidadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String categoria;
    private String web;
    private String rector;
    private String email;
    private String acceso;
    private String telefono;
    private String ciudad;
    private Integer numeroCarreras;
    private Integer numSedes;

    @Column(name = "pais_id")
    private Long paisId;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public String getWeb() { return web; }
    public void setWeb(String web) { this.web = web; }
    public String getRector() { return rector; }
    public void setRector(String rector) { this.rector = rector; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getAcceso() { return acceso; }
    public void setAcceso(String acceso) { this.acceso = acceso; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
    public Integer getNumeroCarreras() { return numeroCarreras; }
    public void setNumeroCarreras(Integer numeroCarreras) { this.numeroCarreras = numeroCarreras; }
    public Integer getNumSedes() { return numSedes; }
    public void setNumSedes(Integer numSedes) { this.numSedes = numSedes; }
    public Long getPaisId() { return paisId; }
    public void setPaisId(Long paisId) { this.paisId = paisId; }
}
