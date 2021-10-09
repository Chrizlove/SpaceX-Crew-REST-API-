package com.chrizlove.spacexcrewapi;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CrewViewModel extends AndroidViewModel {

        private CrewRepository crewRepository;

        private final LiveData<List<CrewModel>> allCrew;

        public CrewViewModel (Application application) {
            super(application);
            crewRepository = new CrewRepository(application);
            allCrew = crewRepository.getAllCrew();
        }

        LiveData<List<CrewModel>> getAllCrew() {
            return allCrew;
        }

        public void insert(CrewModel crew) {
            crewRepository.insert(crew);
        }

        public void delete() {
        crewRepository.delete();
        }

}
