package com.nayem.jwtauth.common.exceptions;

public class InvalidCredentialException extends Exception {
    public InvalidCredentialException() {
    }

    public InvalidCredentialException(String msg) {
        super(msg);
    }
}
