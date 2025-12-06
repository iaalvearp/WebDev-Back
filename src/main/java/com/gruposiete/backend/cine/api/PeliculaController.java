package com.gruposiete.backend.cine.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/peliculas")
@CrossOrigin(origins = "http://localhost:4321")
public class PeliculaController {

    @Autowired
    private PeliculaRepository peliculaRepository;
    
    @Autowired
    private FormatoRepository formatoRepository; // Necesario para buscar formatos al guardar

    // 1. OBTENER TODAS (Ya lo tenías)
    @GetMapping
    public List<Pelicula> getAllPeliculas() {
        return peliculaRepository.findAll();
    }

    // 2. OBTENER UNA POR ID (Esto arregla tu error 404)
    @GetMapping("/{id}")
    public ResponseEntity<Pelicula> getPeliculaById(@PathVariable Long id) {
        Optional<Pelicula> pelicula = peliculaRepository.findById(id);
        return pelicula.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 3. CREAR PELÍCULA
    @PostMapping
    public Pelicula createPelicula(@RequestBody Pelicula pelicula) {
        // Aquí Spring intenta mapear el JSON a la Entidad automáticamente.
        // Si envías formatos, asegúrate que vengan como objetos completos o maneja la lógica aquí.
        return peliculaRepository.save(pelicula);
    }

    // 4. ACTUALIZAR PELÍCULA
    @PutMapping("/{id}")
    public ResponseEntity<Pelicula> updatePelicula(@PathVariable Long id, @RequestBody Pelicula detalles) {
        Optional<Pelicula> peliculaOpt = peliculaRepository.findById(id);

        if (peliculaOpt.isPresent()) {
            Pelicula pelicula = peliculaOpt.get();
            
            pelicula.setTitle(detalles.getTitle());
            pelicula.setSynopsis(detalles.getSynopsis());
            pelicula.setDirector(detalles.getDirector());
            pelicula.setDuration(detalles.getDuration());
            pelicula.setRating(detalles.getRating());
            pelicula.setPoster(detalles.getPoster());
            pelicula.setBackdrop(detalles.getBackdrop());
            pelicula.setAnio(detalles.getAnio());
            pelicula.setPreSale(detalles.isPreSale());
            
            // Actualizar listas si vienen
            if (detalles.getGenre() != null) pelicula.setGenre(detalles.getGenre());
            if (detalles.getCast() != null) pelicula.setCast(detalles.getCast());
            if (detalles.getFormats() != null) pelicula.setFormats(detalles.getFormats());

            Pelicula updatedPelicula = peliculaRepository.save(pelicula);
            return ResponseEntity.ok(updatedPelicula);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 5. ELIMINAR PELÍCULA (Opcional, pero útil para admin)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePelicula(@PathVariable Long id) {
        if (peliculaRepository.existsById(id)) {
            peliculaRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}