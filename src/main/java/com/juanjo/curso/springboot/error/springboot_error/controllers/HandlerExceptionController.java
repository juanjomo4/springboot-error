package com.juanjo.curso.springboot.error.springboot_error.controllers;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.juanjo.curso.springboot.error.springboot_error.models.ErrorModels;

@RestControllerAdvice
public class HandlerExceptionController {
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ErrorModels> divisionByZero(Exception ex) {
        ErrorModels error = new ErrorModels();
        error.setDate(new Date());
        error.setError("ArithmeticException");
        error.setMessage("Error al dividir por cero");
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        // return ResponseEntity.internalServerError().body(error);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorModels> notFoundEx(NoHandlerFoundException ex) {
        ErrorModels error = new ErrorModels();
        error.setDate(new Date());
        error.setError("NoHandlerFoundException, Api Rest no encontrada");
        error.setMessage("Error 404 Not Found. " + ex.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());

        // return ResponseEntity.notFound().build();
        // return ResponseEntity.status(404).body(error);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
