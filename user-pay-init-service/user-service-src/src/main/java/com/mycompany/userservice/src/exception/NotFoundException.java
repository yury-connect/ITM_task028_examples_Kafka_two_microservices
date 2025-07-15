package com.mycompany.userservice.src.exception;

import com.mycompany.userservice.rest.response.HttpErrorResponse;
import org.springframework.http.HttpStatus;


public class NotFoundException extends UserPayInitServiceException {

    public NotFoundException(String message, HttpStatus status) {
        super(message);
    }
}
