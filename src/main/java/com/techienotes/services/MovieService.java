package com.techienotes.services;

import com.techienotes.models.Movie;
import com.techienotes.repositories.MovieRepository;

import java.util.Collection;

public class MovieService {
    private final MovieRepository movieRepository;
    private final NotificationService notificationService;

    public MovieService(MovieRepository movieRepository, NotificationService notificationService) {
        this.movieRepository = movieRepository;
        this.notificationService = notificationService;
    }

    public void addMovie(Movie movie) {
        if (movie.getName() != null)
            this.movieRepository.save(movie);
    }

    public Collection<Movie> getAllMovies() {
        return this.movieRepository.getAllMovies();
    }
}
