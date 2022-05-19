package com.example.pfe_0220.Planning.Models;

import com.example.pfe_0220.Departement.DepartementRepository;

import java.util.Calendar;

public class SchoolClassNode {


    public int id;

    public Calendar start_time, end_time;


    public int section;

    public int school_group;

    public int school_classtype;

    public int level;

    public String module;

    public String departement;

    public String speciality;

    public SchoolClassNode(int id, Calendar start_time, Calendar end_time, int section, int school_group, int school_classtype, int level, String module, String departement, String speciality) {
        this.id = id;
        this.start_time = start_time;
        this.end_time = end_time;
        this.section = section;
        this.school_group = school_group;
        this.school_classtype = school_classtype;
        this.level = level;
        this.module = module;
        this.departement = departement;
        this.speciality = speciality;
    }

    private String getTimeAsString(Calendar time) {
        String hours = start_time.get(Calendar.HOUR) + "";
        String minute = start_time.get(Calendar.MINUTE) + "";
        return hours + " : " + minute;
    }


    public String getStartTime() {
        return getTimeAsString(start_time);
    }

    public String getEndTime() {
        return getTimeAsString(end_time);
    }



}
