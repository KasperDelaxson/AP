package com.example.popularmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity{
    private static final String API_PAGE_URL = "popular?api_key=862ba28e9076e5bb347d7ebb497bc8a2&page=";
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Movie> data;
    private iPopularMovies service;
    private Retrofit retrofit;
    private Runnable updateUI;

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



        callBack = new Callback<Page>() {


            @Override
            public void onResponse(Call<Page> call, Response<Page> response) {
                    Page page = response.body();
                    data.addAll(page.getMovies());
                    Log.d("Poster", "onResponse: "+ data.get(0).getPosterPath());
                    if(movieAdapter==null) {
                        initiateRecyclerView();
                    }else{
                        updateData();

                    }


            }

            @Override
            public void onFailure(Call<Page> call, Throwable t) {
                t.printStackTrace();

            }


        };
        addInitialMovieData();
        addAllMovieData();




    }
    public void initiateRecyclerView(){
        movieAdapter = new MovieAdapter(data);
        Log.d("afterAdapter", "onCreate: virker det?");
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(movieAdapter);
    }

    public void addInitialMovieData(){
        Call<Page> aPage = service.getAPage(API_PAGE_URL+1);
        aPage.enqueue(callBack);

    }

    public void addAllMovieData(){
        //thread that gets rest of data?
        new Thread(new Runnable() {
            @Override
            public void run() {
                int maxPages = 500;
                for(int page=2; page<maxPages; page++){
                    Call<Page> aPage = service.getAPage(API_PAGE_URL+page);
                    aPage.enqueue(callBack);
                }

            }
        }).start();
    }

    public void updateData(){
        runOnUiThread(updateUI);
    }
}
