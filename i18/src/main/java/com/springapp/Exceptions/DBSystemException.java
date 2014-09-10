package com.springapp.Exceptions;


public class DBSystemException extends DBException {
    public DBSystemException(String message) {
        super(message);
    }

    public DBSystemException(String reason, Throwable cause) {
        super(reason, cause);
    }
}
