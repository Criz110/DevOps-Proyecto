package com.ejemplo.auth.service;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ejemplo.auth.entity.UserEntity;
import com.ejemplo.auth.exception.RecursoNoEncontradoException;
import com.ejemplo.auth.exception.ValidationsException;
import com.ejemplo.auth.repository.UserEntityRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Service
public class UserEntityServiceImpl  implements UserEntityService{

    @Value("${jwt.secret}")
    private String secretKeyString;

    @Value("${jwt.expiration}")
    private long expirationTime;

    private  Key secretKey;

     @PostConstruct
    public void init() {
        // Crea una clave HMAC-SHA256 a partir de la cadena del properties
        secretKey = Keys.hmacShaKeyFor(secretKeyString.getBytes());
    }


    @Autowired
    private UserEntityRepository userEntityRepository;

   
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public boolean validarUser(String username, String password) {
        UserEntity user = userEntityRepository.findByUsername(username)
        .orElseThrow( () -> new RecursoNoEncontradoException("Usuario no encontrado"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ValidationsException("Contraseña incorrecta");
        }

        return true;
    }



    @Override
    public UserEntity savEntity(String username, String password) {
        if (userEntityRepository.findByUsername(username).isPresent()) {
            throw new ValidationsException("El usuario ya existe");
        }

        String passwordEncriptada = passwordEncoder.encode(password);
       
        UserEntity newUser = UserEntity.builder()
                .username(username)
                .password(passwordEncriptada)
                .build();

        return userEntityRepository.save(newUser);
    }



    @Override
    public String generarToken(String username) {
        
        Date now = new Date();
            Date expiration = new Date(now.getTime() + expirationTime);

            String token = Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(new Date())
                    .setExpiration(expiration)
                    .signWith(secretKey, SignatureAlgorithm.HS256)
                    .compact();

        return token;

    }
    
    
}
