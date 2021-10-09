package com.chrizlove.spacexcrewapi;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CrewRepository {

        private CrewDAO crewDAO;
        private LiveData<List<CrewModel>> allCrew;

        // Note that in order to unit test the WordRepository, you have to remove the Application
        // dependency. This adds complexity and much more code, and this sample is not about testing.
        // See the BasicSample in the android-architecture-components repository at
        // https://github.com/googlesamples

        CrewRepository (Application application) {
            CrewDataBase db = CrewDataBase.getDatabase(application);
            crewDAO = db.crewDAO();
            allCrew = crewDAO.getCrew();
        }

        // Room executes all queries on a separate thread.
        // Observed LiveData will notify the observer when the data has changed.
        LiveData<List<CrewModel>> getAllCrew() {
            return allCrew;
        }

        // You must call this on a non-UI thread or your app will throw an exception. Room ensures
        // that you're not doing any long running operations on the main thread, blocking the UI.
        void insert(CrewModel crew) {
            CrewDataBase.databaseWriteExecutor.execute(() -> {
                crewDAO.insert(crew);
            });
        }

        void delete() {
        CrewDataBase.databaseWriteExecutor.execute(() -> {
            crewDAO.deleteAll();
        });
        }
    }

