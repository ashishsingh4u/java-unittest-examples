package com.techienotes.repositories;

import com.techienotes.models.Movie;

import java.util.Collection;
import java.util.List;

public interface MovieRepository {
    void save(Movie movie);

    Collection<Movie> getAllMovies();

    List<Movie> getLatestMovies();
}
