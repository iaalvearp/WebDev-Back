package com.gruposiete.backend.cine.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {
    
    // CORRECCIÓN: Usamos @Query para decirle explícitamente que busque dentro del objeto 'cinema' su 'id'
    @Query("SELECT s FROM Sala s WHERE s.cinema.id = :cinemaId")
    List<Sala> findByCinemaId(Long cinemaId);
}