package com.example.pfe_0220.Departement;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.pfe_0220.DatabaseFiles.ApplicationDatabase;
import com.example.pfe_0220.DatabaseFiles.DepartementDao;
import com.example.pfe_0220.Departement.Models.Departement;
import com.example.pfe_0220.Departement.Models.Speciality;

import java.util.List;
import java.util.concurrent.ExecutionException;


public class DepartementRepository {


    public static String[] Levels = {"Licence 1", "Licence 2", "Licence 3", "Master 1", "Master 2", "Licence 1"};

    private DepartementDao dao;

  public LiveData< List<Departement>> savedDepartements ;
  public LiveData< List<Speciality>> savedSpecialities ;



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

    public boolean InsertDepartement(Departement departement) throws ExecutionException, InterruptedException {

        return new InsertDepartement(dao).execute(departement).get();

    }


    public boolean InsertSpeciality(Speciality speciality) throws ExecutionException, InterruptedException {

        return new InsertSpeciality(dao).execute(speciality).get();

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