package com.cydeo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
@Component
public class MapperUtil {

    private final ModelMapper modelMapper;

    public MapperUtil(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    //one way to make generics ModelMapper
     public <T>  T convert(Object objectToBeConverted, T convertedObject){//casting
        return modelMapper.map(objectToBeConverted, (Type) convertedObject.getClass());

     }
    //another way to make mapper generics
//    public<T> T convert(Object objectToBeConverted, Class<T> convertedOject){
//        return modelMapper.map(objectToBeConverted, convertedOject);
//    }


}
