package com.example.pfe_0220.Student.Model;

import com.example.pfe_0220.CommunModels.Person;


public class Student extends Person {





    public int section;

    public String group;


    public int level;

    public   int department_id;
    public  int speciality_id;

    public Student(String firstName, String lastName) {
        super(firstName, lastName);

    }
}