package com.example.pfe_0220.Student.Model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.pfe_0220.CommunModels.Person;

import java.util.Calendar;

@Entity
public class Student extends Person {

   @PrimaryKey(autoGenerate = false)
    public int id;
    public int section;
    public int school_group;
    public int level;
    public   int department_id;
    public  int speciality_id;
    public Calendar birthDate;

    public Student(String firstName, String lastName, int id, int section, int school_group, int level, int department_id, int speciality_id, Calendar birthDate) {
        super(firstName, lastName);
        this.id = id;
        this.section = section;
        this.school_group = school_group;
        this.level = level;
        this.department_id = department_id;
        this.speciality_id = speciality_id;
        this.birthDate = birthDate;
    }

    @Ignore
    public Student(String firstName, String lastName) {
        super(firstName, lastName);

    }
@Ignore
    public Student() {

    }
}