package com.example.popularmovies;

import java.util.Comparator;

public class MovieComparator implements Comparator<Movie> {
    @Override
    public int compare(Movie o1, Movie o2) {
        return Double.compare(o1.getPopularity(), o2.getPopularity());
    }
}
