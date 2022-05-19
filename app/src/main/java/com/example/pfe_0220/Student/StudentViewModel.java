package com.example.pfe_0220.Student;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.pfe_0220.Departement.DepartementRepository;
import com.example.pfe_0220.Student.Model.Student;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class StudentViewModel extends AndroidViewModel {
    DepartementRepository departementRepo;
  public  StudentRepository studentRepository;

   public Student newStudent;

public int selectedStudent_id;





    public StudentViewModel(@NonNull Application application) {
        super(application);
        departementRepo = new DepartementRepository(application);
        studentRepository = new StudentRepository(application);
    }



    public void getStudentof(int departement_id, int speciality_id, int section, int level, int group) throws Exception {
         studentRepository.getStudentOf( departement_id,speciality_id,level,section,group);
    }




    public  void InsertStudent(Student student){
        studentRepository.InsertStudent(student);
    }

public void getReportOfStudent()throws Exception{
        studentRepository.getReportsof(selectedStudent_id);
}
}
