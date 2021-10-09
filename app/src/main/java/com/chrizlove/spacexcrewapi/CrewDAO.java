package com.chrizlove.spacexcrewapi;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CrewDAO {

        // allowing the insert of the same word multiple times by passing a
        // conflict resolution strategy

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        void insert(CrewModel crew);

        @Query("DELETE FROM crew_table")
        void deleteAll();

        @Query("SELECT * FROM crew_table")
        LiveData<List<CrewModel>> getCrew();
}
