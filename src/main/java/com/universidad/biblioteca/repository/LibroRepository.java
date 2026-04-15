package com.universidad.biblioteca.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.universidad.biblioteca.model.Libro;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

        //CRUD Personalizado pero que lo genera JPA
        //Spring analiza el nombre y crea la sentencia SQL a partir de ello
        //SELECT * FROM libros WHERE titulo = ?

        List<Libro> findByTitulo(String titulo);
        
        //SELECT * FROM libros WHERE UPPER(titulo) LIKE  UPPER('%?%')
        List<Libro> findByTituloCotainningIgnoreCase(String titulo);

        //SELECT * FROM libros WHERE precio < ?
        List<Libro> findbyPrecioLessThan(BigDecimal precio);

        //Querys personalizadas usando el lenguaje de JPA
        //JPQL
        @Query("SELECT l FROM Libro WHERE l.categoria.id = :categoriaId")
        List<Libro> encontrarLibroPorCategoria(@Param("categoriaId") Long categoriaId);

}
