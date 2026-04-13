package com.universidad.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.universidad.biblioteca.model.Categoria;

//save() --> insert o update automático
//findById() --> select where id = ?
//findAll() --> select * from 
//count()
//deleteById()
//existById()

@Repository
//Debe ser el mismo nombre del modelo + Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{ //extendes es para que herede todos los métodos


}
