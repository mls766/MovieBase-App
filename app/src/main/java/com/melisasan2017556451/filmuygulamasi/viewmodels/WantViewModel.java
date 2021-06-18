package com.melisasan2017556451.filmuygulamasi.viewmodels;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.melisasan2017556451.filmuygulamasi.db.Favorite;
import com.melisasan2017556451.filmuygulamasi.db.Want;
import com.melisasan2017556451.filmuygulamasi.db.Watched;
import com.melisasan2017556451.filmuygulamasi.repositories.RepositoryMovie;

import java.util.List;

public class WantViewModel extends ViewModel {

    private RepositoryMovie repository;
    private LiveData<List<Want>> wantMoviesList;

    @ViewModelInject
    public WantViewModel(RepositoryMovie repository) {
        this.repository = repository;
        wantMoviesList = repository.getWantList();
    }

    public LiveData<List<Want>> getWantListMovieList() {
        return wantMoviesList;
    }

    public void deleteAllWant(){
        repository.deleteAllWant();
    }

}
