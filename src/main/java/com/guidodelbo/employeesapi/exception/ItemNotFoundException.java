package com.guidodelbo.employeesapi.exception;

public class ItemNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 2741513456755105542L;

    public ItemNotFoundException(final String message) {
        super(message);
    }
    
}
