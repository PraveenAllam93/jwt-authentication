package com.example.air_quality_check.exceptions;

public class EmptyDataException extends RuntimeException {
    
    private String message;
 
    public EmptyDataException() {}
 
    public EmptyDataException(String message)
    {
        super();
        this.message = message;
    }
    
}
