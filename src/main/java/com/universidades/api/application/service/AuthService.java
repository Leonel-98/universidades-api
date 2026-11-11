package com.universidades.api.application.service;

import com.universidades.api.application.port.in.AuthUseCase;
import com.universidades.api.application.port.out.EmailSenderPort;
import com.universidades.api.application.port.out.PasswordEncoderPort;
import com.universidades.api.application.port.out.UsuarioRepositoryPort;
import com.universidades.api.domain.exception.CredencialesInvalidasException;
import com.universidades.api.domain.model.Usuario;
import java.security.SecureRandom;

public class AuthService implements AuthUseCase {
    private static final String CARACTERES = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz23456789";
    private final UsuarioRepositoryPort repository;
    private final PasswordEncoderPort encoder;
    private final EmailSenderPort emailSender;
    private final SecureRandom random = new SecureRandom();

    public AuthService(UsuarioRepositoryPort repository, PasswordEncoderPort encoder, EmailSenderPort emailSender) {
        this.repository = repository;
        this.encoder = encoder;
        this.emailSender = emailSender;
    }

    @Override
    public Usuario login(String email, String password) {
        Usuario usuario = repository.findByEmail(email)
            .orElseThrow(() -> new CredencialesInvalidasException("Email o contraseña incorrectos"));
        if (!encoder.matches(password, usuario.password())) {
            throw new CredencialesInvalidasException("Email o contraseña incorrectos");
        }
        return usuario;
    }

    @Override
    public void recuperarClave(String email) {
        // Si el email no existe no revelamos nada (seguridad)
        repository.findByEmail(email).ifPresent(usuario -> {
            String temporal = generarClaveTemporal(8);
            repository.save(usuario.conPassword(encoder.encode(temporal)));
            String cuerpo = """
                Hola %s,

                Tu nueva contraseña temporal es: %s

                Inicia sesión y cámbiala lo antes posible.
                """.formatted(usuario.nombre(), temporal);
            emailSender.enviar(
                usuario.email(),
                "Recuperación de contraseña - Universidades App",
                cuerpo);
        });
    }

    private String generarClaveTemporal(int longitud) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < longitud; i++) {
            sb.append(CARACTERES.charAt(random.nextInt(CARACTERES.length())));
        }
        return sb.toString();
    }
}
