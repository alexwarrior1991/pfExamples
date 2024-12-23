package com.alejandro.webapp.pfexamples.entities;

public class Rating {

    private String movie;
    private int score;

    public Rating(String movie, int score) {
        this.movie = movie;
        this.score = score;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
