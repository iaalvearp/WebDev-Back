package com.gruposiete.backend.cine.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cinemas")
@CrossOrigin(origins = "http://localhost:4321")
public class CinemaController {

    @Autowired private CinemaRepository repository;

    @GetMapping
    public List<Cinema> getAll(@RequestParam(required = false) String city) {
        if (city != null) return repository.findByCityId(city);
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cinema> getById(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Cinema create(@RequestBody Cinema item) { return repository.save(item); }

    @PutMapping("/{id}")
    public ResponseEntity<Cinema> update(@PathVariable Long id, @RequestBody Cinema detalles) {
        Optional<Cinema> opt = repository.findById(id);
        if (opt.isPresent()) {
            Cinema item = opt.get();
            item.setName(detalles.getName());
            item.setAddress(detalles.getAddress());
            item.setCityId(detalles.getCityId());
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