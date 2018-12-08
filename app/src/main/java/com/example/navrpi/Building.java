package com.example.navrpi;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

//full commentary for all database classes can be found in the Professor database classes (ProfessorDatabase, ProfessorDao, Professor, and ProfessorAsyncPopulate)
@Entity
public class Building {

    //simple class to hold buildings, that is their name and their location for the google maps marker

    @NonNull
    @PrimaryKey
    private String name;


    private double lat;
    private double lng;

    public Building() {};

    public Building(String name, double lat, double lng) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;

    }

    public String getName() { return name; }

    public void setName(String n) {
        this.name = n;
    }

    public LatLng coordinate1() {
        return new LatLng(lat,lng);
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLng(double lng) {
        this.lng = lng;

    }
}
