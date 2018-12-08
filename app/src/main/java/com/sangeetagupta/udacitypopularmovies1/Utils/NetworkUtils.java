package com.sangeetagupta.udacitypopularmovies1.Utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by sangeetagupta1998 on 12/8/18.
 */

public class NetworkUtils {

    private static final String API_KEY = "ce34cbe6a1ce756fd273d16fea7331ab";
    private static String SORT_TYPE = "popular";
    private static final String MOVIE_BASE_URL = "http://api.themoviedb.org/3/movie/";

    public static URL buildURLForMovies(boolean flag) {
        if (flag) {
            SORT_TYPE = "popular";
        } else {
            SORT_TYPE = "top_rated";
        }

        try {
            return new URL(MOVIE_BASE_URL+ SORT_TYPE + "?api_key=" + API_KEY);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

}
