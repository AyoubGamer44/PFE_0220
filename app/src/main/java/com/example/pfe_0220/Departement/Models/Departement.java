package com.example.pfe_0220.Departement.Models;

import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Departement {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;


    public Departement(int id, String name) {
        this.id = id;
        this.name = name;
    }
@Ignore
    public Departement(String departementName) {
        name = departementName;
    }


    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
