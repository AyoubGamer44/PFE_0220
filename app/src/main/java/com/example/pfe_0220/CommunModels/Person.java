package com.example.pfe_0220.CommunModels;


import androidx.room.PrimaryKey;

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


    /***
     * constructor used to instantiate the manager profile object

     * @param firstName
     * @param lastName
     * @param phone_number
     * @param email
     */
    public Person( String firstName, String lastName, String phone_number,  String email) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.phone_number = phone_number;
        this.adress = adress;
        this.email = email;
    }

    public Person() {
    }
}