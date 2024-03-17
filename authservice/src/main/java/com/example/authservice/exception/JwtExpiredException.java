package com.example.authservice.exception;

import java.io.Serial;

public class JwtExpiredException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public JwtExpiredException(String message) { super(message); }
}
