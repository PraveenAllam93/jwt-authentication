package com.example.air_quality_check.exceptions;

public class NoSuchFloorException extends RuntimeException{
    
    private String message;
 
    public NoSuchFloorException() {}
 
    public NoSuchFloorException(String message)
    {
        super(message);
        this.message = message;
    }
}
