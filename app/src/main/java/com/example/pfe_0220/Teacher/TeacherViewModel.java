package com.example.pfe_0220.Teacher;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.pfe_0220.Teacher.Model.Teacher;

import java.util.concurrent.ExecutionException;

public class TeacherViewModel extends AndroidViewModel {

    TeacherRepository teacherRepository;

    public TeacherViewModel(@NonNull Application application) {
        super(application);
        teacherRepository = new TeacherRepository(application);
        try {
            teacherRepository.getAllTeacher();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public void InsertTeacher(Teacher teacher) throws Exception {
        teacherRepository.InsertTeacher(teacher);
    }

}
