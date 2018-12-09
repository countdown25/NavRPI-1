package com.example.navrpi;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

//full commentary for all database classes can be found in the Professor database classes (ProfessorDatabase, ProfessorDao, Professor, and ProfessorAsyncPopulate)
@Dao
public interface ClassroomDao {

    //Data access object for classroom database. Provides wrappers for standard SQL queries.
    //the top line defines the SQL, the bottom line the associated java function

    @Query("SELECT * FROM classroom WHERE number LIKE :search")
    List<Classroom> searchNumber(String search);

    @Query("SELECT * FROM classroom WHERE building LIKE :search")
    List<Classroom> searchBuilding(String search);

    @Insert
    void insert(Classroom rm);

    @Query("SELECT * FROM classroom")
    List<Classroom> getAllRooms();

    @Query("DELETE from classroom")
    void deleteAll();
}
