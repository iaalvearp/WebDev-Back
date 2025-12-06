package com.gruposiete.backend.cine.api;

import jakarta.persistence.*;

@Entity
public class City {
    @Id
    private String id; // Ej: "quito", "guayaquil"
    private String name;

    public City() {}

    public City(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}