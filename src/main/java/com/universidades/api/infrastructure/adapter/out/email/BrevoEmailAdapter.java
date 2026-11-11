package com.universidades.api.infrastructure.adapter.out.email;

import com.universidades.api.application.port.out.EmailSenderPort;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

/**
 * Envía correos por la API HTTP de Brevo (gratis, sin tarjeta).
 * Se usa HTTP y no SMTP porque los servicios gratuitos de hosting suelen bloquear SMTP.
 */
@Component
public class BrevoEmailAdapter implements EmailSenderPort {
    private static final Logger log = LoggerFactory.getLogger(BrevoEmailAdapter.class);
    private final String apiKey;
    private final String from;
    private final String fromName;
    private final RestClient client = RestClient.create();

    public BrevoEmailAdapter(@Value("${app.brevo.api-key:}") String apiKey,
                             @Value("${app.mail.from:}") String from,
                             @Value("${app.mail.from-name:Universidades App}") String fromName) {
        this.apiKey = apiKey;
        this.from = from;
        this.fromName = fromName;
    }

    @Override
    public void enviar(String destinatario, String asunto, String cuerpo) {
        if (apiKey.isBlank() || from.isBlank()) {
            log.warn("BREVO_API_KEY/MAIL_FROM no configurados. Correo NO enviado a {}: {}", destinatario, cuerpo);
            return;
        }
        try {
            Map<String, Object> body = Map.of(
                "sender", Map.of("name", fromName, "email", from),
                "to", List.of(Map.of("email", destinatario)),
                "subject", asunto,
                "textContent", cuerpo);
            client.post()
                .uri("https://api.brevo.com/v3/smtp/email")
                .header("api-key", apiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .body(body)
                .retrieve()
                .toBodilessEntity();
            log.info("Correo enviado a {}", destinatario);
        } catch (Exception e) {
            log.error("Error enviando correo a {}: {}", destinatario, e.getMessage());
        }
    }
}
