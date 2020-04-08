package com.example.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
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
    private Context con;
    public MovieAdapter(List<Movie> movies){
        this.movies = movies;
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_view, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.title.setText(movies.get(position).getOriginalTitle());
        holder.releaseDate.setText(movies.get(position).getReleaseDate());
        String rating = Double.toString(movies.get(position).getVoteAverage());
        holder.avgRating.setText(rating);

        Glide.with(holder.itemView).load(movies.get(position).getPosterPath()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return this.movies.size();
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
