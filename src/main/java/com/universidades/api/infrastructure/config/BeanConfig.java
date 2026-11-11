package com.universidades.api.infrastructure.config;

import com.universidades.api.application.port.in.AuthUseCase;
import com.universidades.api.application.port.in.PaisUseCase;
import com.universidades.api.application.port.in.UniversidadUseCase;
import com.universidades.api.application.port.in.UsuarioUseCase;
import com.universidades.api.application.port.out.EmailSenderPort;
import com.universidades.api.application.port.out.PaisRepositoryPort;
import com.universidades.api.application.port.out.PasswordEncoderPort;
import com.universidades.api.application.port.out.UniversidadRepositoryPort;
import com.universidades.api.application.port.out.UsuarioRepositoryPort;
import com.universidades.api.application.service.AuthService;
import com.universidades.api.application.service.PaisService;
import com.universidades.api.application.service.UniversidadService;
import com.universidades.api.application.service.UsuarioService;
import com.universidades.api.domain.model.Usuario;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Aquí se "cablean" los casos de uso (application) con los adaptadores (infrastructure).
 * Así la capa application no depende de Spring.
 */
@Configuration
public class BeanConfig {
    @Bean
    public PaisUseCase paisUseCase(PaisRepositoryPort repository) {
        return new PaisService(repository);
    }

    @Bean
    public UniversidadUseCase universidadUseCase(UniversidadRepositoryPort repository,
                                                 PaisRepositoryPort paisRepository) {
        return new UniversidadService(repository, paisRepository);
    }

    @Bean
    public UsuarioUseCase usuarioUseCase(UsuarioRepositoryPort repository, PasswordEncoderPort encoder) {
        return new UsuarioService(repository, encoder);
    }

    @Bean
    public AuthUseCase authUseCase(UsuarioRepositoryPort repository, PasswordEncoderPort encoder,
                                   EmailSenderPort emailSender) {
        return new AuthService(repository, encoder, emailSender);
    }

    /** Crea un usuario administrador inicial si no existe. */
    @Bean
    public CommandLineRunner seedAdmin(UsuarioRepositoryPort repository, PasswordEncoderPort encoder) {
        return args -> {
            if (!repository.existsByEmail("admin@demo.com")) {
                repository.save(new Usuario(null, "Administrador", "admin@demo.com",
                    encoder.encode("admin123"), "ADMIN"));
            }
        };
    }
}
