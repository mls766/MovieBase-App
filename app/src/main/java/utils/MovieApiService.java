package utils;

import com.melisasan2017556451.filmuygulamasi.models.Movies;
import com.melisasan2017556451.filmuygulamasi.response.MovieListResponse;

import java.util.HashMap;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

//MovieApi is a interface for using  Retrofit

//HTTP operations
public interface MovieApiService {


    //get popular movies
    //https://api.themoviedb.org/3/movie/popular?api_key=44c2cf0f8bde21ab33099ae8b2c0b9c4
    @GET("/3/movie/popular")
    //The reason to write @GET ("/3/movie/popular") is that ;
    // it makes a request to the server by adding that coming after the BASE_URL("https://api.themoviedb.org/").
    Observable<MovieListResponse> getPopularMovies(
            @QueryMap HashMap<String,String> queries
    );


    //search for movies
    //https://api.themoviedb.org/3/search/movie?api_key={api_key}&query=Jack+Reacher
    @GET("/3/search/movie")
    //It send requests with @GET annotation
    Observable<MovieListResponse> getSearchedMovie(
            @QueryMap HashMap<String,String> queries
    );




    //get top rated movies
    //https://api.themoviedb.org/3/movie/top_rated?api_key=44c2cf0f8bde21ab33099ae8b2c0b9c4
    @GET("/3/movie/top_rated")
    Observable<MovieListResponse> getTopRatedMovies(
            @QueryMap HashMap<String,String> queries

    );


    //get latest movies
    //https://api.themoviedb.org/3/movie/now_playing?api_key=<<api_key>>
    @GET("/3/movie/now_playing")
    Observable<MovieListResponse> getLatestMovies(
            @QueryMap HashMap<String,String> queries

    );


    //get upcoming movies
    //https://api.themoviedb.org/3/movie/upcoming?api_key=<<api_key>>
    @GET("/3/movie/upcoming")
    Observable<MovieListResponse> getUpcomingMovies(
            @QueryMap HashMap<String,String> queries
    );



    //get movie detail
    //https://api.themoviedb.org/3/movie/{movie_id}?api_key=<<api_key>>
    @GET("/3/movie/{movie_id}")
    Observable<Movies> getMovieDetail(
            @Path ("movie_id") int id,
            @QueryMap HashMap<String,String> queries
    );




}
