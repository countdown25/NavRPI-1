package com.example.navrpi;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Professor {

    //simple class to old data about professors, including name and node (room)
    //each member variable has a getter and setter function per Room's guidelines
    //however, we only effectively use the getters, our data does not need to change
    //after it is inserted under normal operation

    private String firstName;

    private String lastName;

    private String node;

    @NonNull
    @PrimaryKey
    private String id;
    //the id is made from a combination of first and last name,
    //id must be unique for Room

    public Professor(String firstName, String lastName, String node) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.node = node;
        this.id = this.firstName + this.lastName;
    }

    public String getFirstName() {return firstName;}

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {return lastName;}

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getNode() {
        return node;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }
}


