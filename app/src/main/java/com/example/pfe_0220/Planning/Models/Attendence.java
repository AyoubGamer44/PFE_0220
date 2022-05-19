package com.example.pfe_0220.Planning.Models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Calendar;


@Entity
public class Attendence {
    @Ignore
    public static int STUDENT = 2;
    @Ignore
    public static int TEACHER = 1;

    @Ignore
    public static int ABSENT = 2;

    @Ignore
    public static int PRESENT = 1;

    @Ignore
    public static int PENDING = 0;
    @Ignore
    public static int LATE = 3;


    public static  String[] shortpresenceType  = {".","P","A","L"};

    @PrimaryKey(autoGenerate = true)
    public int id;
    public int module_id;
    public int schoolClass_id;
    public int student_id;
    public Calendar enterTime;
    public  int presenceType; // responsible or student
    public int state;

    public Attendence(int id, int module_id, int schoolClass_id, int student_id, Calendar enterTime, int presenceType, int state) {
        this.id = id;
        this.module_id = module_id;
        this.schoolClass_id = schoolClass_id;
        this.student_id = student_id;
        this.enterTime = enterTime;
        this.presenceType = presenceType;
        this.state = state;
    }

    @Ignore
    public Attendence(int module_id, int schoolClass_id, int student_id, Calendar enterTime, int presenceType, int state) {
        this.module_id = module_id;
        this.schoolClass_id = schoolClass_id;
        this.student_id = student_id;
        this.enterTime = enterTime;
        this.presenceType = presenceType;
        this.state = state;
    }

    @Ignore

    public Attendence() {
    }

    public void setModule_id(int module_id) {
        this.module_id = module_id;
    }

    public void setSchoolClass_id(int schoolClass_id) {
        schoolClass_id = schoolClass_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public void setEnterTime(Calendar enterTime) {
        this.enterTime = enterTime;
    }

    public void setPresenceType(int presenceType) {
        this.presenceType = presenceType;
    }

    public void setState(int state) {
        this.state = state;
    }
}
