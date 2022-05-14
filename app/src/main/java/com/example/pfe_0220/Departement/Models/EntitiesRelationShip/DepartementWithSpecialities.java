package com.example.pfe_0220.Departement.Models.EntitiesRelationShip;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.pfe_0220.Departement.Models.Departement;
import com.example.pfe_0220.Departement.Models.Speciality;

import java.util.List;

public class DepartementWithSpecialities {

    @Embedded
    public Departement departement;
    @Relation(
            parentColumn = "id",
            entityColumn = "id"
    )
    public List<Speciality> specialities;

    public DepartementWithSpecialities(Departement departement, List<Speciality> specialities) {
        this.departement = departement;
        this.specialities = specialities;
    }
}
