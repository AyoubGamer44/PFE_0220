package com.example.pfe_0220.DatabaseFiles;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.pfe_0220.Departement.Models.Departement;
import com.example.pfe_0220.Departement.Models.Speciality;

import java.util.List;

@androidx.room.Dao
public interface DepartementDao {


    @Transaction
    @Insert
    void InsertDepartement(Departement departement);

    @Query("select * from departement order by id")
    LiveData<List<Departement>> getAllDepartement();


    @Transaction
    @Insert
    void InsertSpeciality(Speciality speciality);


    @Query("select * from Speciality where department_id = :departement_id   order by id")
    LiveData<List<Speciality>> getSpecialities(int departement_id);


}
