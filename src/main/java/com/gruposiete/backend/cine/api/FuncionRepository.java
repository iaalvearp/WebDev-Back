package com.gruposiete.backend.cine.api;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface FuncionRepository extends JpaRepository<Funcion, Long> {
    List<Funcion> findByMovieId(Long movieId);
    List<Funcion> findByDate(LocalDate date);
}