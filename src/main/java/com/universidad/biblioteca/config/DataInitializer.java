package com.universidad.biblioteca.config;

import org.springframework.stereotype.Component;

import com.universidad.biblioteca.repository.CategoriaRepository;
import com.universidad.biblioteca.repository.LibroRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//Genera autómaticamente un sistema de locker, por si ocurre algún error
@Slf4j
//Crearlo como un elemento aparte, para ejecutarlo en cualquier momento
@Component
@RequiredArgsConstructor
//CommandLineRunner
//Obliga a ejecutarse al correr el programa (run)
//y Spring se encarga de ejecutar únicamente después que el contexto de la app
//Y la DB fueron creadas
public class DataInitializer {
    //Inyectar los repositorios, permitirán hacer el INSERT porque contienen el CRUD
    private final CategoriaRepository CategoriaRepository;
    private final LibroRepository LibroRepository;

    @Override
    public void run(String... args){
        //Verificar si existen datos en las tablas
        //en caso que existan, no se insertan y en caso contratio, los intertamos
        if (CategoriaRepository.count() > 0) {
            log.info("La DB ya tiene datos, se omite la carga inicial");
            return; //Termina la ejecución de la función porque la obliga a salir de ella
        }
        
    }

}