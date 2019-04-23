package com.codingnomads.betty.logic.exceptions;

public class InvalidScoreException extends RuntimeException {

    public InvalidScoreException(String message) {
        super(message);
    }

    public InvalidScoreException(String message, Throwable cause) {
        super(message, cause);
    }
}
