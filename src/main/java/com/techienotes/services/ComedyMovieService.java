package com.techienotes.services;

import com.techienotes.models.Movie;

public class ComedyMovieService {

    private final ComedyMovieRepository repository;

    public ComedyMovieService(ComedyMovieRepository repository) {
        this.repository = repository;
    }

    public Movie updateMovieStars(String movieName) {
        Movie movie = repository.findMovieByName(movieName);
        movie = repository.updateMovieStars(movie);
        return movie;
    }
}
