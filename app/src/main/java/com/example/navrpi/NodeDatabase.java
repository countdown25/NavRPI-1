package com.example.navrpi;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

//full commentary for all database classes can be found in the Professor database classes (ProfessorDatabase, ProfessorDao, Professor, and ProfessorAsyncPopulate)
@Database(entities = {MapNode.class}, version = 3)
public abstract class NodeDatabase extends RoomDatabase {
    public abstract NodeDao nodeDao();

    private static volatile NodeDatabase INSTANCE;

    static NodeDatabase getDatabase( final Context context) { //method to construct the database, a singleton class
        if (INSTANCE == null) {
            synchronized (NodeDatabase.class) { //this stuff checks to make sure we only ever make one
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), NodeDatabase.class, "node_database").fallbackToDestructiveMigration().allowMainThreadQueries().addCallback(sRoomDatabaseCallback).build();
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
                public void onOpen (SupportSQLiteDatabase db){
                    super.onOpen(db); //used to initialize, passed as argument to builder statement
                    new NodeAsyncPopulate(INSTANCE).execute();
                }
            };
}

