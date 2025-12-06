package com.gruposiete.backend.cine.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final PeliculaRepository peliculaRepository;
    private final SnackRepository snackRepository;
    private final BeneficioRepository beneficioRepository;
    private final PromocionRepository promocionRepository;
    private final CityRepository cityRepository;
    private final CinemaRepository cinemaRepository;
    private final UsuarioRepository usuarioRepository;
    private final SalaRepository salaRepository;
    private final FuncionRepository funcionRepository;
    private final TipoEntradaRepository tipoEntradaRepository;
    private final GeneroRepository generoRepository;
    private final FormatoRepository formatoRepository;

    public DataSeeder(PeliculaRepository peliculaRepository, 
                      SnackRepository snackRepository,
                      BeneficioRepository beneficioRepository,
                      PromocionRepository promocionRepository,
                      CityRepository cityRepository,
                      CinemaRepository cinemaRepository,
                      UsuarioRepository usuarioRepository,
                      SalaRepository salaRepository,
                      FuncionRepository funcionRepository,
                      TipoEntradaRepository tipoEntradaRepository,
                      GeneroRepository generoRepository,
                      FormatoRepository formatoRepository) {
        this.peliculaRepository = peliculaRepository;
        this.snackRepository = snackRepository;
        this.beneficioRepository = beneficioRepository;
        this.promocionRepository = promocionRepository;
        this.cityRepository = cityRepository;
        this.cinemaRepository = cinemaRepository;
        this.usuarioRepository = usuarioRepository;
        this.salaRepository = salaRepository;
        this.funcionRepository = funcionRepository;
        this.tipoEntradaRepository = tipoEntradaRepository;
        this.generoRepository = generoRepository;
        this.formatoRepository = formatoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        
        // 0. CATÁLOGOS (Géneros y Formatos)
        if (generoRepository.count() == 0) {
            generoRepository.saveAll(Arrays.asList(
                new Genero("Acción"), new Genero("Aventura"), new Genero("Ciencia Ficción"),
                new Genero("Comedia"), new Genero("Drama"), new Genero("Fantasía"),
                new Genero("Musical"), new Genero("Terror"), new Genero("Suspenso"),
                new Genero("Animación"), new Genero("Familia"), new Genero("Romance")
            ));
        }

        // Formatos
        Formato f2d = null, f3d = null, fImax = null, f4d = null, fAtmos = null;
        
        if (formatoRepository.count() == 0) {
            f2d = formatoRepository.save(new Formato("2D"));
            f3d = formatoRepository.save(new Formato("3D"));
            fImax = formatoRepository.save(new Formato("IMAX"));
            f4d = formatoRepository.save(new Formato("4D"));
            fAtmos = formatoRepository.save(new Formato("ATMOS"));
        } else {
            List<Formato> all = formatoRepository.findAll();
            for(Formato f : all) {
                if(f.getName().equals("2D")) f2d = f;
                if(f.getName().equals("3D")) f3d = f;
                if(f.getName().equals("IMAX")) fImax = f;
                if(f.getName().equals("4D")) f4d = f;
                if(f.getName().equals("ATMOS")) fAtmos = f;
            }
        }

        // 1. USUARIOS
        if (usuarioRepository.count() == 0) {
            usuarioRepository.saveAll(Arrays.asList(
                new Usuario("Super Admin", "admin@cineplus.com", "admin123", "ADMIN"),
                new Usuario("Juan Perez", "juan@gmail.com", "123456", "USER")
            ));
        }

        // 2. CIUDADES
        if (cityRepository.count() == 0) {
            cityRepository.saveAll(Arrays.asList(
                new City("quito", "Quito"),
                new City("guayaquil", "Guayaquil"),
                new City("manta", "Manta")
            ));
        }

        // 3. CINES
        if (cinemaRepository.count() == 0) {
            cinemaRepository.saveAll(Arrays.asList(
                new Cinema("Multicines CCI", "Av. Amazonas y Naciones Unidas", "quito"),
                new Cinema("Multicines Scala", "Pasaje el Valle, Cumbayá", "quito"),
                new Cinema("Supercines Ceibos", "Av. Del Bombero Km 6.5", "guayaquil"),
                new Cinema("Multicines Mall del Sol", "Av. Joaquín Orrantia y Juan Tanca Marengo", "guayaquil"),
                new Cinema("Supercines Manta", "Av. 4 de Noviembre, Paseo Shopping", "manta"),
                new Cinema("Multicines Mall del Pacífico", "Av. Circunvalación y Calle 23", "manta")
            ));
        }

        // 4. PELÍCULAS
        if (peliculaRepository.count() == 0) {
            System.out.println("🌱 Cargando Películas...");
            List<Pelicula> peliculas = Arrays.asList(
                new Pelicula("Gladiador II", "Años después...", "Ridley Scott", "2h 28m", "+16", "/images/portadas/gladiador-min.webp", "/images/banner/gladiador-banner.webp", Arrays.asList("Acción", "Drama"), Arrays.asList("Paul Mescal"), Arrays.asList(f2d, fImax, f4d), 2024, false),
                new Pelicula("Wicked", "La historia no contada...", "Jon M. Chu", "2h 40m", "TP", "/images/portadas/wicked-min.webp", "/images/banner/wicked-banner.webp", Arrays.asList("Musical"), Arrays.asList("Ariana Grande"), Arrays.asList(f2d, f3d, fAtmos), 2024, false),
                new Pelicula("Moana 2", "Tras recibir una llamada...", "David G.", "1h 40m", "TP", "/images/portadas/moana-min.webp", "/images/banner/moana-banner.webp", Arrays.asList("Animación"), Arrays.asList("La Roca"), Arrays.asList(f2d, f3d), 2024, true),
                new Pelicula("Sonic 3", "Sinopsis...", "Jeff Fowler", "1h 49m", "TP", "/images/portadas/sonic-min.webp", "/images/banner/sonic-banner.webp", Arrays.asList("Acción"), Arrays.asList("Jim Carrey"), Arrays.asList(f2d), 2024, true),
                new Pelicula("Mufasa", "Sinopsis...", "Barry Jenkins", "1h 58m", "TP", "/images/portadas/rey_leon-min.webp", "/images/banner/rey_leon-banner.webp", Arrays.asList("Aventura"), Arrays.asList("Aaron Pierre"), Arrays.asList(f2d, f3d, fImax), 2024, true)
            );
            peliculaRepository.saveAll(peliculas);
        }

        // 5. SNACKS (FORZAMOS RECARGA)
        System.out.println("🔄 Refrescando Dulcería...");
        snackRepository.deleteAll(); // <--- BORRA LO QUE HAYA ANTES
        List<Snack> snacks = Arrays.asList(
            new Snack("Combo Clásico", "Popcorn grande + 2 bebidas", 12.50, "combos", "/images/snacks/combo-clasico.webp"),
            new Snack("Combo Familiar", "2 Popcorn grandes + 4 bebidas", 22.00, "combos", "/images/snacks/combo-familiar.webp"),
            new Snack("Combo Premium", "Popcorn jumbo + nachos", 18.50, "combos", "/images/snacks/combo-premium.webp"),
            new Snack("Combo Duo", "2 Popcorn medianos", 15.00, "combos", "/images/snacks/combo-duo.webp"),
            new Snack("Popcorn Pequeño", "Palomitas recién hechas", 4.50, "popcorn", "/images/snacks/popcorn-pequenio.webp"),
            new Snack("Popcorn Mediano", "Palomitas recién hechas", 6.00, "popcorn", "/images/snacks/popcorn-mediano.webp"),
            new Snack("Popcorn Grande", "Palomitas recién hechas", 7.50, "popcorn", "/images/snacks/popcorn-grande.webp"),
            new Snack("Popcorn Jumbo", "Tamaño familiar", 9.00, "popcorn", "/images/snacks/popcorn-jumbo.webp"),
            new Snack("Nachos con Queso", "Con queso cheddar caliente", 6.50, "snacks", "/images/snacks/nachos.webp"),
            new Snack("Hot Dog", "Con salchicha premium", 4.50, "snacks", "/images/snacks/hotdog.webp"),
            new Snack("Pizza Personal", "De pepperoni", 5.50, "snacks", "/images/snacks/pizza.webp"),
            new Snack("Nuggets", "6 unidades", 5.00, "snacks", "/images/snacks/nuggets.webp"),
            new Snack("Bebida Mediana", "Gaseosa", 3.50, "bebidas", "/images/snacks/bebida-mediana.webp"),
            new Snack("Bebida Grande", "Gaseosa", 4.50, "bebidas", "/images/snacks/bebida-grande.webp"),
            new Snack("Agua Mineral", "500ml", 2.50, "bebidas", "/images/snacks/agua-mineral.webp"),
            new Snack("ICEE", "Cereza o uva", 5.00, "bebidas", "/images/snacks/icee.webp"),
            new Snack("M&M's", "Clásicos", 3.50, "dulces", "/images/snacks/mms.webp"),
            new Snack("Skittles", "Frutales", 3.00, "dulces", "/images/snacks/skittles.webp"),
            new Snack("Gomitas", "Surtidas", 3.00, "dulces", "/images/snacks/gummies.webp"),
            new Snack("Chocolate", "Con leche", 3.50, "dulces", "/images/snacks/barra-chocolate.webp")
        );
        snackRepository.saveAll(snacks);
        

        // 6. BENEFICIOS (FORZAMOS RECARGA)
        System.out.println("🔄 Refrescando Beneficios...");
        beneficioRepository.deleteAll(); // <--- BORRA LO QUE HAYA ANTES
        beneficioRepository.saveAll(Arrays.asList(
            new Beneficio("CineFan Club", "Acumula puntos...", "star", "yellow"),
            new Beneficio("2x1 en Entradas", "Martes...", "ticket", "green"),
            new Beneficio("Cumpleaños Gratis", "Regalo...", "gift", "pink"),
            new Beneficio("Descuento Familiar", "Grupos...", "users", "blue")
        ));

        // 7. PROMOCIONES (FORZAMOS RECARGA)
        System.out.println("🔄 Refrescando Promociones...");
        promocionRepository.deleteAll(); // <--- BORRA LO QUE HAYA ANTES
        promocionRepository.saveAll(Arrays.asList(
            new Promocion("Martes de Cine", "$5.00", "50% OFF", "Martes", "🎬"),
            new Promocion("Combo Estudiante", "Carnet", "30% OFF", "Todo el año", "📚"),
            new Promocion("Happy Hour", "Antes 15:00", "40% OFF", "Lun-Vie", "☀️"),
            new Promocion("Pack Navideño", "4 entradas", "ESPECIAL", "Dic", "🎄")
        ));

        // 8. SALAS Y FUNCIONES (BLINDADO)
        if (salaRepository.count() == 0) {
            // Buscamos el cine por nombre para no fallar con índices
            List<Cinema> cines = cinemaRepository.findAll();
            Cinema scala = cines.stream().filter(c -> c.getName().contains("Scala")).findFirst().orElse(null);
            
            if (scala != null) {
                System.out.println("💺 Creando Salas para " + scala.getName());
                salaRepository.saveAll(Arrays.asList(
                    new Sala("Sala 1", "Normal", 100, scala),
                    new Sala("Sala 2", "Normal", 100, scala),
                    new Sala("Sala VIP", "VIP", 50, scala),
                    new Sala("Sala 4D", "4D", 60, scala)
                ));
            }
        }

        if (tipoEntradaRepository.count() == 0) {
            tipoEntradaRepository.saveAll(Arrays.asList(
                new TipoEntrada("General", 6.50, "Estándar"),
                new TipoEntrada("Niños / 3ra Edad", 3.25, "Descuento"),
                new TipoEntrada("VIP", 12.00, "Reclinable"),
                new TipoEntrada("IMAX", 10.50, "Gigante"),
                new TipoEntrada("4D", 11.25, "Movimiento")
            ));
        }

        if (funcionRepository.count() == 0 && peliculaRepository.count() > 0) {
            System.out.println("🎬 Creando Funciones...");
            
            List<Pelicula> pelis = peliculaRepository.findAll();
            Pelicula gladiador = pelis.stream().filter(p -> p.getTitle().contains("Gladiador")).findFirst().orElse(null);
            Pelicula wicked = pelis.stream().filter(p -> p.getTitle().contains("Wicked")).findFirst().orElse(null);
            
            List<Cinema> cines = cinemaRepository.findAll();
            Cinema scala = cines.stream().filter(c -> c.getName().contains("Scala")).findFirst().orElse(null);
            
            if (gladiador != null && scala != null) {
                List<Sala> salasScala = salaRepository.findByCinemaId(scala.getId());
                
                if (!salasScala.isEmpty()) {
                    Sala salaNormal1 = salasScala.get(0);
                    Sala sala4D = salasScala.stream().filter(s -> s.getType().equals("4D")).findFirst().orElse(salasScala.get(0));

                    LocalDate hoy = LocalDate.now();

                    funcionRepository.saveAll(Arrays.asList(
                        new Funcion(gladiador, salaNormal1, hoy, "10:30", "2D", "ESP", 6.50, true),
                        new Funcion(gladiador, salaNormal1, hoy, "12:55", "2D", "ESP", 6.50, true),
                        new Funcion(gladiador, sala4D, hoy, "11:25", "4D", "ESP", 11.25, true),
                        new Funcion(wicked, salaNormal1, hoy, "16:00", "2D", "ESP", 6.50, true)
                    ));
                }
            }
        }

        System.out.println("✅ ¡Base de datos COMPLETAMENTE actualizada!");
    }
}