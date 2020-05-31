package com.example.popularmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity implements MovieClickListener{
    private static final String API_PAGE_URL = "popular?api_key=862ba28e9076e5bb347d7ebb497bc8a2&page=";
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Movie> data;
    private iPopularMovies service;
    private Retrofit retrofit;
    private Runnable updateUI;
    private MovieComparator comparator;


    private Callback<Page> callBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = new ArrayList<>();
        String API_BASE_URL = "https://api.themoviedb.org/3/movie/";

        retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(iPopularMovies.class);

        updateUI = new Runnable() {
            @Override
            public void run() {
                movieAdapter.notifyDataSetChanged();
            }
        };
        comparator = new MovieComparator();

        callBack = new Callback<Page>() {


            @Override
            public void onResponse(Call<Page> call, Response<Page> response) {
                    Page page = response.body();
                    data.addAll(page.getMovies());
                    Collections.sort(data, comparator);
                    Log.d("Poster", "onResponse: "+ data.get(0).getPosterPath());
                    runOnUiThread(updateUI);
            }
            @Override
            public void onFailure(Call<Page> call, Throwable t) {
                t.printStackTrace();
            }
        };
        initiateRecyclerView();
        addAllMovieData();

    }
    public void initiateRecyclerView(){
        movieAdapter = new MovieAdapter(data);
        movieAdapter.setItemClickListener(MainActivity.this);
        Log.d("afterAdapter", "onCreate: virker det?");
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(movieAdapter);
    }

    public void addAllMovieData(){
        //thread that gets rest of data?
        new Thread(new Runnable() {
            @Override
            public void run() {
                int maxPages = 500;
                try {
                    for (int page = 1; page < maxPages; page++) {
                        Call<Page> aPage = service.getAPage(API_PAGE_URL + page);
                        aPage.enqueue(callBack);
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException ex){
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void showMovieDetails(int position, String baseURL) {
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        Movie movie = data.get(position);
        intent.putExtra("poster", baseURL + movie.getPosterPath());
        intent.putExtra("releaseDate", movie.getReleaseDate());
        intent.putExtra("title", movie.getTitle());
        intent.putExtra("descriptionTextView", movie.getOverview());
        intent.putExtra("languageView", movie.getOriginalLanguage());

        startActivity(intent);
    }
}
