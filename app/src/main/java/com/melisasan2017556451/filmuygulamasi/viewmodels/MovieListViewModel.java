package com.melisasan2017556451.filmuygulamasi.viewmodels;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.melisasan2017556451.filmuygulamasi.db.Favorite;
import com.melisasan2017556451.filmuygulamasi.db.Want;
import com.melisasan2017556451.filmuygulamasi.db.Watched;
import com.melisasan2017556451.filmuygulamasi.models.Genres;
import com.melisasan2017556451.filmuygulamasi.models.Movies;
import com.melisasan2017556451.filmuygulamasi.repositories.RepositoryMovie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieListViewModel extends ViewModel {
    private static final String TAG = "MovieListViewModel";

    //Disposables is used to control the lifecycle of an Observable.It handles wastes on stream between Observable and Observers
    private final io.reactivex.rxjava3.disposables.CompositeDisposable disposables = new CompositeDisposable();

    private RepositoryMovie repositoryMovie;


    //If some methods are to be called on a LiveData, MutableLiveData should be used.
    private MutableLiveData<ArrayList<Movies>> queryMovies = new MutableLiveData<>();

    private MutableLiveData<ArrayList<Movies>> PopularMoviesList = new MutableLiveData<>();

    private MutableLiveData<ArrayList<Movies>> TopMoviesList = new MutableLiveData<>();

    private MutableLiveData<ArrayList<Movies>> LatestMoviesList = new MutableLiveData<>();

    private MutableLiveData<ArrayList<Movies>> UpcomingMoviesList = new MutableLiveData<>();

    private MutableLiveData<Movies> MovieDetail = new MutableLiveData<>();

    private LiveData<Favorite> favorite;
    private LiveData<Watched> watched;
    private LiveData<Want> want;



    //Similar to @Inject, a ViewModel containing a constructor annotated with @ViewModelInject
    @ViewModelInject
    public MovieListViewModel(RepositoryMovie repositoryMovie) {
        this.repositoryMovie = repositoryMovie;

    }

    public MutableLiveData<ArrayList<Movies>> getQueriesMovies()
    {

        return  queryMovies;
    }


    public MutableLiveData<ArrayList<Movies>> getPopularMoviesList() { return PopularMoviesList; }

    public MutableLiveData<ArrayList<Movies>> getTopRatedMoviesList() {

        return TopMoviesList;
    }

    public MutableLiveData<ArrayList<Movies>> getLatestMoviesList()
    {
        return LatestMoviesList;
    }

    public MutableLiveData<ArrayList<Movies>> getUpcomingMoviesList()
    {
        return UpcomingMoviesList;
    }

    public MutableLiveData<Movies> getMovie() {

        return MovieDetail;
    }


    public void getSearchedMovies(HashMap<String, String> map){
        disposables.add(repositoryMovie.getSearchedMovie(map)
                .subscribeOn(Schedulers.io())
                //subscribeOn(Schedulers.io()) means that we want to subscribe on the background thread
                .observeOn(AndroidSchedulers.mainThread())
                //observeOn(AndroidSchedulers.mainThread()) means that we want to observe the data on the main thread
                .subscribe(result -> queryMovies.setValue(result.getResults()),
                        error -> Log.e(TAG, "getMovieDetail: " + error.getMessage()))

        );
    }


  //repositoryMovie.getPopularMovies() will return an Observable, it observed here and then set the PopularMoviesList(Mutable Live Data) to update the fragment of the changes.
    public void getPopularMovies(HashMap<String, String> map) {
        disposables.add(repositoryMovie.getPopularMovies(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> PopularMoviesList.setValue(result.getResults()),
                        error -> Log.e(TAG, "getPopularMovies: " + error.getMessage()))
        );
    }

    //repositoryMovie.getTopRatedMovies() will return an Observable, it observed here and then set the TopMoviesList(Mutable Live Data) to update the fragment of the changes.
    public void getTopRatedMovies(HashMap<String, String> map) {
        disposables.add(repositoryMovie.getTopRatedMovies(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> TopMoviesList.setValue(result.getResults()),
                        error -> Log.e(TAG, "getTopMovies: " + error.getMessage()))
        );
    }

    //repositoryMovie.getLatestMovies() will return an Observable, it observed here and then set the LatestMoviesList(Mutable Live Data) to update the fragment of the changes.
    public void getLatestMovies(HashMap<String, String> map) {
        disposables.add(repositoryMovie.getLatestMovies(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> LatestMoviesList.setValue(result.getResults()),
                        error -> Log.e(TAG, "getLatestMovies: " + error.getMessage()))
        );
    }


    //repositoryMovie.getUpcomingMovies() will return an Observable, it observed here and then set the UpcomingMoviesList(Mutable Live Data) to update the fragment of the changes.
    public void getUpcomingMovies(HashMap<String, String> map) {
        disposables.add(repositoryMovie.getUpcomingMovies(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> UpcomingMoviesList.setValue(result.getResults()),
                        error -> Log.e(TAG, "getUpcomingMovies: " + error.getMessage()))
        );
    }


    public void getMovieDetail(int movieId, HashMap<String, String> map) {
        disposables.add(repositoryMovie.getMovieDetail(movieId, map)
                .subscribeOn(Schedulers.io())
                .map(new Function<Movies, Movies>()

                {
                    @Override
                    public Movies apply(Movies movies) throws Throwable {
                        ArrayList<String> genreNames = new ArrayList<>();
                        // Movies gives list of genre(object) so we will map each id to it genre name here

                        for(Genres genres : movies.getGenres()){
                            genreNames.add(genres.getName());
                        }
                        movies.setGenre_names(genreNames);
                        return movies;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> MovieDetail.setValue(result),
                        error -> Log.e(TAG, "getMovieDetail: " + error.getMessage()))
        );
    }
    public void insertMovie(Favorite favorite){
        repositoryMovie.insertMovie(favorite);
    }

    public void deleteMovie(int moviesId){
        repositoryMovie.deleteMovie(moviesId);
    }

    public void deleteAll(){
        repositoryMovie.deleteAll();
    }

    public Favorite getFavoriteMovie(int movieId){
        return  repositoryMovie.getFavoriteMovie(movieId);
    }


    public void insertWatched(Watched watched){
        repositoryMovie.insertWatched(watched);
    }

    public void deleteWatched(int moviesId){
        repositoryMovie.deleteWatched(moviesId);
    }

    public void deleteAllWatched(){
        repositoryMovie.deleteAllWatched();
    }

    public Watched getWatchedMovie(int movieId){
        return  repositoryMovie.getWatchedMovie(movieId);
    }



    public void insertWant(Want want){
        repositoryMovie.insertWant(want);
    }

    public void deleteWant(int moviesId){ repositoryMovie.deleteWant(moviesId); }

    public void deleteAllWant(){
        repositoryMovie.deleteAllWant();
    }

    public Want getWantMovie(int movieId){
        return  repositoryMovie.getWantMovie(movieId);
    }



    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }

}
