package com.ejemplo.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<String> manejarRecursoNoEncontrado(RecursoNoEncontradoException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> manejarExcepcionesGenerales(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Ocurrió un error inesperado: " + ex.getMessage());
    }

    @ExceptionHandler(RecursoErroneo.class)
    public ResponseEntity<String> manejarRecursoErroneo(RecursoErroneo ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body("Ocurrió un error inesperado: " + ex.getMessage());
    }

    @ExceptionHandler(ValidationsException.class)
    public ResponseEntity<String> manejarvalidaciones(ValidationsException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Ocurrió un error inesperado: " + ex.getMessage());
    }
    
}
