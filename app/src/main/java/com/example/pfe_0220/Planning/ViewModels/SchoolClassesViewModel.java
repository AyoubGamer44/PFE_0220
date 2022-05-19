package com.example.pfe_0220.Planning.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.pfe_0220.Planning.Models.Attendence;
import com.example.pfe_0220.Planning.PlanningRepository;
import com.example.pfe_0220.Planning.Repositories.SchoolClassRepository;
import com.example.pfe_0220.Student.StudentRepository;
import com.example.pfe_0220.Teacher.TeacherRepository;

public class SchoolClassesViewModel extends AndroidViewModel {


   public SchoolClassRepository schoolClassRepository;



    public SchoolClassesViewModel(@NonNull Application application) {
        super(application);
        schoolClassRepository = new SchoolClassRepository(application);



    }

    public void GetResponsibleOf(int schoolClass_id) throws Exception {
        schoolClassRepository.getResponsibleOfClass(schoolClass_id);
    }
    public void GetStudentOf(int school_class_id) throws Exception {
        schoolClassRepository.getStudentOfClass(school_class_id);
    }

    public void InsertAttendenceOf(Attendence attendence){
        schoolClassRepository.InsertAttendenceFor(attendence);
    }

public Attendence getAttendenceOf(int id) throws Exception{
        return schoolClassRepository.GetAttendenceOf(id);
}
    public void UpdateStudentAttendence(Attendence attendence){
        schoolClassRepository.UpdateStudentAttendence(attendence);
    }

}
