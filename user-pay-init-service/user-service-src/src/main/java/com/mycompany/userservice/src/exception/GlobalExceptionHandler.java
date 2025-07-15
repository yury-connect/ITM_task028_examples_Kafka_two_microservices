package com.mycompany.userservice.src.exception;

import com.mycompany.userservice.rest.response.HttpErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;



@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


//    Пользователь не аутентифицирован
@ExceptionHandler(AuthenticationException.class)
public ResponseEntity<HttpErrorResponse> handleUnauthorizedException(AuthenticationException ex) {
    HttpErrorResponse response = HttpErrorResponse.builder()
            .status(HttpStatus.UNAUTHORIZED.value())
            .error(HttpStatus.UNAUTHORIZED.getReasonPhrase())
            .message("Пользователь не аутентифицирован: " + ex.getMessage())
            .build();
    log.info("GlobalExceptionHandler.handleUnauthorizedException: HttpErrorResponse = " + response);
    return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
}

//    @ExceptionHandler(AuthenticationException.class)
//    public ResponseEntity<HttpErrorResponse> handleUnauthorizedException() {
//        HttpErrorResponse error = new HttpErrorResponse(
//                HttpStatus.UNAUTHORIZED.value(),
//                HttpStatus.UNAUTHORIZED.getReasonPhrase(),
//                "Пользователь не аутентифицирован");
//        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
//    }
//
//    @ExceptionHandler(AccessDeniedException.class)
//    public ResponseEntity<HttpErrorResponse> handleAccessDeniedException() {
//        HttpErrorResponse error = new HttpErrorResponse(
//                HttpStatus.FORBIDDEN.value(),
//                HttpStatus.FORBIDDEN.getReasonPhrase(),
//                "Недостаточно прав пользователя");
//        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
//    }


//    @ExceptionHandler(ValidationException.class)
//    public ResponseEntity<HttpErrorResponse> handleValidationException(ValidationException e) {
//        HttpErrorResponse error = new HttpErrorResponse(
//                HttpStatus.FORBIDDEN.value(),
//                HttpStatus.FORBIDDEN.getReasonPhrase(),
//                "Некорректные данные. Проверьте введенные данные!" + e.getMessage());
//        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
//    }



    // Валидация прилетевших от пользователя данных
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HttpErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        List<HttpErrorResponse.FieldValidationError> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> new HttpErrorResponse.FieldValidationError(
                        err.getField(),
                        String.valueOf(err.getRejectedValue()),
                        err.getDefaultMessage()
                ))
                .toList();

        HttpErrorResponse response = HttpErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message("Ошибка валидации данных. Передано некорректное значение:")
                .fieldErrors(fieldErrors)
                .build();

        log.info("GlobalExceptionHandler.handleValidationException: HttpErrorResponse = " + response);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }



    // Валидация прилетевших от пользователя данных
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<HttpErrorResponse> handleNotFoundException(NotFoundException ex) {

        HttpErrorResponse response = HttpErrorResponse.builder()
                .status(ex.getHttpStatus().value())
                .error(ex.getHttpStatus().getReasonPhrase())
                .message(ex.getMessage())
                .build();

        log.info("GlobalExceptionHandler.handleNotFoundException: HttpErrorResponse = " + response);
        return new ResponseEntity<>(
                response,
                ex.getHttpStatus() == HttpStatus.NO_CONTENT ? HttpStatus.NOT_FOUND : ex.getHttpStatus());
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpErrorResponse> globalExceptionHandler(Exception e) {

        HttpErrorResponse response = HttpErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(e.getCause().getClass().getSimpleName())
                .message("Внутренняя ошибка сервера: " + e.getMessage())
                .build();

        log.info("GlobalExceptionHandler.globalExceptionHandler: response = " + response);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
