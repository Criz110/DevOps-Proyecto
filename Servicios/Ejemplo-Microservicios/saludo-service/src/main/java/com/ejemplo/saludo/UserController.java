package com.ejemplo.saludo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @GetMapping("/getUser")
    public String getUsers() {
        return "Lista de usuarios v3";
    }

    @GetMapping("/getAll")
    public String getAll() {
        return "Lista de todos usuarios";
    }
}