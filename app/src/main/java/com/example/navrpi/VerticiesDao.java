package com.example.navrpi;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


import java.util.List;

//full commentary for all database classes can be found in the Professor database classes (ProfessorDatabase, ProfessorDao, Professor, and ProfessorAsyncPopulate)
@Dao
public interface VerticiesDao {

    //Data access object for edge/vertex database. Provides wrappers for standard SQL queries.
    //the top line defines the SQL, the bottom line the associated java function

    @Insert
    void insert(Verticies v);

    @Query("SELECT * FROM verticies")
    List<Verticies> getAllEdges();

    @Query("DELETE FROM verticies")
    void DeleteAll();

    @Query("SELECT * FROM verticies WHERE source = :s")
    List<Verticies> getEdgesBySource(String s);

    @Query("SELECT * FROM verticies WHERE dest = :d")
    List<Verticies> getEdgesByDest(String d);
}
