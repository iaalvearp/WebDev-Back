package com.gruposiete.backend.cine.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
    // ¡Aquí no hay código! Spring Boot genera mágicamente 
    // los métodos para Guardar, Buscar, Borrar y Actualizar.
}