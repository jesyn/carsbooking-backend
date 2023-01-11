package com.dh.PIG11.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class RestResponseEntityExceptionHandler  {

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Map<String, Object> resourceNotFoundException(RuntimeException ex, WebRequest request) {
        if(ex instanceof CarsBookingException){
            return buildException((CarsBookingException) ex);
        }

        return buildException(new CarsBookingException(ErrorCode.INVALID_PAYLOAD, ex.getMessage(), HttpStatus.NOT_FOUND.value()));
    }
    @ExceptionHandler(value = {BadRequestException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String, Object>  badRequestException(RuntimeException ex, WebRequest request) {
        if(ex instanceof CarsBookingException){
            return buildException((CarsBookingException) ex);
        }

        return buildException(new CarsBookingException(ErrorCode.DUPLICATED_EMAIL, ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    private Map<String, Object> buildException(CarsBookingException ex){
        Map<String, Object> error = new HashMap<>();
        error.put("message", ex.getMessage());
        error.put("status_code", ex.getStatusCode());
        error.put("error_code", ex.getErrorCode().name().toLowerCase());
        return error;

    }

}
