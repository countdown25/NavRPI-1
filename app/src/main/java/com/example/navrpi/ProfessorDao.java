package com.example.navrpi;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ProfessorDao {

    //Data access object for professor database. Provides wrappers for standard SQL queries.
    //the top line defines the SQL, the bottom line the associated java function

    @Query("SELECT * FROM professor WHERE lastName LIKE :searchName")
    List<Professor> searchLastName(String searchName);

    @Insert
    void insert(Professor prof);

    @Query("SELECT * FROM professor")
    List<Professor> getAllProfessors();

    @Query("DELETE from professor")
    void deleteAll();

}

