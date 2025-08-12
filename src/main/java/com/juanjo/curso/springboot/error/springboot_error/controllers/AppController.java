package com.juanjo.curso.springboot.error.springboot_error.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juanjo.curso.springboot.error.springboot_error.exceptions.UserNotFoundException;
import com.juanjo.curso.springboot.error.springboot_error.models.domain.User;
import com.juanjo.curso.springboot.error.springboot_error.services.UserService;

@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String index() {
        // int value = 100 / 0; Error de división por cero 500
        // int value = Integer.parseInt("10"); Ok mando un 200
        int value = Integer.parseInt("10 Hola"); // Error de formato 500 No se puede formatear el String
        System.out.println(value);
        return "Ok mando un 200";
    }

    @GetMapping("/show/{id}")
    public User show(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id).orElseThrow(() -> new UserNotFoundException("Error el usuario no existe!"));
        System.out.println(user.getLastname());
        return user;
    }

}
