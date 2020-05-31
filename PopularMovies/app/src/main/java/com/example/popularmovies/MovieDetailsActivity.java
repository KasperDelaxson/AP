package com.example.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailsActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_data);
        Intent intent = getIntent();
        //Movie mov_intent = (Movie) intent.getSerializableExtra("detail");

        //poster
        String imagePoster = intent.getStringExtra("poster");
        ImageView posterView = findViewById(R.id.poster);
        Picasso.get().load(imagePoster).into(posterView);
        //release date
        String releaseDate = intent.getStringExtra("releaseDate");
        TextView releaseDateView = findViewById(R.id.releaseDate);
        releaseDateView.setText("Release Date: " + releaseDate);
        // title
        String title = intent.getStringExtra("title");
        TextView titleView = findViewById(R.id.title);
        titleView.setText(title);
        //overview
        String overview = intent.getStringExtra("descriptionTextView");
        TextView overviewView = findViewById(R.id.descriptionTextView);
        overviewView.setText("Overview: " +overview);
        //language
        String language = intent.getStringExtra("languageView");
        TextView languageTextView = findViewById(R.id.languageView);
        languageTextView.setText("Original Language: " + language);
    }


}
