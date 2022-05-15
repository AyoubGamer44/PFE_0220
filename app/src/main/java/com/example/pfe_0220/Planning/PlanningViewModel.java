package com.example.pfe_0220.Planning;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Calendar;

public class PlanningViewModel extends AndroidViewModel {

    private PlanningRepository repository = new PlanningRepository();

    MutableLiveData<ArrayList<Calendar>> school_year_days = new MutableLiveData<ArrayList<Calendar>>();
    public ArrayList<SchoolClass> classes = new ArrayList<SchoolClass>();
    public Calendar current_school_day;

    public PlanningViewModel(@NonNull Application application) {
        super(application);
        current_school_day = Calendar.getInstance();
        school_year_days.postValue(repository.getSchoolYearDays(current_school_day.get(Calendar.YEAR)));

    }


}
