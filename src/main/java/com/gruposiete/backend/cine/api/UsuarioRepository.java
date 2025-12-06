package com.gruposiete.backend.cine.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Método mágico: Spring crea el SQL automáticamente para buscar por email
    Optional<Usuario> findByEmail(String email);
    
    // Para verificar si existe antes de registrar
    Boolean existsByEmail(String email);
}