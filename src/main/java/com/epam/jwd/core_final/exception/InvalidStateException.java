package com.epam.jwd.core_final.exception;

public class InvalidStateException extends RuntimeException {
    // todo
    public InvalidStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidStateException(String message) {
        super(message);
    }
}
