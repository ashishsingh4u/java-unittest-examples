package com.techienotes.services;

import com.techienotes.models.Movie;
import com.techienotes.repositories.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class MovieService2Annotation2Test {

    @Mock
    MovieRepository movieRepository;

    @InjectMocks
    MovieService2 movieService2;

    /*
     This works as well to initialize the annotations with JUnit4
     @Rule
     MockitoRule rule = MockitoJUnit.rule();
    */

    @BeforeEach
    void setup() {
        // MockitoAnnotations.initMocks(this); This should be used in older versions of Junit and Mockito
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLatestMoviesByStarsWithMockitoAnnotations() {
        when(movieRepository.getLatestMovies("2021")).thenReturn(
                Arrays.asList(new Movie("X", "2021", 3)
                        , new Movie("Y", "2021", 4)
                        , new Movie("Z", "2021", 5)));

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
