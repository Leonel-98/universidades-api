package com.universidades.api.application.port.in;

import com.universidades.api.domain.model.Usuario;
import java.util.List;

public interface UsuarioUseCase {
    Usuario crear(Usuario usuario);
    Usuario obtener(Long id);
    List<Usuario> listar();
    Usuario actualizar(Long id, Usuario usuario);
    void eliminar(Long id);
    // Reportes parametrizados
    List<Usuario> reportePorRol(String rol);
    List<Usuario> reportePorNombre(String texto);
}
