package com.company.exception;

public class InvalidJwtAuth extends RuntimeException{
    public InvalidJwtAuth(String message) {
        super(message);
    }
}
