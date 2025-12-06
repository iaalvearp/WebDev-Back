package com.gruposiete.backend.cine.api;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoEntradaRepository extends JpaRepository<TipoEntrada, Long> {}