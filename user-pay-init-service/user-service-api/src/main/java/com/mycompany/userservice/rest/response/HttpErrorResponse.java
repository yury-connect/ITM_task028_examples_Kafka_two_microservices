package com.mycompany.userservice.rest.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HttpErrorResponse {
    private int status;
    private String error;
    private String message;
    private List<FieldValidationError> fieldErrors;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class FieldValidationError {
        private String field;
        private String rejectedValue;
        private String message;
    }
}
