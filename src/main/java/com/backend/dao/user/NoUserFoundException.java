package com.backend.dao.user;

public class NoUserFoundException extends Throwable {
    public NoUserFoundException(String message) {
        super(message);
    }
}
