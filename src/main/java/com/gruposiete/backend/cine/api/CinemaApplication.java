package com.gruposiete.backend.cine.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CinemaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaApplication.class, args);
        System.out.println("----------------------------------------");
        System.out.println("  ¡SISTEMA DE CINE INICIADO CON ÉXITO!  ");
        System.out.println("----------------------------------------");
	}

}