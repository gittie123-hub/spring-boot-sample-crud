package com.example.demo.exceptions;

public class DuplicateFoundException extends RuntimeException{
    public DuplicateFoundException(String message){
        super(message);
    }
}
