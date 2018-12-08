package com.example.navrpi;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
//full commentary for all database classes can be found in the Professor database classes (ProfessorDatabase, ProfessorDao, Professor, and ProfessorAsyncPopulate)
public class Classroom {

    //represents classrooms, with number building and location w/ a node

    @NonNull
    @PrimaryKey
    private String id;

    private String number;

    private String building;

    private String node;

    public Classroom(String number, String building, String node) {
        this.number = number;
        this.building = building;
        this.node = node;
        this.id = building + number;
    }

    public String getNumber() { return number; }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getBuilding() { return building; }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    @NonNull
    public String getId() {
        return id;
    }
}
