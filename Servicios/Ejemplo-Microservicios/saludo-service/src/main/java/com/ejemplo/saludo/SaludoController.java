package com.ejemplo.saludo;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saludos-servicio")
public class SaludoController {

    @GetMapping("/saludo")
    public String saludar(@RequestParam(defaultValue = "Mundo") String nombre) {
        return "¡Hola, " + nombre + "!";
    }
}