package com.example.springbootapp.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(Long id){
        super("Resource not found for id: " + id);
    }
}
