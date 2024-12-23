package com.alejandro.webapp.pfexamples.services;

import com.alejandro.webapp.pfexamples.entities.Rating;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Stateless;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Stateless
public class MovieManager {

    private final Map<String, List<Rating>> movieRatings = new HashMap<>();

    // Método que inicializa las películas y calificaciones predeterminadas.
    @PostConstruct
    public void init() {
        addMovie("Inception");
        movieRatings.get("Inception").add(new Rating("Inception", 5));
        movieRatings.get("Inception").add(new Rating("Inception", 4));

        addMovie("The Dark Knight");
        movieRatings.get("The Dark Knight").add(new Rating("The Dark Knight", 5));
        movieRatings.get("The Dark Knight").add(new Rating("The Dark Knight", 5));
        movieRatings.get("The Dark Knight").add(new Rating("The Dark Knight", 4));

        addMovie("Interstellar");
        movieRatings.get("Interstellar").add(new Rating("Interstellar", 4));
        movieRatings.get("Interstellar").add(new Rating("Interstellar", 4));
        movieRatings.get("Interstellar").add(new Rating("Interstellar", 3));

        addMovie("The Matrix");
        movieRatings.get("The Matrix").add(new Rating("The Matrix", 5));
        movieRatings.get("The Matrix").add(new Rating("The Matrix", 5));

        addMovie("Pulp Fiction");
        movieRatings.get("Pulp Fiction").add(new Rating("Pulp Fiction", 4));
        movieRatings.get("Pulp Fiction").add(new Rating("Pulp Fiction", 5));
    }

    // Permite añadir una nueva película al mapa.
    public void addMovie(String movie) {
        movieRatings.putIfAbsent(movie, new ArrayList<>());
    }

    // Añade una calificación para una película.
    public String addRating(String movie, int score) {
        if (!movieRatings.containsKey(movie)) {
            return "La película no existe. Por favor, agrégala primero.";
        }
        if (score < 1 || score > 5) {
            return "La calificación debe estar entre 1 y 5.";
        }
        // Crear un nuevo Rating y añadirlo a la lista de la película.
        movieRatings.get(movie).add(new Rating(movie, score));
        return "Calificación añadida correctamente.";
    }

    // Obtiene todas las calificaciones individuales como una lista de Ratings.
    public List<Rating> getAllRatings() {
        return movieRatings.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    // Obtiene las puntuaciones medias de cada película.
    public Map<String, Double> getAverageRatings() {
        return movieRatings.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey, // El nombre de la película como clave.
                        entry -> entry.getValue().stream()
                                .mapToInt(Rating::getScore) // Suma las puntuaciones.
                                .average()
                                .orElse(0.0) // Si no hay calificaciones, retorna 0.
                ));
    }
}
