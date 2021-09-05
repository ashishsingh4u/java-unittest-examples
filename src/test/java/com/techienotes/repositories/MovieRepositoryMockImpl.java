package com.techienotes.repositories;

import com.techienotes.models.Movie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieRepositoryMockImpl implements MovieRepository {
    List<Movie> movieList = new ArrayList<>();
    int saveCalledCounter = 0;
    Movie lastAddedMovie = null;

    @Override
    public void save(Movie movie) {
        this.movieList.add(movie);
        saveCalledCounter++;
        lastAddedMovie = movie;
    }

    @Override
    public Collection<Movie> getAllMovies() {
        return this.movieList;
    }

    @Override
    public List<Movie> getLatestMovies(String year) {
        return this.movieList.stream().filter(movie -> movie.getReleaseYear().equals("2021")).collect(Collectors.toList());
    }

    public void verify(Movie movie, int times) {
        assertEquals(times, saveCalledCounter, "Movies save called counter is different");
        assertEquals(movie, lastAddedMovie, "Last saved movie is different");
    }
}
