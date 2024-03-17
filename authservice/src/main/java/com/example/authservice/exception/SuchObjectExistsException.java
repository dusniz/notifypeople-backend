package com.example.authservice.exception;

import java.io.Serial;

public class SuchObjectExistsException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public SuchObjectExistsException(String message) {
        super(message);
    }
}
