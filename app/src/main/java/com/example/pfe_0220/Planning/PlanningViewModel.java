package com.example.pfe_0220.Planning;

import android.app.Application;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pfe_0220.DatabaseFiles.PlanningDao;
import com.example.pfe_0220.Planning.Models.SchoolClass;
import com.example.pfe_0220.Planning.Models.SchoolClassNode;
import com.example.pfe_0220.Student.Model.AttendenceReportNode;
import com.example.pfe_0220.Student.Model.ModuleReportNode;
import com.example.pfe_0220.Student.Model.Student;
import com.example.pfe_0220.Student.StudentRepository;
import com.example.pfe_0220.Teacher.Model.Teacher;
import com.example.pfe_0220.Teacher.TeacherRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
        school_year_days.postValue(planningRepository.getSchoolYearDays(current_school_day.get(Calendar.YEAR)));

        try {
            planningRepository.getSchoolClasses(current_school_day);

        } catch (Exception e) {
            Toast.makeText(application, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }



    public void PlaneNewSchoolClass(SchoolClass schoolClass, ArrayList<Teacher> responsibles) {
        planningRepository.InsertSchoolClass(schoolClass, responsibles);
    }





}
