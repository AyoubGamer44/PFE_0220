package com.example.pfe_0220.Departement.Models;

import static androidx.room.ForeignKey.CASCADE;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

//@Entity(foreignKeys = {
//        @ForeignKey(entity = Departement.class,
//                parentColumns = "id",
//                childColumns = "department_id",
//                onDelete = ForeignKey.CASCADE)})
@Entity
public class Speciality {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;


    public int department_id;

    public Speciality(int id, String name,int department_id) {
        this.id = id;
        this.name = name;
       this.department_id = department_id;
    }

    @Ignore
    public Speciality(String name,int department_id) {
        this.name = name;
      this.department_id = department_id;
    }


    @Ignore

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
