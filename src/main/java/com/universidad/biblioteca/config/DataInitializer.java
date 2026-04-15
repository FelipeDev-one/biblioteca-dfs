package com.universidad.biblioteca.config;

import org.springframework.stereotype.Component;

import com.universidad.biblioteca.model.Categoria;
import com.universidad.biblioteca.model.Libro;
import com.universidad.biblioteca.repository.CategoriaRepository;
import com.universidad.biblioteca.repository.LibroRepository;
import java.math.BigDecimal;
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

        log.info("DB Vacia, insertando datos iniciales...");
        //Cargamos los datos iniciales en la tabla categoría
        Categoria cat1 = CategoriaRepository.save(
            new Categoria(null, "El Diaria de Anna Frank", "La trágica vida de Anna Frank")
        );

        Categoria cat2 = CategoriaRepository.save(
            new Categoria(null, "Ciencia Ficción", "Imaginación y aventura futurista")
        );

        //Insertamos los libros
        LibroRepository.save(new Libro(null, "Diario de Anna Frank", "DF22323", 
        new java.math.BigDecimal(29990.99), cat2)
        );
        LibroRepository.save(new Libro(null, "La familia Wakatela", "DF223-2", 
        new java.math.BigDecimal(19990.99), cat1)
        );

        //Mostrar un mensaje para indicar los insert correctos
        log.info("{} categorias y {} libros insertados correctamente",
            CategoriaRepository.count(), LibroRepository.count()
        );
    }

}