package com.appofkirtan.sgplayout;

import android.widget.ImageView;

public class MovieItem {
    private int movieResource;
    private String movie_name;


    public MovieItem(int movieResource, String movie_name) {
        this.movieResource = movieResource;
        this.movie_name = movie_name;
    }

    public int getMovieResource() {
        return movieResource;
    }

    public String getMovie_name() {
        return movie_name;
    }
}
