package com.gruposiete.backend.cine.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // Permiso para que Astro entre
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Clase interna para recibir los datos del Login (DTO)
    public static class LoginRequest {
        public String email;
        public String password;
    }

    // Clase interna para responder (DTO)
    public static class LoginResponse {
        public String status;
        public String mensaje;
        public String nombre;
        public String rol;
        public Long id;

        public LoginResponse(String status, String mensaje, String nombre, String rol, Long id) {
            this.status = status;
            this.mensaje = mensaje;
            this.nombre = nombre;
            this.rol = rol;
            this.id = id;
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // 1. Buscar usuario por email
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(request.email);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            // 2. Verificar contraseña (OJO: En producción esto debería estar encriptado)
            if (usuario.getPassword().equals(request.password)) {
                return ResponseEntity.ok(new LoginResponse(
                    "success", 
                    "Bienvenido " + usuario.getNombre(), 
                    usuario.getNombre(), 
                    usuario.getRol(),
                    usuario.getId()
                ));
            }
        }

        // 3. Si falla
        return ResponseEntity.status(401).body(new LoginResponse("error", "Credenciales incorrectas", null, null, null));
    }
}