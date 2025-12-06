package com.gruposiete.backend.cine.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // "/**" significa: Aplica esto a TODAS las URLs de mi API
                registry.addMapping("/**")
                        // .allowedOrigins: ¿Quién tiene permiso de entrar?
                        // Aquí pondremos la dirección de tu Astro (4321)
                        .allowedOrigins("*")
                        // .allowedMethods: ¿Qué pueden hacer? (Leer, Guardar, Borrar, etc.)
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
            }
        };
    }
}