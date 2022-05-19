package com.example.pfe_0220.Planning.Models;

import java.util.Calendar;

/**
 * used when read data from database
 */
public class AttendenceNode {




    public int id;
    public String first_name;
    public String last_name;
    public String student_id;
    public Calendar enterTime;
    public int state;
    public int presenceType;

    public AttendenceNode(int id, String first_name,String last_name, String student_id, Calendar enterTime, int state, int presenceType) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.student_id = student_id;
        this.enterTime = enterTime;
        this.state = state;
        this.presenceType = presenceType;
    }
}
