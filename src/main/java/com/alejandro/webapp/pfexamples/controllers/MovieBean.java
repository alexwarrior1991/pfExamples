package com.alejandro.webapp.pfexamples.controllers;

import com.alejandro.webapp.pfexamples.entities.Rating;
import com.alejandro.webapp.pfexamples.services.MovieManager;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named("movieBean")
@SessionScoped
public class MovieBean implements Serializable {

    @EJB
    private MovieManager movieManager;

    private String newMovie;     // Película que se va a añadir.
    private String selectedMovie; // Película seleccionada para asignar calificación.
    private int rating;          // Calificación asignada.

    // Getters y Setters
    public String getNewMovie() {
        return newMovie;
    }

    public void setNewMovie(String newMovie) {
        this.newMovie = newMovie;
    }

    public String getSelectedMovie() {
        return selectedMovie;
    }

    public void setSelectedMovie(String selectedMovie) {
        this.selectedMovie = selectedMovie;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    // Devuelve la lista de películas disponibles.
    public List<String> getMovieList() {
        return movieManager.getAverageRatings().keySet()
                .stream()
                .toList();
    }

    // Añade una nueva película al sistema.
    public String addMovie() {
        movieManager.addMovie(newMovie);
        return null; // Permanecer en la misma página.
    }

    // Añade una calificación para la película seleccionada.
    public String addRating() {
        return movieManager.addRating(selectedMovie, rating);
    }

    // Devuelve el promedio de calificaciones de todas las películas.
    public Map<String, Double> getAverageRatings() {
        return movieManager.getAverageRatings();
    }

    // Devuelve todas las calificaciones individuales como lista de Ratings.
    public List<Rating> getAllRatings() {
        return movieManager.getAllRatings();
    }
}
