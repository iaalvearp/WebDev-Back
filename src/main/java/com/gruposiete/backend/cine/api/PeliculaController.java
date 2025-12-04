package com.gruposiete.backend.cine.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/peliculas") // Esta será la dirección web: localhost:8080/api/peliculas
public class PeliculaController {

    @Autowired
    private PeliculaRepository peliculaRepository;

    // Obtener todas las películas (GET)
    @GetMapping
    public List<Pelicula> obtenerTodas() {
        return peliculaRepository.findAll();
    }

    // Guardar una película nueva (POST)
    @PostMapping
    public Pelicula guardarPelicula(@RequestBody Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }
}