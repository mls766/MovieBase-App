package com.melisasan2017556451.filmuygulamasi.viewmodels;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.melisasan2017556451.filmuygulamasi.db.Favorite;
import com.melisasan2017556451.filmuygulamasi.repositories.RepositoryMovie;

import java.util.List;

public class FavoriteViewModel extends ViewModel {
    private RepositoryMovie repository;
    private LiveData<List<Favorite>> favoriteMoviesList;

    @ViewModelInject
    public FavoriteViewModel(RepositoryMovie repository) {
        this.repository = repository;
        favoriteMoviesList = repository.getFavoriteList();
    }

    public LiveData<List<Favorite>> getFavoriteListMovieList() {
        return favoriteMoviesList;
    }

    public void deleteAll(){
        repository.deleteAll();
    }

}
