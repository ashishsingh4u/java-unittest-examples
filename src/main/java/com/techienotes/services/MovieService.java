package com.techienotes.services;

import com.techienotes.exception.DatabaseException;
import com.techienotes.models.Movie;
import com.techienotes.models.MovieRequest;
import com.techienotes.repositories.MovieRepository;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

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

    public void addMovieWithException(Movie movie) {
        try {
            this.movieRepository.saveWithException(movie);
        } catch (SQLException sqlException) {
            throw new DatabaseException(sqlException);
        }
    }

    public List<Movie> getAllKidMovies() {
        try {
            return this.movieRepository.getAllKidMovies();
        } catch (SQLException sqlException) {
            throw new DatabaseException(sqlException);
        }
    }

    public Collection<Movie> getAllMovies() {
        return this.movieRepository.getAllMovies();
    }

    public void save(MovieRequest request) {
        Movie movie = new Movie(request.getName() + "_Updated", request.getReleaseYear(), request.getStarCount());
        this.movieRepository.save(movie);
    }
}
