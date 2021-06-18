package com.melisasan2017556451.filmuygulamasi.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.melisasan2017556451.filmuygulamasi.models.Genres;
import com.melisasan2017556451.filmuygulamasi.models.Movies;

import java.util.List;

public class GenreResponse {

    @SerializedName("results")
    @Expose()
    private List<Genres> genres;

    public List<Genres> getGenres() {
        return genres;
    }

    @Override
    public String toString() {
        return "GenreResponse{" +
                "genres=" + genres +
                '}';
    }
}
