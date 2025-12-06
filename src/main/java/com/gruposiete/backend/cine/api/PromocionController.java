package com.gruposiete.backend.cine.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/promociones")
@CrossOrigin(origins = "http://localhost:4321")
public class PromocionController {

    @Autowired private PromocionRepository repository;

    @GetMapping
    public List<Promocion> getAll() { return repository.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Promocion> getById(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Promocion create(@RequestBody Promocion item) { return repository.save(item); }

    @PutMapping("/{id}")
    public ResponseEntity<Promocion> update(@PathVariable Long id, @RequestBody Promocion detalles) {
        Optional<Promocion> opt = repository.findById(id);
        if (opt.isPresent()) {
            Promocion item = opt.get();
            item.setTitle(detalles.getTitle());
            item.setDescription(detalles.getDescription());
            item.setDiscount(detalles.getDiscount());
            item.setValidity(detalles.getValidity());
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