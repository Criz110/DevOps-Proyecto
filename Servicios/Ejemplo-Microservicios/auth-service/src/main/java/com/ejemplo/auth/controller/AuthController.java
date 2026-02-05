package com.ejemplo.auth.controller;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ejemplo.auth.controller.DTO.UserDTO;
import com.ejemplo.auth.entity.UserEntity;
import com.ejemplo.auth.service.UserEntityService;
import com.ejemplo.auth.service.UserEntityServiceImpl;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    private UserEntityService userEntityService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
         HashMap<String, String> map= new HashMap<>();

        if(userEntityService.validarUser(userDTO.getUsername(), userDTO.getPassword())){
            String token=userEntityService.generarToken(userDTO.getUsername());
            map.put("token", token);
        } else {
            map.put("error", "Credenciales inválidas");
        }

      return  ResponseEntity.ok().body(map);
    }

    // SIGNUP
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok().body(userEntityService.savEntity(userDTO.getUsername(), userDTO.getPassword()));
    }


   
}