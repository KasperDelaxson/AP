package com.example.popularmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Movie> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = addInitialMovieData();
        movieAdapter = new MovieAdapter(data);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(movieAdapter);

    }

    public ArrayList<Movie> addInitialMovieData(){

    }

    public void addAllMovieData(){

    }
}
