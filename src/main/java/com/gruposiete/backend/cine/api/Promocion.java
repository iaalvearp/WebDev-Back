package com.gruposiete.backend.cine.api;

import jakarta.persistence.*;

@Entity
public class Promocion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String discount; // Ej: "50% OFF"
    private String validity; // Ej: "Todos los martes"
    private String image;    // Emoji o URL de icono

    public Promocion() {}

    public Promocion(String title, String description, String discount, String validity, String image) {
        this.title = title;
        this.description = description;
        this.discount = discount;
        this.validity = validity;
        this.image = image;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getDiscount() { return discount; }
    public void setDiscount(String discount) { this.discount = discount; }
    public String getValidity() { return validity; }
    public void setValidity(String validity) { this.validity = validity; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
}