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

    public void saveBook(Movie movie) {
        movieRepository.save(movie);
    }

    public void saveBookWithCheck(Movie movie) {
        Movie movieByName = movieRepository.findMovieByName(movie.getName());
        if (movieByName == null)
            movieRepository.save(movie);
    }

    public void saveBookWithClone(Movie movie) {
        movie = new Movie(movie.getName(), movie.getReleaseYear(), movie.getStarCount());
        movieRepository.save(movie);
    }

    public List<Movie> getLatestMovie(int starCount, String year) {
        List<Movie> latestMovies = movieRepository.getLatestMovies(year);
        return latestMovies.stream().filter(movie -> movie.getStarCount() == starCount).collect(Collectors.toList());
    }

    public String getMovieDetailsByName(String year) {
        List<Movie> latestMovies = movieRepository.getLatestMovies(year);
        StringBuilder builder = new StringBuilder();
        for (Movie movie : latestMovies) {
            Movie movieByName = movieRepository.findMovieByName(movie.getName());
            if (builder.length() != 0) {
                builder.append(", ");
            }
            builder.append(movieByName.getName());
        }
        return builder.toString();
    }
}
