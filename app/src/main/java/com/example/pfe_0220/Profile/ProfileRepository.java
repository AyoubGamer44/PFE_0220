package com.example.pfe_0220.Profile;

import android.app.Application;
import android.os.AsyncTask;



import com.example.pfe_0220.DatabaseFiles.ApplicationDatabase;
import com.example.pfe_0220.DatabaseFiles.ProfileDao;
import com.example.pfe_0220.Profile.Models.ManagerProfile;

public class ProfileRepository {

     public ManagerProfile currentUser;

     ProfileDao profileDao;

    public ProfileRepository(Application application) {
      ApplicationDatabase data = ApplicationDatabase.getInstance(application);
      profileDao = data.profileDao();
    }

    public boolean InsertProfile(ManagerProfile profile) throws Exception{
     return    new InsertProfileAsync(profileDao).execute(profile).get();
    }

    public void getProfileOf(String email,String password) throws Exception{
        currentUser = new getProfileAsync(profileDao,email,password).execute().get();
    }





    static class InsertProfileAsync extends AsyncTask<ManagerProfile,Void,Boolean> {

        ProfileDao dao;

        public InsertProfileAsync(ProfileDao dao) {
            this.dao = dao;
        }

        @Override
        protected Boolean doInBackground(ManagerProfile... managerProfiles) {

            try {
                dao.Insert(managerProfiles[0]);
                return  true;
            } catch (Exception e) {
                return false;
            }

        }
    }


    static class getProfileAsync extends AsyncTask<Void,Void,ManagerProfile> {

        ProfileDao dao;
         String email; String password;

        public getProfileAsync(ProfileDao dao, String email, String password) {
            this.dao = dao;
            this.email = email;
            this.password = password;
        }


        @Override
        protected ManagerProfile doInBackground(Void... Voids) {
            return dao.getProfilewithId(email,password);
        }
    }


}
