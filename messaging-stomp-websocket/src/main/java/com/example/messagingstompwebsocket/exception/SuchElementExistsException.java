package com.example.messagingstompwebsocket.exception;

import java.io.Serial;

public class SuchElementExistsException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public SuchElementExistsException(String message) { super(message); }
}
