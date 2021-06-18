package com.melisasan2017556451.filmuygulamasi.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.melisasan2017556451.filmuygulamasi.models.Genres;

import java.util.ArrayList;

import javax.annotation.Nonnull;

//For room setup we need Entity.The following code defines a Watched data entity.
// Each instance of Watched represents a row in a watched table in the app's database.

@Entity(tableName = "watched_table")
public class Watched {

    @PrimaryKey(autoGenerate = true)
    @Nonnull
    @ColumnInfo(name = "Id")
    public int id;
    private String poster_path,overview,release_date,title;
    private float vote_average;
    private int runtime;

    public Watched(int id, String poster_path, String overview, String release_date, String title, float vote_average, int runtime) {
        this.id = id;
        this.poster_path = poster_path;
        this.overview = overview;
        this.release_date = release_date;
        this.title = title;
        this.vote_average = vote_average;
        this.runtime = runtime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }
}