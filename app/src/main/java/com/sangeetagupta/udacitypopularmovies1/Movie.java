package com.sangeetagupta.udacitypopularmovies1;

/**
 * Created by sangeetagupta1998 on 12/6/18.
 */

public class Movie {

    private String movieName;
    private String movieReleaseDate;
    private String movieSummary;
    private String movieImageUrl;
    private String movieRating;

    public Movie(String movieName, String movieRating, String movieSummary, String movieImageUrl, String movieReleaseDate) {
        this.movieImageUrl = movieImageUrl;
        this.movieName = movieName;
        this.movieRating = movieRating;
        this.movieSummary = movieSummary;
        this.movieReleaseDate = movieReleaseDate;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getMovieSummary() {
        return movieSummary;
    }

    public String getMovieImageUrl() {
        return movieImageUrl;
    }

    public String getMovieRating() {
        return movieRating;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

}
