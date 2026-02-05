package com.ejemplo.cliente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ejemplo.cliente.service.clienteServiceImp;

@RestController
@RequestMapping("clientes")
public class ClienteController {


    @Autowired
    private clienteServiceImp servicioCliente;
   

    @GetMapping("/lista-user")
    public String obtenerSaludo() {
        return servicioCliente.getUser();
    } 

     @GetMapping("/hello")
    public String saludar(@RequestParam(defaultValue = "Mundo") String nombre) {
        return "hola como estas";
    }
}