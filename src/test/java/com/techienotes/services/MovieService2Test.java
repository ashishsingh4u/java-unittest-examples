package com.techienotes.services;

import com.techienotes.models.Movie;
import com.techienotes.repositories.MovieRepositoryStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MovieService2Test {

    private MovieService2 movieService2;

    @BeforeEach
    void setUp() {
        movieService2 = new MovieService2(new MovieRepositoryStub());
    }

    @Test
    void testLatestMoviesByStars() {
        List<Movie> movies = this.movieService2.getLatestMovie(1);
        assertEquals(0, movies.size(), "1 Star movies found");

        movies = this.movieService2.getLatestMovie(3);
        assertEquals(1, movies.size(), "1 Star movies found");

        movies = this.movieService2.getLatestMovie(4);
        assertEquals(1, movies.size(), "1 Star movies found");

        movies = this.movieService2.getLatestMovie(5);
        assertEquals(1, movies.size(), "1 Star movies found");
    }
}