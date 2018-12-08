package com.example.navrpi;

import android.os.AsyncTask;

public class ProfessorAsyncPopulate extends AsyncTask<Void, Void, Void> {

    //this class is used in the ProfessorDatabase's callback to fill the database with our data

    private final ProfessorDao professorDao; //used to do the insertion

    ProfessorAsyncPopulate(ProfessorDatabase db) {
        professorDao = db.professorDao();
    }

    @Override
    protected Void doInBackground(final Void... params){ //the actual method that runs
        professorDao.deleteAll(); //clear anything that's somehow already there (Room can crash if we try inserting the same thing twice)
        professorDao.insert(new Professor("Michael", "Aldersley", "Walker3950550"));
        professorDao.insert(new Professor("Alex", "Ma", "Walker3450200"));
        return null;
    }
}
