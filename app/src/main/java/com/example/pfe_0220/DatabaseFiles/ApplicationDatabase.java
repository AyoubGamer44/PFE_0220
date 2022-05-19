package com.example.pfe_0220.DatabaseFiles;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.pfe_0220.Departement.Models.Departement;
import com.example.pfe_0220.Departement.Models.Module;
import com.example.pfe_0220.Departement.Models.Speciality;
import com.example.pfe_0220.Planning.Models.Attendence;
import com.example.pfe_0220.Planning.Models.SchoolClass;
import com.example.pfe_0220.Student.Model.Student;
import com.example.pfe_0220.Teacher.Model.Teacher;


@Database(
        entities = {Departement.class, Speciality.class, Teacher.class, SchoolClass.class, Module.class, Student.class, Attendence.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class ApplicationDatabase extends RoomDatabase {


    public abstract DepartementDao departementDao();
    public abstract TeacherDao teacherDao();
    public abstract PlanningDao planningDao();
    public abstract StudentDao studentDao();

    private static ApplicationDatabase instance;

    public static synchronized ApplicationDatabase getInstance(Context context) {
        if(instance == null){
            // for creating a instance for our database
            // we are creating a database builder and passing
            // our database class with our database name.
            instance =    Room.databaseBuilder(context.getApplicationContext(),
                            ApplicationDatabase.class, "pfe_app_database")
                    // below line is use to add fall back to
                    // destructive migration to our database.
                    .fallbackToDestructiveMigration()
                    // below line is to add callback
                    // to our database.

                    // below line is to
                    // build our database.
                    .build();
        }
        return instance;
    }
}
