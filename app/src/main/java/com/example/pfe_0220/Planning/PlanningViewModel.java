package com.example.pfe_0220.Planning;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.pfe_0220.Planning.Models.SchoolClass;
import com.example.pfe_0220.Planning.Models.SchoolClassNode;
import com.example.pfe_0220.Student.StudentRepository;
import com.example.pfe_0220.Teacher.Model.Teacher;
import com.example.pfe_0220.Teacher.TeacherRepository;

import java.util.ArrayList;
import java.util.Calendar;

public class PlanningViewModel extends AndroidViewModel {

    public PlanningRepository planningRepository;
    public TeacherRepository teacherRepository;
    public StudentRepository studentRepository;


    public SchoolClass newSchoolClass;
    public SchoolClassNode selected_school_class;

    MutableLiveData<ArrayList<Calendar>> school_year_days = new MutableLiveData<ArrayList<Calendar>>();


    public Calendar current_school_day;

    public PlanningViewModel(@NonNull Application application) {
        super(application);
        planningRepository = new PlanningRepository(application);
        teacherRepository = new TeacherRepository(application);
        studentRepository = new StudentRepository(application);


        current_school_day = Calendar.getInstance();
        JumpToDate(current_school_day);

        try {
            planningRepository.getSchoolClasses(current_school_day);

        } catch (Exception e) {
            Toast.makeText(application, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

    public void JumpToDate(Calendar selectedDate) {
        school_year_days.postValue(planningRepository.getSchoolYearDays(selectedDate.get(Calendar.YEAR)));
    }

    public void PlaneNewSchoolClass(SchoolClass schoolClass, ArrayList<Teacher> responsibles) {
        planningRepository.InsertSchoolClass(schoolClass, responsibles);
    }


}
