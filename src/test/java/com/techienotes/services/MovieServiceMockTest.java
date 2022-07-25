package com.techienotes.services;

import com.techienotes.exception.DatabaseException;
import com.techienotes.models.Movie;
import com.techienotes.models.MovieRequest;
import com.techienotes.repositories.MovieRepository;
import com.techienotes.repositories.MovieRepositoryMockImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class MovieServiceMockTest {

    private MovieService movieService;
    private MovieRepositoryMockImpl movieRepository;

    @InjectMocks
    private MovieService service;

    @Mock
    private MovieRepository repository;

    @Mock
    private NotificationService notificationService;

    @Captor
    private ArgumentCaptor<Movie> movieArgumentCaptor;

    @BeforeEach
    void setUp() {
        movieRepository = new MovieRepositoryMockImpl();
        movieService = new MovieService(movieRepository, new DummyNotificationService());
        MockitoAnnotations.openMocks(this);
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

        // Method never() has the implementation as times(0)
        verify(repository, never()).save(Mockito.any());
    }

    @Test
    void testNullSaveOnMockedMovieServiceWithMockito() {
        MovieRepository repository = mock(MovieRepository.class);
        NotificationService notificationService = mock(NotificationService.class);
        MovieService movieService = new MovieService(repository, notificationService);

        Movie x = new Movie(null, "2020", 5);
        movieService.addMovie(x);

        verifyNoInteractions(repository);
    }

    @Test
    void testExceptionOnGetOnMockedMovieServiceWithMockito() throws SQLException {
        MovieRepository repository = mock(MovieRepository.class);
        NotificationService notificationService = mock(NotificationService.class);
        MovieService movieService = new MovieService(repository, notificationService);

        Movie x = new Movie("X", "2020", 5);
        when(repository.getAllKidMovies()).thenThrow(SQLException.class);

        assertThrows(DatabaseException.class, movieService::getAllKidMovies);
        assertThrows(DatabaseException.class, movieService::getAllKidMovies);
    }

    @Test
    void testExceptionOnGetOnMockedMovieServiceWithBDDMockito() throws SQLException {
        MovieRepository repository = mock(MovieRepository.class);
        NotificationService notificationService = mock(NotificationService.class);
        MovieService movieService = new MovieService(repository, notificationService);

        Movie x = new Movie("X", "2020", 5);
        given(repository.getAllKidMovies()).willThrow(SQLException.class);

        assertThrows(DatabaseException.class, movieService::getAllKidMovies);
        assertThrows(DatabaseException.class, movieService::getAllKidMovies);
    }

    @Test
    void testExceptionOnSaveOnMockedMovieServiceWithMockito() throws SQLException {
        MovieRepository repository = mock(MovieRepository.class);
        NotificationService notificationService = mock(NotificationService.class);
        MovieService movieService = new MovieService(repository, notificationService);

        Movie x = new Movie("X", "2020", 5);
        doThrow(SQLException.class).when(repository).saveWithException(x);

        assertThrows(DatabaseException.class, () -> movieService.addMovieWithException(x));
    }

    @Test
    void testArgumentCaptureOnSaveOnMockedMovieServiceWithMockito() {
        MovieRepository repository = mock(MovieRepository.class);
        NotificationService notificationService = mock(NotificationService.class);
        MovieService movieService = new MovieService(repository, notificationService);

        MovieRequest movieRequest = new MovieRequest("X", "2020", 5);
        ArgumentCaptor<Movie> argumentCaptor = ArgumentCaptor.forClass(Movie.class);
        movieService.save(movieRequest);
        verify(repository).save(argumentCaptor.capture());
        assertEquals("X_Updated", argumentCaptor.getValue().getName(), "Request message and movie are not equal");
    }

    @Test
    void testArgumentCaptureOnSaveWithAnnotationWithMockito() {
        MovieRequest movieRequest = new MovieRequest("X", "2020", 5);
        service.save(movieRequest);
        verify(repository).save(movieArgumentCaptor.capture());
        assertEquals("X_Updated", movieArgumentCaptor.getValue().getName(), "Request message and movie are not equal");
    }
}
