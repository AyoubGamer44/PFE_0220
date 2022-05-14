package com.example.pfe_0220.Teacher;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.pfe_0220.DatabaseFiles.ApplicationDatabase;
import com.example.pfe_0220.DatabaseFiles.DepartementDao;
import com.example.pfe_0220.DatabaseFiles.TeacherDao;
import com.example.pfe_0220.Teacher.Model.Teacher;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class TeacherRepository {

    public LiveData<List<Teacher>> teachers;

    private TeacherDao dao;

    public TeacherRepository(Application application) {
        ApplicationDatabase database = ApplicationDatabase.getInstance(application);
        dao = database.teacherDao();

    }

    public void InsertTeacher(Teacher teacher) throws Exception {
        new InsertTeacher(dao).execute(teacher).get();
    }

    public void getAllTeacher() throws Exception {
        teachers = new GetTeachers(dao).execute().get();

    }

    static class GetTeachers extends AsyncTask<Void,Void,LiveData<List<Teacher>>>{

        TeacherDao dao;

        public GetTeachers(TeacherDao dao) {
            this.dao = dao;
        }


        @Override
        protected LiveData<List<Teacher>> doInBackground(Void... voids) {

            return dao.getTeachers();
        }
    }
    static class InsertTeacher extends AsyncTask<Teacher,Void,Boolean>{

        TeacherDao dao;

        public InsertTeacher(TeacherDao dao) {
            this.dao = dao;
        }

        @Override
        protected Boolean doInBackground(Teacher... teachers) {
            try {
                dao.InsertTeacher(teachers[0]);
                return true;
            }
            catch(Exception e)
            {
                return false;
            }

        }
    }

}


