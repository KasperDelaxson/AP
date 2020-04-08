package com.example.popularmovies;

import retrofit2.Call;
import retrofit2.http.GET;

public interface iPopularMovies {

    @GET("popular?api_key=862ba28e9076e5bb347d7ebb497bc8a2&Page=1")
    Call<Page> getFirstPage();
}
