package com.example.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>  {
    private List<Movie> movies;
    private String basePictureURL;
    public MovieAdapter(List<Movie> movies){
        this.movies = movies;
        Log.d("movies", "MovieAdapter: "+ movies.size());
        this.basePictureURL = "http://image.tmdb.org/t/p/w185/";
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_view, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.title.setText(movies.get(position).getTitle());
        holder.releaseDate.setText(movies.get(position).getReleaseDate());
        String rating = Double.toString(movies.get(position).getVoteAverage());
        holder.avgRating.setText(rating);
        String picturePath = basePictureURL + movies.get(position).getPosterPath();
        Log.d("virk", "onBindViewHolder: "+ picturePath);
        Glide.with(holder.itemView).load(picturePath).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{
        public TextView title, releaseDate, avgRating;
        public ImageView image;

        public MovieViewHolder(@NonNull View itemview){
            super(itemview);
            title = itemview.findViewById(R.id.title);
            releaseDate = itemview.findViewById(R.id.releaseDate);
            avgRating = itemview.findViewById(R.id.avgRating);
            image = itemview.findViewById(R.id.poster);

        }


    }


}
