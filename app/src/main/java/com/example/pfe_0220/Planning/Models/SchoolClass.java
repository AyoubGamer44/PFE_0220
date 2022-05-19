package com.example.pfe_0220.Planning.Models;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.pfe_0220.Student.Model.Student;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
@Entity
public class SchoolClass {
    @PrimaryKey(autoGenerate = true)
    public int id ;

    public Calendar start_time,end_time;

    public int module_id,speciality_id,departement_id;

    public int level;
public int section;
    public int school_group;

    public int school_classtype;


    @Ignore
  public   ArrayList<Student> students_of_class = new ArrayList<>();




    public SchoolClass(int id, Calendar start_time, Calendar end_time, int module_id, int speciality_id, int departement_id, int level, int section, int school_group, int school_classtype) {
        this.id = id;
        this.start_time = start_time;
        this.end_time = end_time;
        this.module_id = module_id;
        this.speciality_id = speciality_id;
        this.departement_id = departement_id;
        this.level = level;
        this.section = section;
        this.school_group = school_group;
        this.school_classtype = school_classtype;
    }

    @Ignore
    public SchoolClass(Calendar start_time, Calendar end_time, int module_id, int speciality_id, int departement_id, int level, int school_group, int school_classtype) {
        this.id = id;
        this.start_time = start_time;
        this.end_time = end_time;
        this.module_id = module_id;
        this.speciality_id = speciality_id;
        this.departement_id = departement_id;
        this.level = level;
        this.school_group = school_group;
        this.school_classtype = school_classtype;
    }

    public SchoolClass() {
    }

    public void setStart_time(Calendar start_time) {
        this.start_time = start_time;
    }

    public void setEnd_time(Calendar end_time) {
        this.end_time = end_time;
    }

    public void setModule_id(int module_id) {
        this.module_id = module_id;
    }

    public void setSpeciality_id(int speciality_id) {
        this.speciality_id = speciality_id;
    }

    public void setDepartement_id(int departement_id) {
        this.departement_id = departement_id;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setSchool_group(int school_group) {
        this.school_group = school_group;
    }

    public void setSchool_classtype(int school_classtype) {
        this.school_classtype = school_classtype;
    }

    public void setSection(int section) {
        this.section = section;
    }
}
