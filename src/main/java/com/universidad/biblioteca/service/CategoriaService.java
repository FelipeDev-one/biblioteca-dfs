package com.universidad.biblioteca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.universidad.biblioteca.model.Categoria;
import com.universidad.biblioteca.repository.CategoriaRepository;

//Anotación para que reconozca que es un servicio
@Service
public class CategoriaService {

    //Permiso, llama y utiliza los datos del Repositorio
    @Autowired
    private CategoriaRepository categoriaRepository;

    //Método para obtener todas las categorías
    public List<Categoria> obtenerTodas(){
        return categoriaRepository.findAll();
    }
    //Método para obtener una categoría por su Id
    public Optional<Categoria> obtenerPorId(Long id){ //Optional hace que el controlador manipule el código de respuesta (200 u 404)
        return categoriaRepository.findById(id);
    }
    //Método para crear una nueva categoria
    public Categoria guardar(Categoria cat){
        return categoriaRepository.save(cat);
    }
    //Método para eliminar una categoria
    public void eliminar(Long id){
        categoriaRepository.deleteById(id);
    }
}
