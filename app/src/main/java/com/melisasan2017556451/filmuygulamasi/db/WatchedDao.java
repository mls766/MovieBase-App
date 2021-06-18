package com.melisasan2017556451.filmuygulamasi.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.melisasan2017556451.filmuygulamasi.models.Movies;

import java.util.List;


//The following code defines a DAO called WatchedDao.
// WatchedDao provides the methods that the rest of the app uses to interact with data in the watched table.


@Dao
public interface WatchedDao {

    ////Methods that insert Watched(movies) to watched list, delete Watched from the list, and get all Watched from the list.
    @Insert
    void insertWatched(Watched watched);

    @Query("DELETE FROM watched_table WHERE id = :moviesId")
    void deleteWatched(int moviesId);

    @Query("DELETE FROM watched_table")
    void deleteAllWatched();

    @Query("SELECT * FROM watched_table")
    LiveData<List<Watched>> getWatchedList();

    @Query("SELECT * FROM watched_table WHERE id = :moviesId ")
    Watched getWatchedMovie(int moviesId);

}


