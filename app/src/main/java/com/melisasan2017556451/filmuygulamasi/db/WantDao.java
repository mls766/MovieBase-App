package com.melisasan2017556451.filmuygulamasi.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.melisasan2017556451.filmuygulamasi.models.Movies;

import java.util.List;

//The following code defines a DAO called WantDao.
// WantDao provides the methods that the rest of the app uses to interact with data in the want table.

@Dao
public interface WantDao {

    //Methods that insert Want(movies) to want list, delete Want from the list, and get all Want from the list.
    @Insert
    void insertWatched(Want want);

    @Query("DELETE FROM want_table WHERE id = :moviesId")
    void deleteWant(int moviesId);

    @Query("DELETE FROM want_table")
    void deleteAllWant();

    @Query("SELECT * FROM want_table")
    LiveData<List<Want>> getWantList();

    @Query("SELECT * FROM want_table WHERE id = :moviesId ")
    Want getWantMovie(int moviesId);

}


