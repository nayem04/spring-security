package com.nayem.jwtauth.common.exceptions;

public class AlreadyExistsException extends Exception {
    public AlreadyExistsException() {
    }

    public AlreadyExistsException(String message) {
        super(message);
    }
}
