package com.cydeo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter

@AllArgsConstructor
public class ValidationError {
    private String errorField;
    private Object rejectedValue;
    private String reason;

//    public ValidationError(String fieldName, Object rejectedValue, String errorMessage) {
//    }
}
