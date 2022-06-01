package com.example.pfe_0220.Profile;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.pfe_0220.Profile.Models.ManagerProfile;

public class ProfileViewModel extends AndroidViewModel {

    public ProfileRepository profileRepository;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        profileRepository = new ProfileRepository(application);

    }


    public boolean InsertProfile(ManagerProfile profile) throws Exception {
     return    profileRepository.InsertProfile(profile);
    }

    public void LogIn(String email, String password) throws Exception {
        profileRepository.getProfileOf(email, password);
    }


}
