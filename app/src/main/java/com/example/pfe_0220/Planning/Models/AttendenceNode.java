package com.example.pfe_0220.Planning.Models;

import android.icu.text.SimpleDateFormat;

import java.util.ArrayList;
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




    public static int getAttendenceof(String student_id, ArrayList<AttendenceNode> nodes){
        for (AttendenceNode node: nodes
             ) {
            if (node.student_id.equals(student_id) ) {
                return node.id;
            }
        }
        return -1;
    }


    public static String getAtendenceTime(Calendar enterTime , Calendar finishTime){
        StringBuffer dateString = new StringBuffer();

        SimpleDateFormat format = new SimpleDateFormat(" h:mm a");
        dateString.append(format.format(enterTime.getTime()));
        dateString.append(" - ");
        dateString.append(format.format(finishTime.getTime()));
        return  dateString.toString();
    }

    public static String getDate(Calendar time){
        SimpleDateFormat format = new SimpleDateFormat(" MMMM d, yyyy");
        return  format.format(time.getTime());
    }
}
