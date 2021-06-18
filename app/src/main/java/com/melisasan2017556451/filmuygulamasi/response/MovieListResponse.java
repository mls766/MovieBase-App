package com.melisasan2017556451.filmuygulamasi.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.melisasan2017556451.filmuygulamasi.models.Movies;

import java.util.ArrayList;


//getting multiple movies(popular movies list)
// Model Class for API Response

public class MovieListResponse {

    @SerializedName("total_results")
    @Expose
    private int total_results;

    @SerializedName("total_pages")
    @Expose
    private int total_pages;

    @SerializedName("page")
    @Expose
    private int page;

    private ArrayList<Movies> results;

    public MovieListResponse(int total_results, int total_pages, int page, ArrayList<Movies> results) {
        this.total_results = total_results;
        this.total_pages = total_pages;
        this.page = page;
        this.results = results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setResults(ArrayList<Movies> results) {
        this.results = results;
    }

    public int getTotal_results(){
        return total_results;

    }

    public ArrayList<Movies> getResults(){

        return results;
    }

}
