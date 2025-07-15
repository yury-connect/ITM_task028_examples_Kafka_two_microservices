package com.mycompany.userservice.src.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


@RequiredArgsConstructor
@Getter
public class UserPayInitServiceException extends RuntimeException {

    private final HttpStatus httpStatus;

    public UserPayInitServiceException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public UserPayInitServiceException(String message, Exception e) {
        super(message, e);
        httpStatus = HttpStatus.NO_CONTENT;
    }

    public UserPayInitServiceException(String message) {
        super(message);
        httpStatus = HttpStatus.NO_CONTENT;
    }
}
