package com.cydeo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice  // To capture all exception
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)// as a variable we will accept notFoundException as parameter
    public ResponseEntity<ExceptionWrapper> processNotFoundException(NotFoundException ex){ // we're capturing this that we can use message inside
    //create a json body and return it
    ExceptionWrapper exceptionWrapper = new ExceptionWrapper(ex.getMessage(), HttpStatus.NOT_FOUND);
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionWrapper);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)// what ever this error happen return this.
    public ResponseEntity<ExceptionWrapper> processValidationError(MethodArgumentNotValidException ex){ // accepting
        return handleMethodArgumentNotValid(ex);
    }
    protected ResponseEntity<ExceptionWrapper> handleMethodArgumentNotValid(MethodArgumentNotValidException ex){
        ExceptionWrapper exceptionWrapper = new ExceptionWrapper("Invalid Input", HttpStatus.BAD_REQUEST);
        List<ValidationError> validationErrors = new ArrayList<>();

        for(ObjectError error : ex.getBindingResult().getAllErrors()){

            String fieldName= ((FieldError) error).getField();
            Object rejectedValue =((FieldError) error).getRejectedValue();
            String errorMessage = error.getDefaultMessage();

            ValidationError validationError = new ValidationError(fieldName, rejectedValue,errorMessage);
            validationErrors.add(validationError);
        }
        exceptionWrapper.setValidationErrorList(validationErrors);
        exceptionWrapper.setErrorCount(validationErrors.size());
        return new ResponseEntity<>(exceptionWrapper,exceptionWrapper.getHttpStatus());
    }
    @ExceptionHandler({Exception.class, RuntimeException.class, Throwable.class})
    public ResponseEntity<ExceptionWrapper> genericExceptionHandler(){
        return new ResponseEntity<>(new ExceptionWrapper("Action failed: An error occurred!", HttpStatus.INTERNAL_SERVER_ERROR),HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(CurrencyTypeNotFoundException.class)// as a variable we will accept notFoundException as parameter
    public ResponseEntity<ExceptionWrapper> handleCurrencyTypeNotFoundException(CurrencyTypeNotFoundException ex){ // we're capturing this that we can use message inside
        //create a json body and return it
        ExceptionWrapper exceptionWrapper = new ExceptionWrapper(ex.getMessage(), HttpStatus.NOT_FOUND);
        exceptionWrapper.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionWrapper);
    }




}
