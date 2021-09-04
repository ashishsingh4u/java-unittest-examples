package com.techienotes.services;

import com.techienotes.models.Movie;
import com.techienotes.repositories.MovieRepositoryMockImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MovieServiceMockTest {

    private MovieService movieService;
    private MovieRepositoryMockImpl movieRepository;

    @BeforeEach
    void setUp() {
        movieRepository = new MovieRepositoryMockImpl();
        movieService = new MovieService(movieRepository, new DummyNotificationService());
    }

    @Test
    void testSaveOnMockedMovieService() {
        Movie x = new Movie("X", "2020", 5);
        movieService.addMovie(x);
        Movie y = new Movie("Y", "2021", 4);
        movieService.addMovie(y);
        Movie z = new Movie("Z", "2019", 3);
        movieService.addMovie(z);

        movieRepository.verify(z, 3);
    }

    @Test
    void testSaveWithNullOnMockedObject() {
        movieService.addMovie(new Movie(null, "2020", 5));
        movieService.addMovie(new Movie(null, "2021", 4));
        movieService.addMovie(new Movie(null, "2019", 3));
        
        movieRepository.verify(null, 0);
    }
}
