package com.ericpinto.catalogms.exception;

public class UniqueViolationException extends RuntimeException {

    public UniqueViolationException(String message) {
        super(message);
    }
}
