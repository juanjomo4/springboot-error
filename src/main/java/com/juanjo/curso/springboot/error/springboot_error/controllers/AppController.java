package com.juanjo.curso.springboot.error.springboot_error.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping("/app")
    public String index() {
        // int value = 100 / 0; Error de división por cero 500
        // int value = Integer.parseInt("10"); Ok mando un 200
        int value = Integer.parseInt("10 Hola"); // Error de formato 500 No se puede formatear el String
        System.out.println(value);
        return "Ok mando un 200";
    }
}
