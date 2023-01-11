package com.dh.PIG11.exceptions;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@ControllerAdvice
public class GlobalExceptions {
    private static final Logger logger= Logger.getLogger(GlobalExceptions.class);

    /*@ExceptionHandler(ResourceNotFoundException.class)
    //este método es como el catch
    public ResponseEntity<String>manejoErrorResourseNotFound(ResourceNotFoundException rnfe){
        logger.error(rnfe.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Atención! Error."+ rnfe.getMessage()+"\n"+rnfe.getStackTrace());
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String>manejoErrorBadRequest(BadRequestException bre){
        logger.error(bre.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Atención! Error."+ bre.getMessage()+"\n"+bre.getStackTrace());
    }*/


}
