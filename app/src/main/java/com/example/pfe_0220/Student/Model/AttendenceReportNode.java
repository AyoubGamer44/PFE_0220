package com.example.pfe_0220.Student.Model;

import java.util.Calendar;

public class AttendenceReportNode {

    public int school_classtype ;

   public int presenceCount,absenceCount;
public Calendar start_time,end_time;

    public AttendenceReportNode(int school_classtype, int presenceCount, int absenceCount, Calendar start_time, Calendar end_time) {
        this.school_classtype = school_classtype;
        this.presenceCount = presenceCount;
        this.absenceCount = absenceCount;
        this.start_time = start_time;
        this.end_time = end_time;
    }
}
