package com.chrizlove.spacexcrewapi;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {CrewModel.class}, version = 1, exportSchema = false)
public abstract class CrewDataBase extends RoomDatabase {

        public abstract CrewDAO crewDAO();
        private static volatile CrewDataBase INSTANCE;
        private static final int NUMBER_OF_THREADS = 4;
        static final ExecutorService databaseWriteExecutor =
                Executors.newFixedThreadPool(NUMBER_OF_THREADS);

        static CrewDataBase getDatabase(final Context context) {
            if (INSTANCE == null) {
                synchronized (CrewDataBase.class) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                CrewDataBase.class, "crew_database")
                                .build();
                    }
                }
            }
            return INSTANCE;
        }
    }

