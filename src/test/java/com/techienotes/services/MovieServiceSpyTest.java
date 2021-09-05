package com.techienotes.services;

import com.techienotes.models.Movie;
import com.techienotes.repositories.FakeMovieRepositoryImpl;
import com.techienotes.repositories.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
    void testSaveOnMovieServiceWithSpy() {
        MovieRepository repository = spy(MovieRepository.class);
        NotificationService notificationService = mock(NotificationService.class);
        MovieService movieService = new MovieService(repository, notificationService);

        Movie x = new Movie("X", "2020", 5);
        movieService.addMovie(x);
        Movie y = new Movie("Y", "2021", 4);
        movieService.addMovie(y);
        Movie z = new Movie(null, "2019", 3);
        movieService.addMovie(z);

        verify(repository, times(1)).save(x);
        verify(repository, times(1)).save(y);
        verify(repository, times(0)).save(z);
    }

    @Test
    void testSaveWithNull() {
        movieService.addMovie(new Movie(null, "2020", 5));
        movieService.addMovie(new Movie(null, "2021", 4));
        movieService.addMovie(new Movie(null, "2019", 3));

        assertEquals(0, movieRepository.getSaveCalledCounter(), "Movies save called counter is not zero");
    }
}
