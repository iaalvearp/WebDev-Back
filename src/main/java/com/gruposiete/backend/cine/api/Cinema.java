package com.gruposiete.backend.cine.api;

import jakarta.persistence.*;

@Entity
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    
    @Column(name = "city_id")
    private String cityId; // Relación con la ciudad (ej: "quito")

    public Cinema() {}

    public Cinema(String name, String address, String cityId) {
        this.name = name;
        this.address = address;
        this.cityId = cityId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getCityId() { return cityId; }
    public void setCityId(String cityId) { this.cityId = cityId; }
}