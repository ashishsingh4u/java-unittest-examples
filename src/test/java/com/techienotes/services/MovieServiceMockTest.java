package com.techienotes.services;

import com.techienotes.models.Movie;
import com.techienotes.repositories.MovieRepository;
import com.techienotes.repositories.MovieRepositoryMockImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
    void testSaveOnMockedMovieServiceWithMockito() {
        MovieRepository repository = mock(MovieRepository.class);
        NotificationService notificationService = mock(NotificationService.class);
        MovieService movieService = new MovieService(repository, notificationService);

        Movie x = new Movie("X", "2020", 5);
        movieService.addMovie(x);
        Movie y = new Movie("Y", "2021", 4);
        movieService.addMovie(y);
        Movie z = new Movie("Z", "2019", 3);
        movieService.addMovie(z);

        Movie notSaved = new Movie("Undef", "2020", 0);

        verify(repository).save(x);
        verify(repository, times(1)).save(x);
        verify(repository).save(y);
        verify(repository).save(z);
        verify(repository, times(0)).save(notSaved);
    }

    @Test
    void testSaveWithNullOnMockedObject() {
        movieService.addMovie(new Movie(null, "2020", 5));
        movieService.addMovie(new Movie(null, "2021", 4));
        movieService.addMovie(new Movie(null, "2019", 3));

        movieRepository.verify(null, 0);
    }

    @Test
    void testSaveWithNullOnMockedObjectWithMockito() {
        MovieRepository repository = mock(MovieRepository.class);
        NotificationService notificationService = mock(NotificationService.class);
        MovieService movieService = new MovieService(repository, notificationService);

        Movie x = new Movie(null, "2020", 5);
        movieService.addMovie(x);
        Movie y = new Movie(null, "2021", 4);
        movieService.addMovie(y);
        Movie z = new Movie(null, "2019", 3);
        movieService.addMovie(z);

        verify(repository, times(0)).save(x);
        verify(repository, times(0)).save(y);
        verify(repository, times(0)).save(z);
        
        verify(repository, times(0)).save(Mockito.any());
    }
}
