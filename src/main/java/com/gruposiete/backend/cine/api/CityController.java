package com.gruposiete.backend.cine.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cities")
@CrossOrigin(origins = "http://localhost:4321")
public class CityController {

    @Autowired private CityRepository repository;

    @GetMapping
    public List<City> getAll() { return repository.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<City> getById(@PathVariable String id) {
        return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public City create(@RequestBody City item) { return repository.save(item); }

    @PutMapping("/{id}")
    public ResponseEntity<City> update(@PathVariable String id, @RequestBody City detalles) {
        Optional<City> opt = repository.findById(id);
        if (opt.isPresent()) {
            City item = opt.get();
            item.setName(detalles.getName());
            return ResponseEntity.ok(repository.save(item));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}