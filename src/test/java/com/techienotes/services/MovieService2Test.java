package com.techienotes.services;

import com.techienotes.models.Movie;
import com.techienotes.repositories.MovieRepository;
import com.techienotes.repositories.MovieRepositoryStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MovieService2Test {

    private MovieService2 movieService2;

    @BeforeEach
    void setUp() {
        movieService2 = new MovieService2(new MovieRepositoryStub());
    }

    @Test
    void testLatestMoviesByStars() {
        List<Movie> movies = this.movieService2.getLatestMovie(1, "2021");
        assertEquals(0, movies.size(), "1 Star movies found");

        movies = this.movieService2.getLatestMovie(3, "2021");
        assertEquals(1, movies.size(), "1 Star movies found");

        movies = this.movieService2.getLatestMovie(4, "2021");
        assertEquals(1, movies.size(), "1 Star movies found");

        movies = this.movieService2.getLatestMovie(5, "2021");
        assertEquals(1, movies.size(), "1 Star movies found");
    }

    @Test
    void testLatestMoviesByStarsWithMockito() {
        MovieRepository movieRepository = mock(MovieRepository.class);
        when(movieRepository.getLatestMovies("2021")).thenReturn(
                Arrays.asList(new Movie("X", "2021", 3)
                        , new Movie("Y", "2021", 4)
                        , new Movie("Z", "2021", 5)));

        MovieService2 movieService2 = new MovieService2(movieRepository);

        List<Movie> movies = movieService2.getLatestMovie(1, "2021");
        assertEquals(0, movies.size(), "1 Star movies found");

        movies = movieService2.getLatestMovie(3, "2021");
        assertEquals(1, movies.size(), "1 Star movies found");

        movies = movieService2.getLatestMovie(4, "2021");
        assertEquals(1, movies.size(), "1 Star movies found");

        movies = movieService2.getLatestMovie(5, "2021");
        assertEquals(1, movies.size(), "1 Star movies found");
    }
}