package com.example.pfe_0220.Departement.Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Module {
    @PrimaryKey(autoGenerate = true)
   public int id;
  public  String name;
  public int speciality_id;

    public Module(int id, String name, int speciality_id) {
        this.id = id;
        this.name = name;
        this.speciality_id = speciality_id;
    }

@Ignore
    public Module(String name, int speciality_id) {
        this.name = name;
        this.speciality_id = speciality_id;
    }

    @Ignore
    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
