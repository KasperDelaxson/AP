package com.example.popularmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Movie> data;
    private iPopularMovies service;


    private Callback<Page> callBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = new ArrayList<>();
        movieAdapter = new MovieAdapter(data);
        addInitialMovieData();
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(movieAdapter);
        service = RetrofitClientInstance.getRetrofitInstance().create(iPopularMovies.class);

        callBack = new Callback<Page>() {


            @Override
            public void onResponse(Call<Page> call, Response<Page> response) {
                data.addAll(response.body().getResults());
            }

            @Override
            public void onFailure(Call<Page> call, Throwable t) {

            }

            ;
            };
    }

    public void addInitialMovieData(){
        Call<Page> aPage = service.getFirstPage();
        aPage.enqueue(callBack);

    }

    public void addAllMovieData(){
        //thread that gets rest of data?
    }
}
