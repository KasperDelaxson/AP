package com.example.popularmovies;

import java.util.Comparator;

public class MovieComparator implements Comparator<Movie> {
    @Override
    public int compare(Movie o1, Movie o2) {
        return Double.compare(o2.getPopularity(), o1.getPopularity());
    }
}
