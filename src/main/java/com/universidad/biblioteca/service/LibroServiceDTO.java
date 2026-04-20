package com.universidad.biblioteca.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.universidad.biblioteca.dto.LibroRequestDTO;
import com.universidad.biblioteca.dto.LibroResponseDTO;
import com.universidad.biblioteca.model.Categoria;
import com.universidad.biblioteca.model.Libro;
import com.universidad.biblioteca.repository.CategoriaRepository;
import com.universidad.biblioteca.repository.LibroRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LibroServiceDTO {

    private final LibroRepository libroRepository;
    private final CategoriaRepository categoriaRepository;

    //Mapeo privado: Model -> ResponseDTO
    //Controller y Repository nunca conozcan el DTO 
    //ni la entidad
    private LibroResponseDTO mapToDto(Libro lib){
        return new LibroResponseDTO(
            lib.getId(),
            lib.getTitulo(),
            lib.getIsbn(),
            lib.getPrecio(),
            lib.getCategoria().getNombre()
        );
    }

    //Métodos de los repositorios para la lógica de programación

    //Obtener todos los libros
    public List<LibroResponseDTO> obtenerTodos(){
        return libroRepository.findAll()
        .stream()//Separo objeto a objeto de la lista
        .map(this::mapToDto) //Transformo el objeto en el DTO
        .collect(Collectors.toList()); //Vuelvo a convertir en lista pero ahora de la estructura del DTO
    }

    //Obtener un libro por su ID
    public Optional<LibroResponseDTO> obtenerPorId(Long id){
        return libroRepository.findById(id).map(this::mapToDto);
    }

    //Guardar un libro
    //Anter recibía Libro - Ahora el DTO de petición
    public LibroResponseDTO guardarLibro(LibroRequestDTO dto){
        //Buscar la categoria mediante el ID que viene en el DTO
        Categoria categoria = categoriaRepository
        .findById(dto.getCategoriaId())
        .orElseThrow(() -> new RuntimeException(
            "Categoria no encontrada con id: " + dto.getCategoriaId()
        ));
        //Si se consigue la categoria --> Agregar el libro
        Libro lib = new Libro(
            null,
            dto.getTitulo(),
            dto.getIsbn(),
            dto.getPrecio(),
            categoria
        );
        return mapToDto(libroRepository.save(lib));
    }

    //Actualizar un libro
    public Optional<LibroResponseDTO> actualizarLibro(Long id, LibroRequestDTO dto){
        //Vertificar si el libro que actualizaremos, existe
        return libroRepository.findById(id).map(existente ->{
            Categoria categoria = categoriaRepository
            .findById(dto.getCategoriaId())
            .orElseThrow(() -> new RuntimeException(
                "Categoria no encontrada con ID: " + dto.getCategoriaId()
            ));
            //Asigno los nuevos valores
            existente.setTitulo(dto.getTitulo());
            existente.setIsbn(dto.getIsbn());
            existente.setPrecio(dto.getPrecio());
            existente.setCategoria(categoria);
            return mapToDto(libroRepository.save(existente));
        });
    }

    //Eliminar
    public void eliminarLibro(Long id){
        libroRepository.deleteById(id);
    }

    //Métodos de repository personalizados
    public List<LibroResponseDTO> buscarPorTitulo(String texto){
        return libroRepository.findByTituloContainingIgnoreCase(texto)
        .stream().map(this::mapToDto).collect(Collectors.toList());
    }
}
