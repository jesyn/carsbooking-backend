package com.dh.PIG11.exceptions;

import org.springframework.http.HttpStatus;

import static com.dh.PIG11.exceptions.ErrorCode.DUPLICATED_EMAIL;
import static com.dh.PIG11.exceptions.ErrorCode.NOT_FOUND_ID;

public class BadRequestException extends CarsBookingException{
    public BadRequestException(String mensaje){
        super(DUPLICATED_EMAIL, mensaje, HttpStatus.BAD_REQUEST.value());
    }
}
