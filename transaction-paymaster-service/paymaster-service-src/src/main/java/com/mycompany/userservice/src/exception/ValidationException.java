package com.mycompany.userservice.src.exception;


public class ValidationException extends UserPayInitServiceException {

    public ValidationException(String message, Exception e) {
        super(message, e);
    }

    public ValidationException(String message) {
        super(message);
    }
}
