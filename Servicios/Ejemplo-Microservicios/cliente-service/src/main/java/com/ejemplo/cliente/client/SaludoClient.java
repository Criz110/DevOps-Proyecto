package com.ejemplo.cliente.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
 
@FeignClient(name = "saludo-service", url = "localhost:8082/users")// Nombre del servicio en Eureka
public interface SaludoClient {

    @GetMapping("/getUser")
    String obtenerListUser(@RequestHeader("X-Internal-Request") String internalToken);
    //String obtenerListUser(@RequestParam("nombre") String nombre);
}