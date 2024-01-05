package com.cydeo.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
public class ExceptionWrapper { // this is my costume class
    public String message;
    public HttpStatus httpStatus;

    // create constructor for message and httpStatus


    public ExceptionWrapper(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
