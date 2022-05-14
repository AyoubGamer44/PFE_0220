package com.example.pfe_0220.Teacher.Model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Teacher {

    @PrimaryKey(autoGenerate = true)
    public int id ;

    public String firstName,lastName,email,adress,phonbeNumber;

    public Teacher(int id, String firstName, String lastName, String email, String adress, String phonbeNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.adress = adress;
        this.phonbeNumber = phonbeNumber;
    }

    @Ignore
    public Teacher( String firstName, String lastName, String email, String adress, String phonbeNumber) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.adress = adress;
        this.phonbeNumber = phonbeNumber;
    }
}
