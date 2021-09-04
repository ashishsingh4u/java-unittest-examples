package com.techienotes.services;

import com.techienotes.models.Movie;
import com.techienotes.repositories.MovieRepository;

import java.util.List;
import java.util.stream.Collectors;

public class MovieService2 {
    private final MovieRepository movieRepository;

    public MovieService2(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getLatestMovie(int starCount, String year) {
        List<Movie> latestMovies = movieRepository.getLatestMovies(year);
        return latestMovies.stream().filter(movie -> movie.getStarCount() == starCount).collect(Collectors.toList());
    }
}
