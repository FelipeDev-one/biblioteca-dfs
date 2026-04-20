package com.universidad.biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class LibroResponseDTO {

    private Long id;
    private String titulo;
    private String isbn;
    //Solo el nombre de la categoría / No el objeto completo
    private String categoriaNombre;

}
