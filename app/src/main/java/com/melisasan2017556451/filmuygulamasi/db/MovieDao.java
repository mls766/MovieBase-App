package com.melisasan2017556451.filmuygulamasi.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.melisasan2017556451.filmuygulamasi.models.Movies;

import java.util.List;

//The following code defines a DAO called MovieDao.
// MovieDao provides the methods that the rest of the app uses to interact with data in the favorite table.

@Dao
public interface MovieDao {

    //Methods that insert Movies to favorite list, delete Movies from the list, and get all Movies from the list.
    @Insert
    void insertMovie(Favorite favorite);

    @Query("DELETE FROM favorite_table WHERE id = :moviesId")
    void deleteMovie(int moviesId);

    @Query("DELETE FROM favorite_table")
    void deleteAll();

    @Query("SELECT * FROM favorite_table")
    LiveData<List<Favorite>> getFavoriteList();

    @Query("SELECT * FROM favorite_table WHERE id = :moviesId ")
    Favorite getFavoriteMovie(int moviesId);

}


