package com.universidad.biblioteca.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Encapsulamiento de variables, creación de constructores
@Data
@AllArgsConstructor
@NoArgsConstructor
//Indicar que es una tabla para la DB
@Entity
//Darle nombre a la tabla
@Table(name = "categoria")

public class Categoria {
    //Identifica como llave primaria
    @Id
    //Autoincrementable (Crea una secuencia en DB)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Validación NotBlank y Size
    //Mensaje directo si no escribe nada, si lo deja en blanco el campo
    @NotBlank(message = "El nombre no puede estar vacio")

    //Mensaje directo si supera el límite de carácteres
    @Size(message = "El nombre no puede superar los 100 carácteres")

    //Aplica restricciones en el campo de la DB
    @Column(nullable = false, length = 100)
    private String nombre;

    //Mensaje directo si supera el límite de carácteres
    @Size(message = "La descripción no puede superar los 255 carácteres")
    @Column(length = 255)
    private String descripcion;
}
