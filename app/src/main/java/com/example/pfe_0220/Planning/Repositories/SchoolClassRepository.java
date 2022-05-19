package com.example.pfe_0220.Planning.Repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.pfe_0220.DatabaseFiles.ApplicationDatabase;
import com.example.pfe_0220.DatabaseFiles.PlanningDao;
import com.example.pfe_0220.DatabaseFiles.StudentDao;
import com.example.pfe_0220.Planning.Models.Attendence;
import com.example.pfe_0220.Planning.Models.AttendenceNode;

import java.util.List;

public class SchoolClassRepository {
    PlanningDao planningDao;
    StudentDao studentDao;
    public LiveData<List<AttendenceNode>> responsibleAttendence;
    public LiveData<List<AttendenceNode>> studentsAttendence;

    public SchoolClassRepository(Application application) {
        ApplicationDatabase source = ApplicationDatabase.getInstance(application);
        planningDao = source.planningDao();
        studentDao = source.studentDao();
    }

    public void getResponsibleOfClass(int schoolClassId) throws Exception {
        responsibleAttendence = new getAttendences(planningDao, Attendence.TEACHER, schoolClassId).execute().get();
    }

    public void InsertAttendenceFor(Attendence attendence) {
        new InsertAttendence(planningDao).execute(attendence);
    }

    public void getStudentOfClass(int school_class_id) throws Exception {

        studentsAttendence = new getAttendences(planningDao, Attendence.STUDENT, school_class_id).execute().get();
    }

    public void UpdateStudentAttendence(Attendence attendence) {
        new UpsateStudentAttendence(planningDao).execute(attendence);
    }


    public Attendence GetAttendenceOf(int student_id) throws Exception {
        return new getAttendenceOfId(planningDao).execute(student_id).get();
    }

    static class getAttendences extends AsyncTask<Void, Void, LiveData<List<AttendenceNode>>> {

        PlanningDao source;
        int who;
        int of;

        public getAttendences(PlanningDao source, int who, int of) {
            this.source = source;
            this.who = who;
            this.of = of;
        }


        @Override
        protected LiveData<List<AttendenceNode>> doInBackground(Void... voids) {


            if (who == Attendence.TEACHER) {
                return source.getResponsiblesAttendenceOf(of, who);
            } else {
                return source.getStudentAttendenceOf(of, who);
            }
        }
    }


    static class getAttendenceOfId extends AsyncTask<Integer, Void, Attendence> {

        PlanningDao source;

        public getAttendenceOfId(PlanningDao source) {
            this.source = source;
        }

        @Override
        protected Attendence doInBackground(Integer... integers) {
            return source.getAttendenceOf(integers[0]);
        }
    }

    static class InsertAttendence extends AsyncTask<Attendence, Void, Void> {
        PlanningDao dao;

        public InsertAttendence(PlanningDao dao) {
            this.dao = dao;
        }


        @Override
        protected Void doInBackground(Attendence... attendences) {
            dao.InsertAttendence(attendences[0]);
            return null;
        }
    }


    static class UpsateStudentAttendence extends AsyncTask<Attendence, Void, Void> {
        PlanningDao dao;

        public UpsateStudentAttendence(PlanningDao dao) {
            this.dao = dao;
        }


        @Override
        protected Void doInBackground(Attendence... attendences) {
            dao.UpdateAttendence(attendences[0]);
            return null;
        }
    }
}
