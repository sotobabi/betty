package com.codingnomads.betty.logic.exceptions;

public class TwitterSearchFailedException extends RuntimeException {
    public TwitterSearchFailedException(String message, Throwable cause){
        super(message, cause);
    }
}
