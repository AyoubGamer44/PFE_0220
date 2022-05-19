package com.example.pfe_0220.Student;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pfe_0220.DatabaseFiles.ApplicationDatabase;
import com.example.pfe_0220.DatabaseFiles.StudentDao;
import com.example.pfe_0220.Student.Model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    StudentDao studentDao;

    public LiveData<List<Student>> foundStudent;
    public LiveData<List<Student>> allStudent;


    public StudentRepository(Application application) {
        ApplicationDatabase data = ApplicationDatabase.getInstance(application);
        studentDao = data.studentDao();
    }


public void getAllStudent() throws Exception{
allStudent = new GetAllStudent(studentDao).execute().get();
}


public Student getStudentwithId(int id)throws  Exception{
return new GetStudent_BY_ID(studentDao).execute(id).get();
}

    public void getStudentOf(int departement_id, int speciality_id, int section, int level, int group) throws Exception {
        foundStudent = new GetStudentOf(departement_id, speciality_id, level, section, group, studentDao).execute().get();


    }

    public void InsertStudent(Student student) {
        new InsertStudent(studentDao).execute(student);
    }


    static class InsertStudent extends AsyncTask<Student, Void, Void> {

        StudentDao dao;

        public InsertStudent(StudentDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Student... students) {

            try {
                dao.InsertStudent(students[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }


    static class GetStudentOf extends AsyncTask<Void, Void, LiveData<List<Student>>> {
        int departement_id;
        int speciality_id;
        int section;
        int level;
        int group;
        StudentDao dao;

        public GetStudentOf(int departement_id, int speciality_id, int section, int level, int group, StudentDao dao) {
            this.departement_id = departement_id;
            this.speciality_id = speciality_id;
            this.section = section;
            this.level = level;
            this.group = group;
            this.dao = dao;
        }


        @Override
        protected LiveData<List<Student>> doInBackground(Void... voids) {
            return    dao.getStudentof(departement_id, speciality_id, level, section, group);


        }
    }

    static class GetAllStudent extends AsyncTask<Void, Void, LiveData<List<Student>>> {

        StudentDao dao;

        public GetAllStudent(StudentDao dao) {

            this.dao = dao;
        }


        @Override
        protected LiveData<List<Student>> doInBackground(Void... voids) {
            return    dao.getAllStudent();


        }
    }


    static class GetStudent_BY_ID extends AsyncTask<Integer, Void,Student> {

        StudentDao dao;

        public GetStudent_BY_ID(StudentDao dao) {
            this.dao = dao;
        }

        @Override
        protected Student doInBackground(Integer... integers) {
            return    dao.getStudentwithId(integers[0]);
        }
    }

}
