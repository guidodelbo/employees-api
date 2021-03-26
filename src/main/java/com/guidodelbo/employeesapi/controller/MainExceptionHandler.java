package com.guidodelbo.employeesapi.controller;

import com.guidodelbo.employeesapi.exception.ItemNotFoundException;
import com.guidodelbo.employeesapi.model.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class MainExceptionHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleItemNotFoundException(ItemNotFoundException ex) {
        ErrorResponse response = new ErrorResponse(NOT_FOUND.value(), ex.getMessage());
        Optional.ofNullable(ex.getCause()).map(Throwable::getCause).ifPresent(c -> response.putExtra("cause", c));
        return new ResponseEntity<>(response, NOT_FOUND);
    }

}
