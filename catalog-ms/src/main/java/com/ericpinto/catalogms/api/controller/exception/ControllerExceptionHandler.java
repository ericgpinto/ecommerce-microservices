package com.ericpinto.catalogms.api.controller.exception;

import com.ericpinto.catalogms.exception.ObjectInvalidException;
import com.ericpinto.catalogms.exception.ObjectNotFoundException;
import com.ericpinto.catalogms.exception.UniqueViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<CustomError> objectNotFound(ObjectNotFoundException exception){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(CustomError.builder()
                        .message(exception.getMessage()).build());
    }

    @ExceptionHandler({ObjectInvalidException.class, UniqueViolationException.class})
    public ResponseEntity<CustomError> badRequest(Exception exception){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(CustomError.builder()
                        .message(exception.getMessage()).build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomError> genericException(Exception exception){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(CustomError.builder()
                        .message(exception.getMessage()).build());
    }
}
