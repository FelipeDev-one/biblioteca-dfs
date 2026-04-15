package com.universidad.biblioteca.controller;

import com.universidad.biblioteca.repository.CategoriaRepository;
import com.universidad.biblioteca.repository.LibroRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.universidad.biblioteca.model.Categoria;
import com.universidad.biblioteca.service.CategoriaService;

import jakarta.validation.Valid;


@RestController
//la URL base para acceder a este archivo
@RequestMapping("/api/categorias") //No pueden tener carácter especial, espacios, Ñ, SOLO PALABRAS
public class CategoriaController {
    private final LibroRepository LibroRepository;
    private final CategoriaRepository CategoriaRepository;
    @Autowired
    private CategoriaService categoriaService;

    CategoriaController(CategoriaRepository categoriaRepository, LibroRepository libroRepository) {
        this.categoriaRepository = categoriaRepository;
        this.libroRepository = libroRepository;
    }

    //Endpoints accesibles para este controlador

    //Método GET --> Permite obtener y mostrar datos (SELECT)
    @GetMapping()
    public ResponseEntity<List<Categoria>> obtenerCategorias(){
        return ResponseEntity.ok(categoriaService.obtenerTodas());
    }
    //Endpoint para obtener solo las categorías que cumplan con una condición
    //@PathVariable extrae ese {id} de la URL
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obtenerCategoriaPorId(@PathVariable Long id){
        return categoriaService.obtenerPorId(id)
        .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build()); //Parecido al Trycash
    }

    //@RequestBody --> Recibe los datos mediante el body del HTTP
    @PostMapping()
    public ResponseEntity<Categoria> crear(@Valid @RequestBody Categoria cat){
        Categoria nueva = categoriaService.guardar(cat);
        return ResponseEntity.status(201).body(nueva);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizar(
        @PathVariable Long id,
        @Valid @RequestBody Categoria cat
    ){
        //Verificar la categoria con el Id existente
        return categoriaService.obtenerPorId(id)
        .map(existente -> { //Si existe, mediante una función de flecha le digo qué hacer
            cat.setId(id); //Aseguramos que se actualice el correcto
            return ResponseEntity.ok(categoriaService.guardar(cat));
        })
        .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        if(categoriaService.obtenerPorId(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        categoriaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
