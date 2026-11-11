package com.universidades.api.application.port.out;

public interface PasswordEncoderPort {
    String encode(String raw);
    boolean matches(String raw, String encoded);
}
