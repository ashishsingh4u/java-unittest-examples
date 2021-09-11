package com.techienotes.services;

import com.techienotes.models.Movie;

public class ComedyMovieRepository {
    public Movie findMovieByName(String movieName) {
        // This has to be implemented. For testing please mock it.
        return null;
    }

    public Movie updateMovieStars(Movie movie) {
        movie.setStarCount(movie.getStarCount() + 1);
        return movie;
    }
}
