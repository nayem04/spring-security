package com.nayem.jwtauth.common.exceptions;

public class EnumNotFoundException extends NotFoundException {
    public EnumNotFoundException() {
    }

    public EnumNotFoundException(String message) {
        super(message);
    }
}
