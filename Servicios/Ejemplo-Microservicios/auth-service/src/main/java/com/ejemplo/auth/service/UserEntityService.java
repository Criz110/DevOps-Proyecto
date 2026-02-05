package com.ejemplo.auth.service;

import com.ejemplo.auth.entity.UserEntity;

public interface UserEntityService {
    
    boolean validarUser(String username, String password);

    String generarToken(String username);

    UserEntity savEntity(String username, String password);
}
