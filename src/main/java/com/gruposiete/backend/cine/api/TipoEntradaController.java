package com.gruposiete.backend.cine.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tipos-entradas")
@CrossOrigin(origins = "http://localhost:4321")
public class TipoEntradaController {

    @Autowired private TipoEntradaRepository repository;

    @GetMapping
    public List<TipoEntrada> getAll() { return repository.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<TipoEntrada> getById(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public TipoEntrada create(@RequestBody TipoEntrada item) { return repository.save(item); }

    @PutMapping("/{id}")
    public ResponseEntity<TipoEntrada> update(@PathVariable Long id, @RequestBody TipoEntrada detalles) {
        Optional<TipoEntrada> opt = repository.findById(id);
        if (opt.isPresent()) {
            TipoEntrada item = opt.get();
            item.setName(detalles.getName());
            item.setPrice(detalles.getPrice());
            item.setDescription(detalles.getDescription());
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