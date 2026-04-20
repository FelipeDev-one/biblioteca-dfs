package com.universidad.biblioteca.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.universidad.biblioteca.dto.LibroRequestDTO;
import com.universidad.biblioteca.dto.LibroResponseDTO;
import com.universidad.biblioteca.service.LibroServiceDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/dto/libros")
@RequiredArgsConstructor
public class LibroControllerDTO {

    private final LibroServiceDTO libroService;

    //GET
    @GetMapping
    public ResponseEntity<List<LibroResponseDTO>> obtenerTodos(){
        return ResponseEntity.ok(libroService.obtenerTodos());
    }

    //GET por ID
    @GetMapping("/{id}")
    public ResponseEntity<LibroResponseDTO> obtenerId(@PathVariable Long id){
        return libroService.obtenerPorId(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    //POST insertar un nuevo libro
    @PostMapping
    public ResponseEntity<LibroResponseDTO> crear(
        @Valid @RequestBody LibroRequestDTO dto
    ){
        return ResponseEntity.status(201).body(libroService.guardarLibro(dto));
    }
    
    //PUT modificar
    @PutMapping("/{id}")
    public ResponseEntity<LibroResponseDTO> actualizar(
        @PathVariable Long id, @Valid @RequestBody LibroRequestDTO dto
    ){
        return libroService.actualizarLibro(id, dto)
        .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    //Delete eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        //Verificar si el Id existe
        if(libroService.obtenerPorId(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        libroService.eliminarLibro(id);
        return ResponseEntity.noContent().build();
    }
}

