package com.techienotes.repositories;

import com.techienotes.models.Movie;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public interface MovieRepository {
    void save(Movie movie);

    Collection<Movie> getAllMovies();

    List<Movie> getLatestMovies(String year);

    Movie findMovieByName(String name);

    default void saveWithException(Movie movie) throws SQLException {
        // Left blank intentionally
    }

    default List<Movie> getAllKidMovies() throws SQLException {
        return Collections.emptyList();
    }

}
