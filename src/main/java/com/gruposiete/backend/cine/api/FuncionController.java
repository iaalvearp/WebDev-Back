package com.gruposiete.backend.cine.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/funciones")
@CrossOrigin(origins = "http://localhost:4321")
public class FuncionController {

    @Autowired private FuncionRepository repository;
    @Autowired private PeliculaRepository peliculaRepository;
    @Autowired private SalaRepository salaRepository;

    @GetMapping
    public List<Funcion> getAll(@RequestParam(required = false) Long peliculaId) {
        if (peliculaId != null) return repository.findByMovieId(peliculaId);
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcion> getById(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Funcion create(@RequestBody Funcion item) { return repository.save(item); }

    @PutMapping("/{id}")
    public ResponseEntity<Funcion> update(@PathVariable Long id, @RequestBody Funcion detalles) {
        Optional<Funcion> opt = repository.findById(id);
        if (opt.isPresent()) {
            Funcion item = opt.get();
            item.setDate(detalles.getDate());
            item.setTime(detalles.getTime());
            item.setFormat(detalles.getFormat());
            item.setLanguage(detalles.getLanguage());
            item.setPrice(detalles.getPrice());
            item.setAvailable(detalles.isAvailable());
            
            // Actualizar relaciones si vienen
            if(detalles.getMovie() != null && detalles.getMovie().getId() != null) {
                peliculaRepository.findById(detalles.getMovie().getId()).ifPresent(item::setMovie);
            }
            if(detalles.getSala() != null && detalles.getSala().getId() != null) {
                salaRepository.findById(detalles.getSala().getId()).ifPresent(item::setSala);
            }

            return ResponseEntity.ok(repository.save(item));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}