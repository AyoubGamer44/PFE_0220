package com.example.pfe_0220.Student;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pfe_0220.Student.Model.Student;

import java.util.ArrayList;

public class StudentRepository {
    MutableLiveData<ArrayList<Student>> selectedStudents = new MutableLiveData<ArrayList<Student>>();


    public void getStudentOf(int departement_id, int speciality_id, int section, int level, String group){


         ArrayList<Student> students = new ArrayList<Student>();
         students.add(new Student("kridi","ibrahim"));
         students.add(new Student("kridi","ibrahim"));
         students.add(new Student("kridi","ibrahim"));
         students.add(new Student("kridi","ibrahim"));
         students.add(new Student("kridi","ibrahim"));
         students.add(new Student("kridi","ibrahim"));
         students.add(new Student("kridi","ibrahim"));
         students.add(new Student("kridi","ibrahim"));
        selectedStudents.postValue(students);

    }

}
