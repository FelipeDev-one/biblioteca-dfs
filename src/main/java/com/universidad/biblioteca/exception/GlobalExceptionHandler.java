package com.universidad.biblioteca.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//Está unido a cualquier controlador que generemos
@RestControllerAdvice
public class GlobalExceptionHandler {
    //Errores de validación @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidateErrors(
        MethodArgumentNotValidException ex
    ){
        //Objeto para manipular y organizar los errores a mostrar
        Map<String, String> errores = new  LinkedHashMap<>();
        //getFieldErrors --> Devolver uno por uno los errores
        //getField --> Nombre del campo donde esta el error
        //getDefaultMessage --> Mensaje de error de la validación
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errores.put(error.getField(), error.getDefaultMessage())
        );
        //Bad Request -- 400
        return ResponseEntity.badRequest().body(errores);
    }

    //Errores de negocio
    //Categoria no encontrada
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handlerRuntimeException(
        RuntimeException ex
    ){
        Map<String, String> error = new LinkedHashMap<>();
        error.put("Error", ex.getMessage());
        //400 --> El cliente envió algún dato que no existe
        //manipulación de errores es por parte del envió de datos del cliente
        //no del servidor
        return ResponseEntity.badRequest().body(error);
    }

}
