package com.juanjo.curso.springboot.error.springboot_error.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.juanjo.curso.springboot.error.springboot_error.exceptions.UserNotFoundException;
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

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> numberFormatException(Exception ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("Date", new Date().toString());
        error.put("Error", "NumberFormatException, número no válido");
        error.put("Message", "Error de formato en el número, no tiene formato de dígito. " + ex.getMessage());
        error.put("Status", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        return error;
    }

    @ExceptionHandler({ NullPointerException.class,
            HttpMessageNotWritableException.class,
            UserNotFoundException.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> userNotFoundException(Exception ex) {

        Map<String, Object> error = new HashMap<>();
        error.put("date", new Date());
        error.put("error", "el usuario o role no existe!");
        error.put("message", ex.getMessage());
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());

        return error;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorModels> notFoundEx(NoHandlerFoundException ex) {
        ErrorModels error = new ErrorModels();
        error.setDate(new Date());
        error.setError("NoHandlerFoundException, API-Rest no encontrada");
        error.setMessage("Error 404 Not Found. " + ex.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());

        // return ResponseEntity.notFound().build();
        // return ResponseEntity.status(404).body(error);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

}
