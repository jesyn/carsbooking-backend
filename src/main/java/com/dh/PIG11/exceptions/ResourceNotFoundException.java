package com.dh.PIG11.exceptions;

import org.springframework.http.HttpStatus;

import static com.dh.PIG11.exceptions.ErrorCode.NOT_FOUND_ID;

public class ResourceNotFoundException extends CarsBookingException{
    public ResourceNotFoundException(String mensaje){
        super(NOT_FOUND_ID, mensaje, HttpStatus.NOT_FOUND.value());
    }
}
