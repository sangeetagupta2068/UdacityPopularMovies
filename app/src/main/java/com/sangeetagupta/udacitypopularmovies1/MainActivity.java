package com.sangeetagupta.udacitypopularmovies1;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieListItemHandler {

    static ArrayList<Movie> movies = new ArrayList<>();
    static int gridColumnCount;

    RecyclerView movieRecyclerView;
    MovieAdapter movieAdapter;
    GridLayoutManager gridLayoutManager;

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
        Intent intent = new Intent(this,DetailActivity.class);
        intent.putExtra("MOVIE_ITEM_INDEX",index);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movie_menu_item,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.popularity_sort){
            Toast.makeText(this,"Popularity sort",Toast.LENGTH_SHORT).show();
        } else if(item.getItemId()== R.id.rating_sort){
            Toast.makeText(this,"Rating sort",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void initialize() {
        setTitle(R.string.home_screen_title);
        if (movies == null || movies.size() == 0) {
            movies.add(new Movie("abc",
                    "abc",
                    "abc",
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Grilled_ham_and_cheese_014.JPG/800px-Grilled_ham_and_cheese_014.JPG",
                    "20th March 2019"));
            movies.add(new Movie("abc",
                    "abc",
                    "abc",
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Grilled_ham_and_cheese_014.JPG/800px-Grilled_ham_and_cheese_014.JPG",
                    "20th March 2019"));
            movies.add(new Movie("abc",
                    "abc",
                    "abc",
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Grilled_ham_and_cheese_014.JPG/800px-Grilled_ham_and_cheese_014.JPG",
                    "20th March 2019"));
            movies.add(new Movie("abc",
                    "abc",
                    "abc",
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Grilled_ham_and_cheese_014.JPG/800px-Grilled_ham_and_cheese_014.JPG",
                    "20th March 2019"));
            movies.add(new Movie("abc",
                    "abc",
                    "abc",
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Grilled_ham_and_cheese_014.JPG/800px-Grilled_ham_and_cheese_014.JPG",
                    "20th March 2019"));
            movies.add(new Movie("abc",
                    "abc",
                    "abc",
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Grilled_ham_and_cheese_014.JPG/800px-Grilled_ham_and_cheese_014.JPG",
                    "20th March 2019"
            ));
            movies.add(new Movie("abc",
                    "abc",
                    "abc",
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Grilled_ham_and_cheese_014.JPG/800px-Grilled_ham_and_cheese_014.JPG",
                    "20th March 2019"));
        }

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            gridColumnCount = 2;
        } else {
            gridColumnCount = 3;
        }

        movieRecyclerView = findViewById(R.id.movie_poster_list);
        gridLayoutManager = new GridLayoutManager(this, gridColumnCount);
        movieAdapter = new MovieAdapter(this,this);

    }

}
