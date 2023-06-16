package com.cydeo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter// based on getter and setter Jackson is converting ResponseWrapper to JSON
@Setter
@NoArgsConstructor
public class ResponseWrapper {// we are creating this class to customize Json output (api body)


    private boolean success;
    private String message;
    private Integer code;
    private Object data;// object is for any data

    // we create 2 constructors if we delete object we can't see data with one constructor
    public ResponseWrapper(String message, Object data){
        this.message = message;
        this.data = data;
        this.code = HttpStatus.OK.value();
        this.success = true;

    }

    public ResponseWrapper(String message){
        this.message = message;
        this.code = HttpStatus.OK.value();
        this.success = true;
    }
}
