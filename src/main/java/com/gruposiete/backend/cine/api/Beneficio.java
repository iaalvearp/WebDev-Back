package com.gruposiete.backend.cine.api;

import jakarta.persistence.*;

@Entity
public class Beneficio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String icon;  // Ej: "star", "ticket", "users", "gift"
    private String color; // Ej: "yellow", "green", "pink", "blue"

    public Beneficio() {}

    public Beneficio(String title, String description, String icon, String color) {
        this.title = title;
        this.description = description;
        this.icon = icon;
        this.color = color;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
}