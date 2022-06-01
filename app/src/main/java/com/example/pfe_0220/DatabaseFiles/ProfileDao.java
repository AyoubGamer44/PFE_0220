package com.example.pfe_0220.DatabaseFiles;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.pfe_0220.Profile.Models.ManagerProfile;

@Dao
public interface ProfileDao {

    @Insert
    void Insert(ManagerProfile profile);


    @Update
    void Update(ManagerProfile profile);

    @Query("select * from users where email =:email  and password = :password")
    ManagerProfile getProfilewithId(String email ,String password);

}
