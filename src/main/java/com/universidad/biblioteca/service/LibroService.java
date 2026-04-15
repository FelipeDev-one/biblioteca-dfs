package com.universidad.biblioteca.service;

import com.universidad.biblioteca.repository.LibroRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.universidad.biblioteca.model.Libro;


import lombok.RequiredArgsConstructor;

@Service
//Permite inyectar las dependencias mediante el método
//constructor y los genera como tipo de acceso final
@RequiredArgsConstructor
public class LibroService {
    //final hace que no puedan existir modificaciones de los
    private final LibroRepository libroRepository;
    
    //Método para obtener todos los libros
    public List<Libro> obtenerTodos(){
        return libroRepository.findAll();
    }
    //Método para obtener los libros por Id
    public Optional<Libro> obtenerPorId(Long id){
        return libroRepository.findById(id);
    }
    //Método para guardar los libros
    public Libro guardar(Libro lib){
        return libroRepository.save(lib);
    }
    //Método para eliminar los libros
    public void eliminar(Long id){
        libroRepository.deleteById(id);
    }

    //Llamado a consultar personalizadas en el Repository}
    //Query nativo
    public List<Libro> buscarTituloParecido(String titulo){
        return libroRepository.buscarPorTituloParecido(titulo);
    }
    //Query creado por Spring
    public List<Libro> buscarTituloLike(String titulo){
        return libroRepository.findByTituloCotainningIgnoreCase(titulo);
    }
    //Query JPQL
    public List<Libro> buscarPrecioBajo(BigDecimal precio){
        return libroRepository.encontrarPreciosBajosLibros(precio);
    }
}
