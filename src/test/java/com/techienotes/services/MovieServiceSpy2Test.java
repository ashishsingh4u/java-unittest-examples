package com.techienotes.services;

import com.techienotes.models.Movie;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

@ExtendWith(MockitoExtension.class)
class MovieServiceSpy2Test {

    @InjectMocks
    private ComedyMovieService movieService;

    @Spy
    private ComedyMovieRepository movieRepository;

    @Test
    void testMockitoSpyWithoutAnnotation() {
        Movie movie = new Movie("X-Men", "2020", 4);

        /*
         If you try to use mock here then this test will fail. Mock will return the movie correctly when findMovieByName is called
         as soon as you call updateMovieStars on repository it will return null. But in our case since this method is implemented
         we would like to call the real method updateMovieStars.
        */
        ComedyMovieRepository repository = spy(ComedyMovieRepository.class);
        ComedyMovieService service = new ComedyMovieService(repository);
        doReturn(movie).when(repository).findMovieByName(movie.getName());

        Movie updatedMovie = service.updateMovieStars(movie.getName());
        assertEquals(movie.getName(), updatedMovie.getName(), "Movie name changed");
        assertEquals(5, updatedMovie.getStarCount(), "Star count is not updated");
    }

    @Test
    void testUpdateMovieStarsWithMock() {
        Movie movie = new Movie("X-Men", "2020", 4);

        ComedyMovieRepository repository = mock(ComedyMovieRepository.class);
        ComedyMovieService service = new ComedyMovieService(repository);
        doReturn(movie).when(repository).findMovieByName(movie.getName());

        // In this case since the repository object is mock and not spy this method will return null
        Movie updatedMovie = service.updateMovieStars(movie.getName());
        assertNull(updatedMovie, "Movie object is not null");
    }

    @Test
    void testMockitoSpyWithAnnotation() {
        Movie movie = new Movie("X-Men", "2020", 4);

        doReturn(movie).when(movieRepository).findMovieByName(movie.getName());

        Movie updatedMovie = movieService.updateMovieStars(movie.getName());
        assertEquals(movie.getName(), updatedMovie.getName(), "Movie name changed");
        assertEquals(5, updatedMovie.getStarCount(), "Star count is not updated");
    }
}
