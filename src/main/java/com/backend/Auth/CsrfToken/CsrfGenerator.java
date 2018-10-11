package com.backend.Auth.CsrfToken;

import java.util.UUID;

public class CsrfGenerator {
    public String generate() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
