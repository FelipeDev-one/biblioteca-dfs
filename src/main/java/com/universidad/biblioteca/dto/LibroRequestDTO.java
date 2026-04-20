package com.universidad.biblioteca.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Si no se crean los métodos y data, no se puede instanciar
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibroRequestDTO {

    //Se deben trasladar todas las validaciones del modelo al DTO 
    //porque todas las validaciones llegarán acá
    @NotBlank(message = "EL titulo no puede estar vacio")
    @Size(max= 200, message = "El titulo no puede superar los 200 carácteres")
    private String titulo;

    @NotBlank
    @Size(max = 20, message = "El isbn no puede superar los 20 carácteres")
    private String isbn;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El número debe ser mayor a 0")
    private BigDecimal precio;

    @NotNull(message = "El ID de la categoria es obligatorio")
    private Long categoriaId;
}
