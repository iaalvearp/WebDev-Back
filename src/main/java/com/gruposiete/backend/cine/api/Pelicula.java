package com.gruposiete.backend.cine.api;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// @Entity: Le dice a Spring "Esto no es código normal, esto debe ser una TABLA en la base de datos"
@Entity
public class Pelicula {

    // @Id: Dice "Esta es la Llave Primaria (Primary Key)"
    // @GeneratedValue: Dice "No me pidas el ID, genéralo tú automáticamente (1, 2, 3...)"
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String director;
    private String genero;
    private Integer anio;

    // Constructor vacío (Obligatorio para que Spring funcione)
    public Pelicula() {
    }

    // Constructor con datos (Para facilitarnos la vida a nosotros)
    public Pelicula(String titulo, String director, String genero, Integer anio) {
        this.titulo = titulo;
        this.director = director;
        this.genero = genero;
        this.anio = anio;
    }

    // Getters y Setters (Necesarios para que Spring pueda leer y escribir los datos)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }
}