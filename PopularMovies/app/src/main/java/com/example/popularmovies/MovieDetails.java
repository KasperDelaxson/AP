package com.example.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

public class MovieDetails extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_data);
        Intent intent = getIntent();
        //Movie mov_intent = (Movie) intent.getSerializableExtra("detail");

        //poster
        String imagePoster = intent.getStringExtra("poster");
        ImageView posterView = findViewById(R.id.poster);
        Glide.with(this).load(imagePoster).into(posterView);
        //release date
        String releaseDate = intent.getStringExtra("releaseDate");
        TextView releaseDateView = findViewById(R.id.releaseDate);
        releaseDateView.setText(releaseDate);
        //avg rating
        String avgRating = intent.getStringExtra("avgRating");
        TextView avgRatingView = findViewById(R.id.avgRating);
        avgRatingView.setText(avgRating);
        // title
        String title = intent.getStringExtra("title");
        TextView titleView = findViewById(R.id.title);
        titleView.setText(title);

        // skal hente billeder via Glide/picasso

    }


}
