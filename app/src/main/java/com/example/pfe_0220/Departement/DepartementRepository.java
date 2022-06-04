package com.example.pfe_0220.Departement;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.pfe_0220.DatabaseFiles.ApplicationDatabase;
import com.example.pfe_0220.DatabaseFiles.DepartementDao;
import com.example.pfe_0220.Departement.Models.Departement;
import com.example.pfe_0220.Departement.Models.Module;
import com.example.pfe_0220.Departement.Models.Speciality;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class DepartementRepository {

public static  int FIRST_SECTION = 1;
public static  int LAST_SECTION = 5;
public static  int FIRST_GROUP = 1;
public static  int LAST_GROUP = 6;


    private DepartementDao dao;

    public LiveData<List<Departement>> savedDepartements;
    public LiveData<List<Speciality>> savedSpecialities;
    public LiveData<List<Module>> savedModules;


    public DepartementRepository(Application application) {
        ApplicationDatabase database = ApplicationDatabase.getInstance(application);
        dao = database.departementDao();
    }

    public void getDepartement() throws Exception {


        savedDepartements = new GetDepartement(dao).execute().get();
    }


    public void getSpecialitiesOf(int departement_id) throws Exception {


        savedSpecialities = new GetSpecialities(dao).execute(departement_id).get();
    }


    public void getModuleof(int speciality_id) throws Exception {


        savedModules = new GetModules(dao).execute(speciality_id).get();
    }


    public boolean InsertDepartement(Departement departement) throws ExecutionException, InterruptedException {

        return new InsertDepartement(dao).execute(departement).get();

    }


    public boolean InsertSpeciality(Speciality speciality) throws ExecutionException, InterruptedException {

        return new InsertSpeciality(dao).execute(speciality).get();

    }

    public boolean InsertModule(Module module) throws Exception  {

        return new InsertModule(dao).execute(module).get();

    }



    public static ArrayList<String> getAvailableLevels(){
        ArrayList<String> levels = new ArrayList<>();
        levels.add("Licnece 1");
        levels.add("Licnece 2");
        levels.add("Licnece 3");
        levels.add("Master 1");
        levels.add("Master 2");

        return levels;
    }

    public static ArrayList<String> getSchoolClassType(){
        ArrayList<String> schoolClassesType = new ArrayList<>();
        schoolClassesType.add("Cours");
        schoolClassesType.add("TD");
        schoolClassesType.add("TP");
        schoolClassesType.add("Examen");
        return schoolClassesType;
    }


}

class InsertDepartement extends AsyncTask<Departement, Void, Boolean> {
    DepartementDao dao;

    public InsertDepartement(DepartementDao dao) {
        this.dao = dao;
    }

    @Override
    protected Boolean doInBackground(Departement... departements) {
        try {
            dao.InsertDepartement(departements[0]);
            return true;
        } catch (Exception e) {
            return false;
        }


    }
}


class GetDepartement extends AsyncTask<Void, Void, LiveData<List<Departement>>> {
    DepartementDao dao;

    public GetDepartement(DepartementDao dao) {
        this.dao = dao;
    }


    @Override
    protected LiveData<List<Departement>> doInBackground(Void... voids) {
        return dao.getAllDepartement();
    }
}


class GetSpecialities extends AsyncTask<Integer, Void, LiveData<List<Speciality>>> {
    DepartementDao dao;

    public GetSpecialities(DepartementDao dao) {
        this.dao = dao;
    }


    @Override
    protected LiveData<List<Speciality>> doInBackground(Integer... ids) {
        return dao.getSpecialities(ids[0]);
    }
}


class GetModules extends AsyncTask<Integer, Void, LiveData<List<Module>>> {
    DepartementDao dao;

    public GetModules(DepartementDao dao) {
        this.dao = dao;
    }


    @Override
    protected LiveData<List<Module>> doInBackground(Integer... ids) {
        return dao.getModules(ids[0]);
    }
}



class InsertSpeciality extends AsyncTask<Speciality, Void, Boolean> {
    DepartementDao dao;

    public InsertSpeciality(DepartementDao dao) {
        this.dao = dao;
    }

    @Override
    protected Boolean doInBackground(Speciality... specialities) {
        try {
            dao.InsertSpeciality(specialities[0]);
            return true;
        } catch (Exception e) {
            return false;
        }


    }
}



class InsertModule extends AsyncTask<Module, Void, Boolean> {
    DepartementDao dao;

    public InsertModule(DepartementDao dao) {
        this.dao = dao;
    }

    @Override
    protected Boolean doInBackground(Module... modules) {
        try {
            dao.InsertModule(modules[0]);
            return true;
        } catch (Exception e) {
            return false;
        }


    }
}