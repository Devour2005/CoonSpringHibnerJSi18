package com.springapp.exceptions;

public class NotUniqueRoleNameException extends DBException {

    public NotUniqueRoleNameException(String message) {
        super(message);
    }

    public NotUniqueRoleNameException(String message, Throwable cause) {
        super(message, cause);
    }
}
