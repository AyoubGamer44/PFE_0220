package com.example.pfe_0220.Planning;

import android.util.Log;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

public class PlanningRepository {
    public int schoolYearStartMonth = 10,schoolYearEndMonth = 9;


    public ArrayList<Calendar> getSchoolYearDays(int year){
        ArrayList<Calendar> days= new ArrayList<Calendar>();

        int prev_year = year - 1;

        Calendar currentDay =Calendar.getInstance();
        Calendar lastDay = Calendar.getInstance();

        lastDay.set(year,schoolYearEndMonth,30);
        currentDay.set(prev_year,schoolYearStartMonth,1);

        while(currentDay.getTimeInMillis() <  lastDay.getTimeInMillis()){
            Calendar day = Calendar.getInstance();
            day.setTime(currentDay.getTime());
            days.add(day);
            currentDay.add(Calendar.DATE,1);

        }

        for (Calendar day: days
             ) {
            Log.i("TAG", "getSchoolYearDays: "+ day.get(Calendar.DAY_OF_MONTH)+" "+ day.get(Calendar.MONTH)+"\n");
        }
        return days;
    }

}
