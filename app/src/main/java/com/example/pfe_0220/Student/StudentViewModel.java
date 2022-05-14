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
    StudentRepository studentRepository;

    public int departement_id;
    public int speciality_id;
    public int level;
    public int section;
    public String group = "A";



    public StudentViewModel(@NonNull Application application) {
        super(application);
        departementRepo = new DepartementRepository(application);
        studentRepository = new StudentRepository();
    }

    public void  getDepartementList() throws Exception {
        departementRepo.getDepartement();
    }

    public void getStudentof(int departement_id, int speciality_id, int section, int level, String group) {

//        selectedStudents .postValue(studentRepository.getStudentOf(departement_id, speciality_id, section, level, group));
         studentRepository.getStudentOf( 1,  1,  2,  1,  "group");
    }

    public void UpdateSelectionFilter(int _departement_id,
                                      int _speciality_id,
                                      int _level,
                                      int _section,
                                      String _group) {
        departement_id = _departement_id;
        speciality_id = _speciality_id;
        level = _level;
        section = _section;
        group = _group;
    }

}
