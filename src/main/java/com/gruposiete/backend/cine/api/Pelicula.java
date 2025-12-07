package com.gruposiete.backend.cine.api;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    
    @Column(columnDefinition = "TEXT")
    private String synopsis;
    
    private String director;
    private String duration;
    private String rating;
    private String poster;
    private String backdrop;
    
    @ElementCollection
    @CollectionTable(name = "movie_genre", joinColumns = @JoinColumn(name = "movie_id"))
    @Column(name = "genre_name")
    private List<String> genre;
    
    @ElementCollection
    @CollectionTable(name = "movie_cast", joinColumns = @JoinColumn(name = "movie_id"))
    @Column(name = "actor_name")
    private List<String> cast;
    
    // CAMBIO CLAVE: Ahora es una lista de Strings simple, no objetos complejos.
    // Esto arregla el error de guardado inmediatamente.
    @ElementCollection
    @CollectionTable(name = "movie_format", joinColumns = @JoinColumn(name = "movie_id"))
    @Column(name = "format_name")
    private List<String> formats; 

    private Integer anio;
    
    // Mapeamos 'preSale' del JSON a 'isPreSale' de Java automáticamente
    @Column(name = "is_pre_sale")
    private boolean preSale; 

    public Pelicula() {}

    public Pelicula(String title, String synopsis, String director, String duration, String rating, String poster, String backdrop, List<String> genre, List<String> cast, List<String> formats, Integer anio, boolean preSale) {
        this.title = title;
        this.synopsis = synopsis;
        this.director = director;
        this.duration = duration;
        this.rating = rating;
        this.poster = poster;
        this.backdrop = backdrop;
        this.genre = genre;
        this.cast = cast;
        this.formats = formats;
        this.anio = anio;
        this.preSale = preSale;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getSynopsis() { return synopsis; }
    public void setSynopsis(String synopsis) { this.synopsis = synopsis; }
    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }
    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }
    public String getRating() { return rating; }
    public void setRating(String rating) { this.rating = rating; }
    public String getPoster() { return poster; }
    public void setPoster(String poster) { this.poster = poster; }
    public String getBackdrop() { return backdrop; }
    public void setBackdrop(String backdrop) { this.backdrop = backdrop; }
    public List<String> getGenre() { return genre; }
    public void setGenre(List<String> genre) { this.genre = genre; }
    public List<String> getCast() { return cast; }
    public void setCast(List<String> cast) { this.cast = cast; }
    public List<String> getFormats() { return formats; }
    public void setFormats(List<String> formats) { this.formats = formats; }
    public Integer getAnio() { return anio; }
    public void setAnio(Integer anio) { this.anio = anio; }
    
    // Getter/Setter especial para JSON 'preSale'
    public boolean isPreSale() { return preSale; }
    public void setPreSale(boolean preSale) { this.preSale = preSale; }
}