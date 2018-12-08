package com.example.navrpi;

import android.os.AsyncTask;

//full commentary for all database classes can be found in the Professor database classes (ProfessorDatabase, ProfessorDao, Professor, and ProfessorAsyncPopulate)
public class ClassroomAsyncPopulate extends AsyncTask<Void, Void, Void> {

    private final ClassroomDao cDao;

    ClassroomAsyncPopulate(ClassroomDatabase db) {
        cDao = db.classroomDao();
    }

    @Override
    protected Void doInBackground(final Void... params){
        cDao.deleteAll(); //clear anything that's somehow already there (Room can crash if we try inserting the same thing twice)
        cDao.insert(new Classroom("3113","Walker","Walker31000650"));
        cDao.insert(new Classroom("3222","walker","Walker3950650"));
        return null;
    }
}
