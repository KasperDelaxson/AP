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
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>  {
    private ArrayList<Movie> movies;
    private Context con;
    public MovieAdapter(ArrayList<Movie> movies){
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
        String rating = Float.toString(movies.get(position).getVoteAvg());
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
