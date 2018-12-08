package com.sangeetagupta.udacitypopularmovies1;

import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.sangeetagupta.udacitypopularmovies1.Utils.JSONUtils;
import com.sangeetagupta.udacitypopularmovies1.Utils.NetworkUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieListItemHandler {

    static public ArrayList<Movie> movies = new ArrayList<>();
    static private boolean POPULARITY = true;
    static private boolean TOP_RATED = false;
    private MovieAsyncTask movieAsyncTask = new MovieAsyncTask();

    RecyclerView movieRecyclerView;
    ProgressBar progressBar;
    TextView textView;
    MovieAdapter movieAdapter;
    GridLayoutManager gridLayoutManager;

    private class MovieAsyncTask extends AsyncTask<Boolean, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Boolean... filter) {

            if (filter.length == 0) {
                return true;
            }

            boolean sortFlag = filter[0];
            URL url = NetworkUtils.buildURLForMovies(sortFlag);
            try {
                String jsonData = NetworkUtils.getResponseFromHttpUrl(url);
                JSONUtils.getMovieList(jsonData);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("MainActivity", "This called!");
            return true;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            MainActivity.movies.clear();
            progressBar.setVisibility(View.VISIBLE);
            movieRecyclerView.setVisibility(View.INVISIBLE);
            textView.setVisibility(View.INVISIBLE);

        }

        @Override
        protected void onPostExecute(Boolean bool) {
            super.onPostExecute(bool);
            progressBar.setVisibility(View.INVISIBLE);
            if (MainActivity.movies.size() == 0 || MainActivity.movies == null) {
                textView.setText(getString(R.string.log_failed_data_retrieval));
                textView.setVisibility(View.VISIBLE);
                movieRecyclerView.setVisibility(View.INVISIBLE);
            } else {
                movieRecyclerView.setVisibility(View.VISIBLE);
                textView.setVisibility(View.INVISIBLE);
            }

            movieAdapter.notifyDataSetChanged();
        }
    }

    public void initialize() {
        setTitle(R.string.home_screen_title);

        textView = findViewById(R.id.log_view);
        progressBar = findViewById(R.id.connection_status);

        movieRecyclerView = findViewById(R.id.movie_poster_list);


        int gridColumnCount;
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            gridColumnCount = 2;
        } else {
            gridColumnCount = 3;
        }

        gridLayoutManager = new GridLayoutManager(this, gridColumnCount);
        movieAdapter = new MovieAdapter(this, this);

        new MovieAsyncTask().execute(POPULARITY);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        movieRecyclerView.setHasFixedSize(true);
        movieRecyclerView.setLayoutManager(gridLayoutManager);
        movieRecyclerView.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();

    }

    @Override
    public void onMovieItemClick(int index) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("MOVIE_ITEM_INDEX", index);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movie_menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.popularity_sort) {
            new MovieAsyncTask().execute(POPULARITY);
        } else if (item.getItemId() == R.id.rating_sort) {
            new MovieAsyncTask().execute(TOP_RATED);
        }
        return super.onOptionsItemSelected(item);
    }

}
