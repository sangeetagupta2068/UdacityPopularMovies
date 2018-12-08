package com.sangeetagupta.udacitypopularmovies1.Utils;

import com.sangeetagupta.udacitypopularmovies1.MainActivity;
import com.sangeetagupta.udacitypopularmovies1.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sangeetagupta1998 on 12/8/18.
 */

public class JSONUtils {

    private static String POSTER_BASE_URL = "http://image.tmdb.org/t/p/w500/";

    public static void getMovieList(String jsonString) {
        try {

            JSONObject moviesRootNode = new JSONObject(jsonString);
            JSONArray moviesResultNode = moviesRootNode.getJSONArray("results");

            for (int count = 0; count < moviesResultNode.length(); count++) {

                JSONObject movieJSON = moviesResultNode.getJSONObject(count);

                String movieName = movieJSON.getString("title");
                String movieImageURL = POSTER_BASE_URL + movieJSON.getString("poster_path");
                String movieSummary = movieJSON.getString("overview");
                String movieReleaseDate = movieJSON.getString("release_date");
                String movieRating = movieJSON.getString("vote_average");

                MainActivity.movies.add(new Movie(movieName, movieRating, movieSummary, movieImageURL, movieReleaseDate));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
