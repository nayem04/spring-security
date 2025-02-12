package com.nayem.jwtauth.common.exceptions;

public class NullPasswordException extends NullException {
    public NullPasswordException() {
    }

    public NullPasswordException(String message) {
        super(message);
    }
}
