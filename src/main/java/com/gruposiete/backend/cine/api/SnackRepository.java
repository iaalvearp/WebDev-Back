package com.gruposiete.backend.cine.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SnackRepository extends JpaRepository<Snack, Long> {
    // Método para buscar por categoría (ej: dame solo las bebidas)
    List<Snack> findByCategory(String category);
}