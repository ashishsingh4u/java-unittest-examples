package com.techienotes.repositories;

import com.techienotes.models.Movie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class FakeMovieRepositoryImpl implements MovieRepository {
    List<Movie> movieList = new ArrayList<>();
    int saveCalledCounter = 0;
    Movie lastAddedMovie = null;

    public int getSaveCalledCounter() {
        return saveCalledCounter;
    }

    public Movie getLastAddedMovie() {
        return lastAddedMovie;
    }

    @Override
    public void save(Movie movie) {
        if (movie.getName() == null)
            return;
        this.movieList.add(movie);
        saveCalledCounter++;
        lastAddedMovie = movie;
    }

    @Override
    public Collection<Movie> getAllMovies() {
        return this.movieList;
    }

    @Override
    public List<Movie> getLatestMovies() {
        return this.movieList.stream().filter(movie -> movie.getReleaseYear().equals("2021")).collect(Collectors.toList());
    }
}
