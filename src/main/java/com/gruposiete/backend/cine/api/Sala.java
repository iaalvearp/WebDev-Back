package com.gruposiete.backend.cine.api;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // "Sala 1", "Sala IMAX"
    private String type; // "Normal", "VIP", "IMAX"
    private int capacity; 

    @ManyToOne
    @JoinColumn(name = "cinema_id")
    @JsonIgnore // Evita que se traiga todo el objeto Cine y cause bucles
    private Cinema cinema;

    public Sala() {}

    public Sala(String name, String type, int capacity, Cinema cinema) {
        this.name = name;
        this.type = type;
        this.capacity = capacity;
        this.cinema = cinema;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    
    public Cinema getCinema() { return cinema; }
    public void setCinema(Cinema cinema) { this.cinema = cinema; }

    // --- SOLUCIÓN MÁGICA ---
    // Este método crea un campo "cinemaId" en el JSON automáticamente
    @JsonProperty("cinemaId")
    public Long getCinemaId() {
        return (cinema != null) ? cinema.getId() : null;
    }
}