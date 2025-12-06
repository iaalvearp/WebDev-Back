package com.gruposiete.backend.cine.api;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Funcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con Película
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Pelicula movie;

    // Relación con Sala
    @ManyToOne
    @JoinColumn(name = "sala_id")
    private Sala sala;

    private LocalDate date; // 2024-12-05
    private String time;    // "14:30"
    private String format;  // "2D - Subtitulada"
    private String language; // "ESP", "SUB"
    private double price;   // Precio base
    private boolean available;

    public Funcion() {}

    public Funcion(Pelicula movie, Sala sala, LocalDate date, String time, String format, String language, double price, boolean available) {
        this.movie = movie;
        this.sala = sala;
        this.date = date;
        this.time = time;
        this.format = format;
        this.language = language;
        this.price = price;
        this.available = available;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Pelicula getMovie() { return movie; }
    public void setMovie(Pelicula movie) { this.movie = movie; }
    public Sala getSala() { return sala; }
    public void setSala(Sala sala) { this.sala = sala; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
    public String getFormat() { return format; }
    public void setFormat(String format) { this.format = format; }
    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}