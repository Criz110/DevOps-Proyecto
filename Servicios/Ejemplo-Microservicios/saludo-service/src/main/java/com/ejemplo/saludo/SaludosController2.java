package com.ejemplo.saludo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controlador2")
public class SaludosController2 {

    @GetMapping("/respuesta2")
    public String respuesta(){
        return "este es el 2 controlador de saludos";
    }
    
}
