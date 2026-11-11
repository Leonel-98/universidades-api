package com.universidades.api.application.port.out;

public interface EmailSenderPort {
    void enviar(String destinatario, String asunto, String cuerpo);
}
