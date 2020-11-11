package com.epam.jwd.core_final.exception;

public class DuplicateEntityException extends RuntimeException {
    private final String entityName;

    public DuplicateEntityException(String message) {
        this.entityName = message;
    }

    @Override
    public String getMessage() {
        // todo
        // you should use entityName, args (if necessary)
        return entityName;
    }
}
