package com.springapp.exceptions;

@SuppressWarnings("serial")
public class CustomException extends Exception {

    public CustomException(String message) {
        super(message);
    }

}