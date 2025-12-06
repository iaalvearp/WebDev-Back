package com.gruposiete.backend.cine.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/beneficios")
@CrossOrigin(origins = "http://localhost:4321")
public class BeneficioController {

    @Autowired private BeneficioRepository repository;

    @GetMapping
    public List<Beneficio> getAll() { return repository.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Beneficio> getById(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Beneficio create(@RequestBody Beneficio item) { return repository.save(item); }

    @PutMapping("/{id}")
    public ResponseEntity<Beneficio> update(@PathVariable Long id, @RequestBody Beneficio detalles) {
        Optional<Beneficio> opt = repository.findById(id);
        if (opt.isPresent()) {
            Beneficio item = opt.get();
            item.setTitle(detalles.getTitle());
            item.setDescription(detalles.getDescription());
            item.setIcon(detalles.getIcon());
            item.setColor(detalles.getColor());
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