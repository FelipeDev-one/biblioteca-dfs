package com.universidad.biblioteca.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "EL titulo no puede estar vacio")
    @Size(max= 200, message = "El titulo no puede superar los 200 carácteres")
    @Column(nullable = false,length = 200)
    private String titulo;

    
    @NotBlank
    @Size(max = 20, message = "El isbn no puede superar los 20 carácteres")
    @Column(nullable = false,unique = true,length = 20)
    private String isbn;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El número debe ser mayor a 0")
    @Column(nullable = false, precision = 10, scale = 2) //precision = Máximo de cifras, scale = Máximo de decimales
    private BigDecimal precio;
    
    @NotNull(message = "La categoria es obligatoria")
    //Cardinalidad de uno a mucho
    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    //Traer el objeto completo 
    private Categoria categoria;

    public void setId(Long id2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setId'");
    }
}
