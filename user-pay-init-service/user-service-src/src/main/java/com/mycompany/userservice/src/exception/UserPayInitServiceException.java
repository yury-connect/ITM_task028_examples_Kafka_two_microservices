package com.mycompany.userservice.src.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public class UserPayInitServiceException extends RuntimeException {

    private final HttpStatus httpStatus;

    public UserPayInitServiceException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
