package com.example.navrpi;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

//full commentary for all database classes can be found in the Professor database classes (ProfessorDatabase, ProfessorDao, Professor, and ProfessorAsyncPopulate)
@Entity
public class Verticies {

    //class for representing the edges between nodes

    private String source;
    private String dest;

    @PrimaryKey
    @NonNull
    private String id;

    int distance;

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public Verticies() {
        this.dest = "";
        this.source = "";
        this.distance = 0;
        this.id = this.dest + this.source + Integer.toString(this.distance);
    }

    public Verticies( String s, String d, int dist) {
        this.dest = d;
        this.source = s;
        this.distance = dist;
        this.id = this.dest + this.source + Integer.toString(this.distance);
    }

    public String getDest() {
        return dest;
    }

    public String getSource() {
        return source;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
