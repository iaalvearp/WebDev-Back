package com.gruposiete.backend.cine.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {
    
    // Este es el método que te faltaba y causaba el error en LocationController
    List<Cinema> findByCityId(String cityId);
    
    // count(), saveAll() y findAll() vienen gratis al extender JpaRepository
}