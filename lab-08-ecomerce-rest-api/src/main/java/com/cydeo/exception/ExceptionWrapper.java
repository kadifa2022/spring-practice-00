package com.cydeo.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionWrapper { // this is my costume class
    public String message;
    public HttpStatus httpStatus;
    private LocalDateTime timestamp;

    // additional fields for validation error class
    private Integer errorCount;
    private List<ValidationError> validationErrorList;// to keep the error count and put in a list of information inside the response wrapper


    // create constructor for message and httpStatus


    public ExceptionWrapper(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
