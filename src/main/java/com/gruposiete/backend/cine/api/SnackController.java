package com.gruposiete.backend.cine.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/snacks")
@CrossOrigin(origins = "http://localhost:4321")
public class SnackController {

    @Autowired private SnackRepository repository;

    @GetMapping
    public List<Snack> getAll() { return repository.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Snack> getById(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Snack create(@RequestBody Snack item) { return repository.save(item); }

    @PutMapping("/{id}")
    public ResponseEntity<Snack> update(@PathVariable Long id, @RequestBody Snack detalles) {
        Optional<Snack> opt = repository.findById(id);
        if (opt.isPresent()) {
            Snack item = opt.get();
            item.setName(detalles.getName());
            item.setDescription(detalles.getDescription());
            item.setPrice(detalles.getPrice());
            item.setCategory(detalles.getCategory());
            item.setImage(detalles.getImage());
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