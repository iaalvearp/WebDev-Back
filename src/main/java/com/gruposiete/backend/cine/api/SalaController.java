package com.gruposiete.backend.cine.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/salas")
@CrossOrigin(origins = "http://localhost:4321")
public class SalaController {

    @Autowired private SalaRepository repository;
    @Autowired private CinemaRepository cinemaRepository;

    @GetMapping
    public List<Sala> getAll(@RequestParam(required = false) Long cinemaId) {
        if (cinemaId != null) return repository.findByCinemaId(cinemaId);
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sala> getById(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Sala create(@RequestBody Sala item) { return repository.save(item); }

    @PutMapping("/{id}")
    public ResponseEntity<Sala> update(@PathVariable Long id, @RequestBody Sala detalles) {
        Optional<Sala> opt = repository.findById(id);
        if (opt.isPresent()) {
            Sala item = opt.get();
            item.setName(detalles.getName());
            item.setType(detalles.getType());
            item.setCapacity(detalles.getCapacity());
            // Actualizar relación con Cine si viene
            if (detalles.getCinema() != null && detalles.getCinema().getId() != null) {
                 Optional<Cinema> cine = cinemaRepository.findById(detalles.getCinema().getId());
                 cine.ifPresent(item::setCinema);
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