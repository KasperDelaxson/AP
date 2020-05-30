package com.example.popularmovies;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>  {
    private List<Movie> movies;
    private String basePictureURL;
    private MovieClickListener listener;

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
            avgRating = itemview.findViewById(R.id.rating);
            image = itemview.findViewById(R.id.poster);
            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.showMovieDetails(position, basePictureURL);
                        }
                    }
                }
            });
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView tView;

        public MyViewHolder(View v){
            super(v);
            TextView text = v.findViewById(R.id.recycler_view);
            tView = text;
            v.setOnClickListener(this);
        }
        public void onClick(View view){
            listener.showMovieDetails(getAdapterPosition(), basePictureURL);
        }
    }

    public void setItemClickListener(MovieClickListener listener){
        this.listener = listener;
    }
}
