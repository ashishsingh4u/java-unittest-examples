package com.techienotes.services;

import com.techienotes.models.Movie;
import com.techienotes.repositories.FakeMovieRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MovieServiceSpyTest {

    private MovieService movieService;
    private FakeMovieRepositoryImpl movieRepository;

    @BeforeEach
    void setUp() {
        movieRepository = new FakeMovieRepositoryImpl();
        movieService = new MovieService(movieRepository, new DummyNotificationService());
    }

    @Test
    void testSaveOnMovieService() {
        Movie x = new Movie("X", "2020", 5);
        movieService.addMovie(x);
        Movie y = new Movie("Y", "2021", 4);
        movieService.addMovie(y);
        Movie z = new Movie("Z", "2019", 3);
        movieService.addMovie(z);

        assertEquals(3, movieRepository.getSaveCalledCounter(), "Movies save called counter is different");
        assertEquals(z, movieRepository.getLastAddedMovie(), "Last saved movie is different");
    }

    @Test
    void testSaveWithNull() {
        movieService.addMovie(new Movie(null, "2020", 5));
        movieService.addMovie(new Movie(null, "2021", 4));
        movieService.addMovie(new Movie(null, "2019", 3));

        assertEquals(0, movieRepository.getSaveCalledCounter(), "Movies save called counter is not zero");
    }
}
