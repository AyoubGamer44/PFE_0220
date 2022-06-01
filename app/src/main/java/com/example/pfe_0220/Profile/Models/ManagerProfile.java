package com.example.pfe_0220.Profile.Models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.pfe_0220.CommunModels.Person;


@Entity(tableName = "users")
public class ManagerProfile extends Person {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String password;


    public ManagerProfile(int id, String firstName, String lastName, String phone_number, String email,  String password) {
        super( firstName, lastName, phone_number, email);
        this.id = id;
        this.password = password;
    }

@Ignore
    public ManagerProfile( String firstName, String lastName, String phone_number, String email, String password) {
        super( firstName, lastName, phone_number, email);

        this.password = password;
    }

}
