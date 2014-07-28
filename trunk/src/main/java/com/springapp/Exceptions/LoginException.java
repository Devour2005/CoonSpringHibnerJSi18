package com.springapp.Exceptions;

public class LoginException extends DBException {
    public LoginException(String message) {
        super(message);
    }

    public LoginException(String reason, Throwable cause) {
        super(reason, cause);
    }
}
