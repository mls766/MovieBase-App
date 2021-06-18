package com.melisasan2017556451.filmuygulamasi.models;

import java.io.Serializable;

public class Directors implements Serializable {
    private int director_id;
    private String director_name;

    public Directors() {
    }

    public Directors(int director_id, String director_name) {
        this.director_id = director_id;
        this.director_name = director_name;
    }

    public int getDirector_id() {
        return director_id;
    }

    public void setDirector_id(int director_id) {
        this.director_id = director_id;
    }

    public String getDirector_name() {
        return director_name;
    }

    public void setDirector_name(String director_name) {
        this.director_name = director_name;
    }
}
