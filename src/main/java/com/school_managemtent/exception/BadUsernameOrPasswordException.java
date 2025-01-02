package com.school_managemtent.exception;

public class BadUsernameOrPasswordException extends RuntimeException {
    public BadUsernameOrPasswordException(String message) {
        super(message);
    }
}