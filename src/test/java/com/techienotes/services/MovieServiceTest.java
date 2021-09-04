package com.techienotes.services;

import com.techienotes.models.Movie;
import com.techienotes.repositories.FakeMovieRepositoryImpl;
import com.techienotes.repositories.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MovieServiceTest {

    private MovieService movieService;

    @BeforeEach
    void setUp() {
        movieService = new MovieService(new FakeMovieRepositoryImpl(), new DummyNotificationService());
    }

    @Test
    void testSaveOnMovieService() {
        movieService.addMovie(new Movie("X", "2020", 5));
        movieService.addMovie(new Movie("Y", "2021", 4));
        movieService.addMovie(new Movie("Z", "2019", 3));

        assertEquals(3, movieService.getAllMovies().size(), "Movies save failed");
    }

    @Test
    void testGetAllOnMovieService() {
        movieService.addMovie(new Movie("ABC", "2020", 5));
        movieService.addMovie(new Movie("XYZ", "2021", 4));
        movieService.addMovie(new Movie("DEF", "2019", 3));

        assertEquals(3, movieService.getAllMovies().size(), "Movies count is not equal");
    }

    @Test
    void testGetAllOnMovieServiceWithMockito() {
        List<Movie> movieList = new ArrayList<>();
        Movie x = new Movie("X", "2020", 5);
        movieList.add(x);
        Movie y = new Movie("Y", "2021", 4);
        movieList.add(y);
        Movie z = new Movie("Z", "2019", 3);
        movieList.add(z);

        // These two lines saved the effort to create fake repository
        MovieRepository movieRepository = mock(MovieRepository.class);
        when(movieRepository.getAllMovies()).thenReturn(movieList);

        // Creating dummy service to make our compiler happy
        NotificationService notificationService = mock(NotificationService.class);

        movieService = new MovieService(movieRepository, notificationService);
        assertEquals(3, movieService.getAllMovies().size(), "Movies count is not equal");
    }
}