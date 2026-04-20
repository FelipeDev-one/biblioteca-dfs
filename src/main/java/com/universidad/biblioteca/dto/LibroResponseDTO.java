package com.universidad.biblioteca.dto;

import java.math.BigDecimal;

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
    private BigDecimal precio;
    //Solo el nombre de la categoría / No el objeto completo
    private String categoriaNombre;

}
