package ar.com.app.people.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { IllegalArgumentException.class })
    protected ResponseEntity<ResponseErrorMessage> handleIllegalArgumentException(RuntimeException ex) {
        return new ResponseEntity<>(new ResponseErrorMessage(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<ResponseErrorMessage> handleException(Exception ex) {
        return new ResponseEntity<>(new ResponseErrorMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
