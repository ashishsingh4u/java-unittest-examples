package com.techienotes.services;

import com.techienotes.models.Movie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.startsWith;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ComedyServiceBDDTest {
    @Mock
    private ComedyMovieRepository comedyMovieRepository;

    @InjectMocks
    private ComedyMovieService comedyMovieService;

    @Test
    void testServiceWithTraditionalApproach() {
        Movie movie = new Movie("X-Men", "2020", 4);
        Movie updatedMovie = new Movie("X-Men", "2020", 5);

        when(comedyMovieRepository.findMovieByName(movie.getName())).thenReturn(movie);
        when(comedyMovieRepository.updateMovieStars(movie)).thenReturn(updatedMovie);

        Movie returnedMovie = comedyMovieService.updateMovieStars(movie.getName());
        assertEquals(movie.getName(), returnedMovie.getName(), "Movie name changed");
        assertEquals(5, returnedMovie.getStarCount(), "Star count is not updated");
    }

    @Test
    @DisplayName("Testing the service when repository methods findMovieByName and updateMovieStars are called")
    void test_Given_Movie_When_FindMovieByName_And_UpdateMovieStars_Then_Movie_IsUpdated() {

        // Arrange - Given
        Movie movie = new Movie("X-Men", "2020", 4);
        Movie updatedMovie = new Movie("X-Men", "2020", 5);

        given(comedyMovieRepository.findMovieByName(movie.getName())).willReturn(movie);
        given(comedyMovieRepository.updateMovieStars(movie)).willReturn(updatedMovie);

        // Act - When
        Movie returnedMovie = comedyMovieService.updateMovieStars(movie.getName());

        // Assert - Then
        assertEquals(movie.getName(), returnedMovie.getName(), "Movie name changed");
        assertEquals(5, returnedMovie.getStarCount(), "Star count is not updated");

        // AssertJ - BDDAssertion
        then(returnedMovie).isNotNull();
        then(returnedMovie.getName()).isEqualTo(movie.getName());
        then(returnedMovie.getStarCount()).isEqualTo(5);
    }

    @Test
    @DisplayName("When service method updateMovieStars called then repository should be called to update the movie")
    void test_Given_Movie_When_UpdateMovieStars_Then_Repository_UpdateMovieStars_Should_Called() {

        // Arrange - Given
        Movie movie = new Movie("X-Men", "2020", 4);

        given(comedyMovieRepository.findMovieByName(movie.getName())).willReturn(movie);

        // Act - When
        comedyMovieService.updateMovieStars(movie.getName());

        // Mockito - BDD Verification
        BDDMockito.then(comedyMovieRepository).should().updateMovieStars(movie);
    }

    @Test
    void test_Given_Movie_With_Argument_Matcher() {

        // Arrange - Given
        Movie movie = new Movie("X-Men", "2020", 4);

        /*
         Argument matcher should be provided for all the arguments. It's not possible to provide it just for one or two.
         And it cannot be used outside stubbing/verification of methods
        */
        given(comedyMovieRepository.findMovieByName(any(String.class))).willReturn(movie);

        // Act - When
        comedyMovieService.updateMovieStars(movie.getName());

        // Mockito - BDD Verification
        BDDMockito.then(comedyMovieRepository).should().updateMovieStars(movie);
    }

    @Test
    void test_Given_Movie_With_Argument_Matcher2() {

        // Arrange - Given
        Movie movie = new Movie("X-Men", "2020", 4);

        /*
         Argument matcher should be provided for all the arguments. It's not possible to provide it just for one or two.
         And it cannot be used outside stubbing/verification of methods
        */
        given(comedyMovieRepository.findMovieByName(anyString())).willReturn(movie);

        // Act - When
        comedyMovieService.updateMovieStars(movie.getName());

        // Mockito - BDD Verification
        BDDMockito.then(comedyMovieRepository).should().updateMovieStars(movie);
    }

    @Test
    void test_Given_Movie_With_Argument_Matcher3() {

        // Arrange - Given
        Movie movie = new Movie("X-Men", "2020", 4);

        /*
         Argument matcher should be provided for all the arguments. It's not possible to provide it just for one or two.
         And it cannot be used outside stubbing/verification of methods
        */
        given(comedyMovieRepository.findMovieByName(any())).willReturn(movie);

        // Act - When
        comedyMovieService.updateMovieStars(movie.getName());

        // Mockito - BDD Verification
        BDDMockito.then(comedyMovieRepository).should().updateMovieStars(movie);
    }

    @Test
    void test_Given_Movie_With_Argument_Matcher4() {

        // Arrange - Given
        Movie movie = new Movie("X-Men", "2020", 4);

        /*
         Argument matcher should be provided for all the arguments. It's not possible to provide it just for one or two.
         And it cannot be used outside stubbing/verification of methods
        */
        given(comedyMovieRepository.findMovieByName(eq("X-Men"))).willReturn(movie);

        // Act - When
        comedyMovieService.updateMovieStars(movie.getName());

        // Mockito - BDD Verification
        BDDMockito.then(comedyMovieRepository).should().updateMovieStars(movie);
    }

    @Test
    void test_Given_Movie_With_Argument_Matcher5() {

        // Arrange - Given
        Movie movie = new Movie("X-Men", "2020", 4);

        /*
         Argument matcher should be provided for all the arguments. It's not possible to provide it just for one or two.
         And it cannot be used outside stubbing/verification of methods
        */
        given(comedyMovieRepository.findMovieByName(startsWith("X-Men"))).willReturn(movie);

        // Act - When
        comedyMovieService.updateMovieStars(movie.getName());

        // Mockito - BDD Verification
        BDDMockito.then(comedyMovieRepository).should().updateMovieStars(movie);
    }
}
