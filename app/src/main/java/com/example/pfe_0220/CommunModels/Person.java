package com.example.pfe_0220.CommunModels;


public class Person  {


public static String[] PersonType  = {"Student","Responsible"};

    public int id;


    public String firstName;


    public String lastName;


    public String phone_number;


    public String adress;


    public String email;


    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

    }

    public Person() {
    }
}