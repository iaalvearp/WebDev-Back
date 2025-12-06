package com.gruposiete.backend.cine.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/formatos")
@CrossOrigin(origins = "http://localhost:4321")
public class FormatoController {
    @Autowired private FormatoRepository repository;

    @GetMapping
    public List<Formato> getAll() { return repository.findAll(); }
}