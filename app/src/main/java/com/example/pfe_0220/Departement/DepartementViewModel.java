package com.example.pfe_0220.Departement;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;


import com.example.pfe_0220.Departement.Models.Departement;
import com.example.pfe_0220.Departement.Models.Speciality;

public class DepartementViewModel extends AndroidViewModel {

    public  int selected_departement;


    public  DepartementRepository departementRepository;

public MutableLiveData<Boolean> status = new MutableLiveData<Boolean>();

    public DepartementViewModel(@NonNull Application application)  {
        super(application);
        departementRepository = new DepartementRepository(getApplication());

        try {
            departementRepository.getDepartement();
        } catch (Exception e) {
        status.postValue(false);
        }




    }






    public void InsertDepartement(Departement departement) throws Exception {
     status.postValue( departementRepository.InsertDepartement(departement));
    }


    public void InsertSpeciality(Speciality speciality) throws Exception {
        status.postValue( departementRepository.InsertSpeciality(speciality));
    }




}
