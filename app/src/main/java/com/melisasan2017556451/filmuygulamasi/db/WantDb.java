package com.melisasan2017556451.filmuygulamasi.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.melisasan2017556451.filmuygulamasi.models.Movies;

// abstract database class WantDb for storing our want movies

//The class annotated with a @Database annotation that includes an entities array that lists all of the data entities associated with the database.
@Database(entities = {Want.class},version = 5,exportSchema = false)
// abstract method of WantDao return type
public abstract class WantDb extends RoomDatabase{
    public abstract WantDao wantDao();
}

