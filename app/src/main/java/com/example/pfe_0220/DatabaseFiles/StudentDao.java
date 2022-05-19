package com.example.pfe_0220.DatabaseFiles;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.pfe_0220.Student.Model.Student;

import java.util.List;

@Dao
public interface StudentDao {
    @Insert
    void InsertStudent(Student student);
    @Update
    void UpdateStuednt(Student student);


    @Query("select * from Student where department_id = :departement_id and speciality_id = :speciality_id and level = :level and section = :section and school_group = :school_group")
    LiveData<List<Student>> getStudentof(int departement_id, int speciality_id, int level, int section, int school_group);

@Query("select * from Student")
    LiveData<List<Student>> getAllStudent();


@Query("select * from student where id == :student_id")
    Student getStudentwithId(int student_id);
}
