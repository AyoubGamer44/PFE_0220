package com.example.pfe_0220.DatabaseFiles;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.pfe_0220.Planning.Models.Attendence;
import com.example.pfe_0220.Planning.Models.AttendenceNode;
import com.example.pfe_0220.Planning.Models.SchoolClass;
import com.example.pfe_0220.Planning.Models.SchoolClassNode;

import java.util.List;

@Dao
public interface PlanningDao {


    @Query("select c.id as id ,c.start_time as start_time,section ,level, c.end_time as end_time, c.school_group as school_group , c.school_classtype as school_classtype , m.name as module , d.name as departement , s.name as speciality  from schoolclass c,Departement d,Speciality s,Module m" +
            "  where c.departement_id == d.id and c.module_id == m.id and c.speciality_id == s.id ")
    LiveData<List<SchoolClassNode>> getClassesOf();

    @Insert
    public void InsertSchoolClass(SchoolClass schoolClass);

    @Transaction
    @Insert
    void InsertAttendence(Attendence attendence);

    @Update
    void UpdateAttendence(Attendence attendence);

    @Query("Select a.id as id , s.firstName as first_name,s.lastName  as last_name ,s.id as student_id ,a.enterTime as enterTime,a.presenceType as presenceType ,a.state as state from Teacher s , Attendence a ,SchoolClass sc where sc.id = :schoolClassId and a.presenceType == :presence_type and a.schoolClass_id = sc.id")
    LiveData<List<AttendenceNode>> getResponsiblesAttendenceOf(int schoolClassId, int presence_type);

    @Query("Select a.id as id , s.firstName as first_name,s.lastName  as last_name ,s.id as student_id ,a.enterTime as enterTime,a.presenceType as presenceType ,a.state as state from Student s , Attendence a ,SchoolClass sc where sc.id = :schoolClassId and a.presenceType == :presence_type and a.schoolClass_id = sc.id and s.id == a.student_id")
    LiveData<List<AttendenceNode>> getStudentAttendenceOf(int schoolClassId, int presence_type);

    @Transaction
    @Query("select MAX(id) from schoolclass")
    int getLastInsertedIdfrom();

    @Query("select * from attendence where student_id = :student_id ")
    Attendence getAttendenceOf(int student_id);
}
