package com.example.pfe_0220.Planning;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.pfe_0220.DatabaseFiles.ApplicationDatabase;
import com.example.pfe_0220.DatabaseFiles.PlanningDao;
import com.example.pfe_0220.Planning.Models.Attendence;
import com.example.pfe_0220.Planning.Models.SchoolClass;
import com.example.pfe_0220.Planning.Models.SchoolClassNode;
import com.example.pfe_0220.Student.Model.Student;
import com.example.pfe_0220.Teacher.Model.Teacher;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PlanningRepository {
    public int schoolYearStartMonth = 10, schoolYearEndMonth = 9;
    PlanningDao planningDao;


    public LiveData<List<SchoolClassNode>> plannedSchoolClasses;


    public PlanningRepository(Application application) {
        ApplicationDatabase database = ApplicationDatabase.getInstance(application);
        this.planningDao = database.planningDao();


    }


    public void InsertSchoolClass(SchoolClass schoolClass, ArrayList<Teacher> responsibles) {
        new InsertSchoolClass(planningDao, responsibles,schoolClass).execute();
    }

    public void getSchoolClasses(Calendar day) throws Exception {
        plannedSchoolClasses = new GetAllClassesAsync(planningDao).execute().get();
    }

    public ArrayList<Calendar> getSchoolYearDays(int year) {
        ArrayList<Calendar> days = new ArrayList<Calendar>();

        int prev_year = year - 1;

        Calendar currentDay = Calendar.getInstance();
        Calendar lastDay = Calendar.getInstance();

        lastDay.set(year, schoolYearEndMonth, 30);
        currentDay.set(prev_year, schoolYearStartMonth, 1);

        while (currentDay.getTimeInMillis() < lastDay.getTimeInMillis()) {
            Calendar day = Calendar.getInstance();
            day.setTime(currentDay.getTime());
            days.add(day);
            currentDay.add(Calendar.DATE, 1);

        }

        for (Calendar day : days
        ) {
            Log.i("TAG", "getSchoolYearDays: " + day.get(Calendar.DAY_OF_MONTH) + " " + day.get(Calendar.MONTH) + "\n");
        }
        return days;
    }


    static class GetAllClassesAsync extends AsyncTask<Void, Void, LiveData<List<SchoolClassNode>>> {
        PlanningDao dao;

        public GetAllClassesAsync(PlanningDao dao) {
            this.dao = dao;
        }

        @Override
        protected LiveData<List<SchoolClassNode>> doInBackground(Void... voids) {
            return dao.getClassesOf();

        }
    }


    static class InsertSchoolClass extends AsyncTask<Void, Void, Void> {
        PlanningDao dao;
        ArrayList<Teacher> responsibles;
        SchoolClass schoolClass;


        public InsertSchoolClass(PlanningDao dao, ArrayList<Teacher> responsibles, SchoolClass schoolClass) {
            this.dao = dao;
            this.responsibles = responsibles;
            this.schoolClass = schoolClass;

        }

        @Override
        protected Void doInBackground(Void... schoolClasses) {
            dao.InsertSchoolClass(schoolClass);
            int lastInsertedSchoolClassId = dao.getLastInsertedIdfrom();
            ArrayList<Attendence> attendences = new ArrayList<>();


            for (Teacher t : responsibles
            ) {
                Attendence attendence = new Attendence();
                attendence.schoolClass_id = lastInsertedSchoolClassId;
                attendence.student_id = t.id+"";
                attendence.presenceType = Attendence.TEACHER;
                attendence.state = Attendence.PENDING;
                attendence.enterTime = Calendar.getInstance();
                attendence.module_id = schoolClass.module_id;
                attendences.add(attendence);
            }


            for (Student s: schoolClass.students_of_class
                 ) {
                Attendence attendence = new Attendence();
                attendence.schoolClass_id = lastInsertedSchoolClassId;
                attendence.student_id = s.id;
                attendence.presenceType = Attendence.STUDENT;
                attendence.state = Attendence.PENDING;
                attendence.module_id = schoolClass.module_id;
                attendence.enterTime = Calendar.getInstance();
                attendences.add(attendence);
            }

            for (Attendence a:
                    attendences) {
                dao.InsertAttendence(a);
            }
            return null;
        }
    }


}
