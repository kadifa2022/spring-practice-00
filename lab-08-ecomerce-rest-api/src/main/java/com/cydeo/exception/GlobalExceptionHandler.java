package com.cydeo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice  // To capture all exception
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)// as a variable we will accept notFoundException as parameter
    public ResponseEntity<ExceptionWrapper> processNotFoundException(NotFoundException ex){ // we're capturing this that we can use message inside
    //create a json body and return it
    ExceptionWrapper exceptionWrapper = new ExceptionWrapper(ex.getMessage(), HttpStatus.NOT_FOUND);
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionWrapper);
    }

}
