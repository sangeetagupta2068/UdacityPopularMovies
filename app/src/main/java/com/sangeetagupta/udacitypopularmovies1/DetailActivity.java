package com.sangeetagupta.udacitypopularmovies1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    int movieItemIndex;
    ImageView moviePosterView;
    TextView movieReleaseView, movieDescriptionView, movieRatingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initialize();

        Intent intent = getIntent();
        if (intent.hasExtra("MOVIE_ITEM_INDEX")) {
            movieItemIndex = intent.getIntExtra("MOVIE_ITEM_INDEX", 0);
            setMovieView();
        }

    }

    public void initialize() {
        moviePosterView = findViewById(R.id.movie_poster);
        movieDescriptionView = findViewById(R.id.movie_plot_summary);
        movieRatingView = findViewById(R.id.movie_rating);
        movieReleaseView = findViewById(R.id.movie_release_date);
    }

    public void setMovieView() {
        setTitle(MainActivity.movies.get(movieItemIndex).getMovieName());

        movieRatingView.setText(String.format("%s: %s", getString(R.string.rating_label), MainActivity.movies.get(movieItemIndex).getMovieRating()));
        movieDescriptionView.setText(String.format("%s %s", getString(R.string.summary_label), MainActivity.movies.get(movieItemIndex).getMovieSummary()));
        movieReleaseView.setText(String.format("%s %s", getString(R.string.release_date_label), MainActivity.movies.get(movieItemIndex).getMovieReleaseDate()));

        Picasso.with(DetailActivity.this)
                .load(MainActivity.movies.get(movieItemIndex).getMovieImageUrl())
                .into(moviePosterView);
    }
}
