package com.example.university.repository;


import com.example.university.pojo.entity.Teacher_Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherStudentRepository extends JpaRepository<Teacher_Student, String> {

    @Query("SELECT ts FROM Teacher_Student ts WHERE t_id=?1")
    List<Teacher_Student> findByTeacher(String tid);

    @Query("SELECT ts FROM Teacher_Student ts WHERE s_id=?1")
    List<Teacher_Student> findByStudent(String sid);

    @Query("SELECT ts FROM Teacher_Student ts WHERE t_id=?1 and s_id=?2")
    Teacher_Student findByTeacherAndStudent(String tid, String sid);

}
