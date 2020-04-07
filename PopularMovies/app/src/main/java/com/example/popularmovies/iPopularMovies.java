package com.example.popularmovies;

import retrofit2.Call;
import retrofit2.http.GET;

public interface iPopularMovies {

    @GET("")
    Call<Page> getFirstPage();
}
