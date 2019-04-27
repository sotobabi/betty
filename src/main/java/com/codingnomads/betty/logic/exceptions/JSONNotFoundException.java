package com.codingnomads.betty.logic.exceptions;

public class JSONNotFoundException extends RuntimeException {

    public JSONNotFoundException(String message){
        super(message);
    }

    public JSONNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

}
