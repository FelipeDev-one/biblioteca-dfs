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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 200)
    private String titulo;
    
    
    @Column(nullable = false,unique = true,length = 20)
    private String isbn;

    
    @Column(nullable = false, precision = 10, scale = 2) //precision = Máximo de cifras, scale = Máximo de decimales
    private BigDecimal precio;
    
    
    //Cardinalidad de uno a mucho
    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    //Traer el objeto completo 
    private Categoria categoria;

    
}
