package com.techienotes.services;

import com.techienotes.models.Movie;
import com.techienotes.repositories.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

//@RunWith(MockitoJUnitRunner.class) used in JUnit4. @ExtendWith is only available in JUnit5
@ExtendWith(MockitoExtension.class)
class MovieService2AnnotationTest {

    @Mock
    MovieRepository movieRepository;

    @InjectMocks
    MovieService2 movieService2;

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
