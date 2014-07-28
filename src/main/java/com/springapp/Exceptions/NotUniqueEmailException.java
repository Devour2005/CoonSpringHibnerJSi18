package com.springapp.Exceptions;

public class NotUniqueEmailException extends DBException {
    public NotUniqueEmailException(String message) {
        super(message);
    }

    public NotUniqueEmailException(String reason, Throwable cause) {
        super(reason, cause);
    }
}
