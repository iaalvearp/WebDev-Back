package com.gruposiete.backend.cine.api;

import jakarta.persistence.*;

@Entity
public class Formato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String name; // "2D", "IMAX", "ATMOS"

    public Formato() {}

    public Formato(String name) {
        this.name = name;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}