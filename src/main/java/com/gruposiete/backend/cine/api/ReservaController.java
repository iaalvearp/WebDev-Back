package com.gruposiete.backend.cine.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin(origins = "http://localhost:4321")
public class ReservaController {

    @Autowired private ReservaRepository reservaRepository;
    @Autowired private FuncionRepository funcionRepository;
    @Autowired private UsuarioRepository usuarioRepository;

    // 1. OBTENER RESERVAS (Para ver asientos ocupados)
    // Uso: GET /api/reservas?funcionId=1
    @GetMapping
    public List<Reserva> getReservas(@RequestParam(required = false) Long funcionId) {
        if (funcionId != null) {
            // Aquí filtramos todas las reservas que sean de esa función específica
            // Nota: Esto es ineficiente en sistemas grandes, pero para tu demo funciona perfecto.
            return reservaRepository.findAll().stream()
                    .filter(r -> r.getFuncion().getId().equals(funcionId))
                    .collect(Collectors.toList());
        }
        return reservaRepository.findAll();
    }

    // 2. CREAR RESERVA (Comprar)
    @PostMapping
    public ResponseEntity<?> createReserva(@RequestBody Reserva reserva) {
        try {
            // Asignar fecha actual si no viene
            if (reserva.getBookingDate() == null) {
                reserva.setBookingDate(LocalDateTime.now());
            }
            
            // Validar relaciones (opcional, pero recomendado para que no falle feo)
            if (reserva.getUsuario() != null && reserva.getUsuario().getId() != null) {
                usuarioRepository.findById(reserva.getUsuario().getId()).ifPresent(reserva::setUsuario);
            }
            if (reserva.getFuncion() != null && reserva.getFuncion().getId() != null) {
                funcionRepository.findById(reserva.getFuncion().getId()).ifPresent(reserva::setFuncion);
            }

            Reserva nueva = reservaRepository.save(reserva);
            return ResponseEntity.ok(nueva);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al procesar la reserva: " + e.getMessage());
        }
    }
}