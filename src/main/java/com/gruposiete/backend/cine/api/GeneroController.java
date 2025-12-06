package com.gruposiete.backend.cine.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/generos")
@CrossOrigin(origins = "http://localhost:4321")
public class GeneroController {
    @Autowired private GeneroRepository repository;

    @GetMapping
    public List<Genero> getAll() { return repository.findAll(); }
}