package com.techienotes.services;

import com.techienotes.models.Movie;
import com.techienotes.repositories.MovieRepository;
import com.techienotes.repositories.MovieRepositoryStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

    @Test
    void testFindBookWithWhenReturnMockito() {
        MovieRepository movieRepository = mock(MovieRepository.class);
        List<Movie> movies = Arrays.asList(new Movie("X", "2021", 3)
                , new Movie("Y", "2021", 4)
                , new Movie("Z", "2021", 5));
        when(movieRepository.getLatestMovies("2021")).thenReturn(movies);
        when(movieRepository.findMovieByName("X")).thenReturn(movies.get(0));
        when(movieRepository.findMovieByName("Y")).thenReturn(movies.get(1));
        when(movieRepository.findMovieByName("Z")).thenReturn(movies.get(2));

        MovieService2 movieService2 = new MovieService2(movieRepository);
        String movieDetailsByName = movieService2.getMovieDetailsByName("2021");
        assertEquals("X, Y, Z", movieDetailsByName, "Movie names are not correct");
    }

    @Test
    void testFindBookWithDoReturnWhenMockito() {
        MovieRepository movieRepository = mock(MovieRepository.class);
        List<Movie> movies = Arrays.asList(new Movie("X", "2021", 3)
                , new Movie("Y", "2021", 4)
                , new Movie("Z", "2021", 5));
        doReturn(movies).when(movieRepository).getLatestMovies("2021");
        doReturn(movies.get(0)).when(movieRepository).findMovieByName("X");
        doReturn(movies.get(1)).when(movieRepository).findMovieByName("Y");
        doReturn(movies.get(2)).when(movieRepository).findMovieByName("Z");

        MovieService2 movieService2 = new MovieService2(movieRepository);
        String movieDetailsByName = movieService2.getMovieDetailsByName("2021");
        assertEquals("X, Y, Z", movieDetailsByName, "Movie names are not correct");
    }

    @Test
    void testFindBookWithDoReturnWhenMockito2() {
        MovieRepository movieRepository = mock(MovieRepository.class);
        List<Movie> movies = Arrays.asList(new Movie("X", "2021", 3)
                , new Movie("Y", "2021", 4)
                , new Movie("Z", "2021", 5));
        when(movieRepository.getLatestMovies("2021")).thenReturn(movies);
        when(movieRepository.findMovieByName(Mockito.anyString())).thenReturn(movies.get(0), movies.get(1), movies.get(2));
        when(movieRepository.findMovieByName(Mockito.anyString()))
                .thenReturn(movies.get(0))
                .thenReturn(movies.get(1))
                .thenReturn(movies.get(2));

        MovieService2 movieService2 = new MovieService2(movieRepository);
        String movieDetailsByName = movieService2.getMovieDetailsByName("2021");
        assertEquals("X, Y, Z", movieDetailsByName, "Movie names are not correct");
    }

    @Test
    void testSaveBookWithMockito() {
        MovieRepository movieRepository = mock(MovieRepository.class);
        MovieService2 movieService2 = new MovieService2(movieRepository);

        Movie movie = new Movie("Z", "2021", 5);

        /*
         ByDefault Mockito uses equals to compare object. If not implemented on object then the Object's equal will called (==), which is reference equals
         Since both Movie refers to same object here, this test will be passed
        */
        doNothing().when(movieRepository).save(movie);
        movieService2.saveBook(movie);
        verify(movieRepository, times(1)).save(movie);
    }

    @Test
    void testSaveBookWithCloneWithMockito() {
        MovieRepository movieRepository = mock(MovieRepository.class);
        MovieService2 movieService2 = new MovieService2(movieRepository);

        Movie movie = new Movie("Z", "2021", 5);

        /*
         ByDefault Mockito uses equals to compare object. If not implemented on object then the Object's equal will called (==), which is reference equals
         Since both Movie refers to different object here (A new object was created in saveBookWithClone), this test will be failed
         To fix this equals and hashcode should be implemented in Movie class
        */
        doNothing().when(movieRepository).save(movie);
        movieService2.saveBookWithClone(movie);
        verify(movieRepository, times(1)).save(movie);
    }
}