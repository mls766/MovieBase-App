package com.melisasan2017556451.filmuygulamasi.repositories;

import androidx.lifecycle.LiveData;

//import com.melisasan2017556451.filmuygulamasi.db.MovieDao;
import com.melisasan2017556451.filmuygulamasi.db.Favorite;
import com.melisasan2017556451.filmuygulamasi.db.MovieDao;
import com.melisasan2017556451.filmuygulamasi.db.Want;
import com.melisasan2017556451.filmuygulamasi.db.WantDao;
import com.melisasan2017556451.filmuygulamasi.db.Watched;
import com.melisasan2017556451.filmuygulamasi.db.WatchedDao;
import com.melisasan2017556451.filmuygulamasi.models.Movies;
import com.melisasan2017556451.filmuygulamasi.response.MovieListResponse;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import utils.MovieApiService;

//Getting livedata from MovieListViewModel and send it to repository
public class RepositoryMovie {

    //private static RepositoryMovie instance;
    private MovieDao movieDao;
    private WatchedDao watchedDao;
    private WantDao wantDao;
    private MovieApiService apiService;


   // For hilt to instantiate objects; use @Inject annotation above the constructor of Repository class so that whenever Repository object needed, hilt will provide a Repository Object.
   // The Repository class has four dependencies MovieApiService , MovieDao , WatchedDao , WantDao
    @Inject //for repository constructor
    public RepositoryMovie(MovieApiService apiService, MovieDao movieDao, WatchedDao watchedDao, WantDao wantDao) {
        this.movieDao = movieDao;
        this.watchedDao = watchedDao;
        this.wantDao = wantDao;
        this.apiService = apiService;

    }

    //getSearchedMovie method  returns the Observable of MovieListResponse type.
    public Observable<MovieListResponse> getSearchedMovie(HashMap<String, String> map) {
        return apiService.getSearchedMovie(map);
    }

    //getPopularMovie method  returns the Observable of MovieListResponse type.
    public Observable<MovieListResponse> getPopularMovies(HashMap<String, String> map) {
        return apiService.getPopularMovies(map);
    }

    //getTopRatedMovie method  returns the Observable of MovieListResponse type.
    public Observable<MovieListResponse> getTopRatedMovies(HashMap<String, String> map) {
        return apiService.getTopRatedMovies(map);
    }

    //getLatestMovie method  returns the Observable of MovieListResponse type.
    public Observable<MovieListResponse> getLatestMovies(HashMap<String, String> map) {
        return apiService.getLatestMovies(map);
    }

    //getUpcomingMovie method  returns the Observable of MovieListResponse type.
    public Observable<MovieListResponse> getUpcomingMovies(HashMap<String, String> map) {
        return apiService.getUpcomingMovies(map);
    }

    //getMovieDetail method  returns the Observable of Movie type.
    public Observable<Movies> getMovieDetail(int id, HashMap<String, String> map) {
        return apiService.getMovieDetail(id, map);
    }


    public void insertMovie(Favorite favorite) {
        movieDao.insertMovie(favorite);
    }

    public void deleteMovie(int movieId) {
        movieDao.deleteMovie(movieId);
    }

    public void deleteAll() {
        movieDao.deleteAll();
    }

    public LiveData<List<Favorite>> getFavoriteList() {
        return movieDao.getFavoriteList();
    }

    public Favorite getFavoriteMovie(int moviesId) {
        return movieDao.getFavoriteMovie(moviesId);
    }




    public void insertWatched(Watched watched){
        watchedDao.insertWatched(watched);
    }

    public void deleteWatched(int movieId){
        watchedDao.deleteWatched(movieId);
    }

    public void deleteAllWatched(){
        watchedDao.deleteAllWatched();
    }

    public LiveData<List<Watched>> getWatchedList(){
        return watchedDao.getWatchedList();
    }

    public Watched getWatchedMovie(int moviesId) {
        return watchedDao.getWatchedMovie(moviesId);
    }



    public void insertWant(Want want){
        wantDao.insertWatched(want);
    }

    public void deleteWant(int movieId){
        wantDao.deleteWant(movieId);
    }

    public void deleteAllWant(){
        wantDao.deleteAllWant();
    }

    public LiveData<List<Want>> getWantList(){ return wantDao.getWantList();}

    public Want getWantMovie(int moviesId) {
        return wantDao.getWantMovie(moviesId);
    }





}