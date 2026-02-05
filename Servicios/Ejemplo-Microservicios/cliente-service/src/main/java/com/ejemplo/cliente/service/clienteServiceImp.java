package com.ejemplo.cliente.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ejemplo.cliente.client.SaludoClient;

@Service
public class clienteServiceImp  implements clienteService{

    private final SaludoClient saludoClient;

    

    @Value("${security.internal.value}")
    private String internalValue;

    public clienteServiceImp(SaludoClient saludoClient) {
        this.saludoClient = saludoClient;
    }

    @Override
    public String getUser() {

            try {
                return saludoClient.obtenerListUser(internalValue);
            } catch (Exception ex) {
                // Manejo centralizado de errores o reintentos
                throw new RuntimeException("Error al comunicar con Microservicio B", ex);
            }
        

          
    }
    
}
