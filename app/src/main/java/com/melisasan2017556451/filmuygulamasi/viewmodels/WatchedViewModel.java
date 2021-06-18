package com.melisasan2017556451.filmuygulamasi.viewmodels;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.melisasan2017556451.filmuygulamasi.db.Favorite;
import com.melisasan2017556451.filmuygulamasi.db.Watched;
import com.melisasan2017556451.filmuygulamasi.repositories.RepositoryMovie;

import java.util.List;

public class WatchedViewModel extends ViewModel {
    private RepositoryMovie repository;
    private LiveData<List<Watched>> watchedMoviesList;

    @ViewModelInject
    public WatchedViewModel(RepositoryMovie repository) {
        this.repository = repository;
        watchedMoviesList = repository.getWatchedList();
    }

    public LiveData<List<Watched>> getWatchedListMovieList() {
        return watchedMoviesList;
    }

    public void deleteAllWatched(){
        repository.deleteAllWatched();
    }

}
