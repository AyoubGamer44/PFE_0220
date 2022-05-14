package com.example.pfe_0220.DatabaseFiles;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.pfe_0220.Teacher.Model.Teacher;

import java.util.List;

@Dao
public interface TeacherDao {
    @Insert
    public void InsertTeacher(Teacher teacher);
    @Query("select * from teacher order by id")
    public LiveData<List<Teacher>> getTeachers();

}
