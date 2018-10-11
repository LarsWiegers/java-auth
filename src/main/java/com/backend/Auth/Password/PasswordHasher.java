package com.backend.Auth.Password;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHasher {
    public String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
