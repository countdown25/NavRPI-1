package com.example.navrpi;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Professor.class}, version = 3)
public abstract class ProfessorDatabase extends RoomDatabase {

    //the actual database class for professors

    public abstract ProfessorDao professorDao();
    //this is the database's data access object, to access the database all you have to do is build it, then call this method

    private static volatile ProfessorDatabase INSTANCE;
    //this is to make sure we don't build the database twice in a single program run, with the code below


    static ProfessorDatabase getDatabase( final Context context) { //method to construct the database, a singleton class
        if (INSTANCE == null) {
            synchronized (ProfessorDatabase.class) { //this stuff checks to make sure we only ever make one
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ProfessorDatabase.class, "professor_database").fallbackToDestructiveMigration().allowMainThreadQueries().addCallback(sRoomDatabaseCallback).build();
                    //this obnoxious line builds the database, with the following options:
                    //destructive migration - we do not allow users to enter data, so we do not have to worry about migrating it from schema update to schema update
                    //main thread queries allowed - to allow access throughout the program
                    //added callback (below) - for initializing database with data
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (SupportSQLiteDatabase db){ //used to initialize, passed as argument to builder statement
                    super.onOpen(db);
                    new ProfessorAsyncPopulate(INSTANCE).execute(); //executes the doInBackground() method in the class, which adds the data
                }
            };
}

