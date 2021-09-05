package com.techienotes.repositories;

import com.techienotes.models.Movie;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MovieRepositoryImpl implements MovieRepository {
    @Override
    public void save(Movie movie) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<Movie> getAllMovies() {
        return Collections.emptyList();
    }

    @Override
    public List<Movie> getLatestMovies(String year) {
        return Collections.emptyList();
    }

    @Override
    public Movie findMovieByName(String name) {
        return null;
    }
}
