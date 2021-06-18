package com.melisasan2017556451.filmuygulamasi.request;

import android.app.Application;

import androidx.room.Room;

//import com.melisasan2017556451.filmuygulamasi.db.MovieDao;
//import com.melisasan2017556451.filmuygulamasi.db.MoviesDb;
import com.melisasan2017556451.filmuygulamasi.db.MovieDao;
import com.melisasan2017556451.filmuygulamasi.db.MoviesDb;
import com.melisasan2017556451.filmuygulamasi.db.WantDao;
import com.melisasan2017556451.filmuygulamasi.db.WantDb;
import com.melisasan2017556451.filmuygulamasi.db.WatchedDao;
import com.melisasan2017556451.filmuygulamasi.db.WatchedDb;
import com.melisasan2017556451.filmuygulamasi.models.Movies;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

// DatabaseModule will help hilt to provide  with the instance of MoviesDb, WatchedDb and WantDb databases.

//@Module and @InstallIn annotation tell hilt that this is a module and its required for application cope
@Module
@InstallIn(ApplicationComponent.class)
public class DatabaseModule {


    @Provides
    //@Singleton is used to have a single instance of this database over the whole app.
    @Singleton
    public static MoviesDb provideMoviesDb(Application application){
        return Room.databaseBuilder(application, MoviesDb.class,"FavoriteDB")
                .fallbackToDestructiveMigration().allowMainThreadQueries().build();
    }

    @Provides
    @Singleton
    //@Singleton is used to have a single instance of this database over the whole app.
    public static WatchedDb provideWatchedDb(Application application){
        return Room.databaseBuilder(application, WatchedDb.class,"WatchedDB")
                .fallbackToDestructiveMigration().allowMainThreadQueries().build();
    }


    @Provides
    @Singleton
    public static WantDb provideWantDb(Application application){
        return Room.databaseBuilder(application, WantDb.class,"WantDB")
                .fallbackToDestructiveMigration().allowMainThreadQueries().build();
    }

    @Provides
    @Singleton
    public static MovieDao provideMovieDao(MoviesDb moviesDb){
        return moviesDb.movieDao();
    }

    @Provides
    @Singleton
    public static WatchedDao provideWatchedDao(WatchedDb watchedDb){
        return watchedDb.watchedDao();
    }

    @Provides
    @Singleton
    public static WantDao provideWantDao(WantDb wantDb){
        return wantDb.wantDao();
    }



}


