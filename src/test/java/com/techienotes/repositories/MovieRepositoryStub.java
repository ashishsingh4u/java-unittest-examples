package com.techienotes.repositories;

import com.techienotes.models.Movie;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MovieRepositoryStub implements MovieRepository {
    @Override
    public void save(Movie movie) {
        throw new AssertionError("method is not required for testing. Code should not reach here");
    }

    @Override
    public Collection<Movie> getAllMovies() {
        throw new AssertionError("method is not required for testing. Code should not reach here");
    }

    @Override
    public List<Movie> getLatestMovies(String year) {
        return Stream.of(new Movie("X", "2021", 3)
                , new Movie("Y", "2021", 4)
                , new Movie("Z", "2021", 5)).filter(movie -> movie.getReleaseYear().equals(year)).collect(Collectors.toList());
    }

    @Override
    public Movie findMovieByName(String name) {
        return null;
    }
}
