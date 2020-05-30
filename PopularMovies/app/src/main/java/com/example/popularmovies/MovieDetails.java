package com.example.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class MovieDetails extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_data);
        Intent intent = getIntent();
        Movie mov_intent = (Movie) intent.getSerializableExtra("detail");

        String imagePoster = intent.getStringExtra("poster");
        ImageView view = findViewById(R.id.poster);
        // skal hente billeder via Glide/picasso

    }


}
