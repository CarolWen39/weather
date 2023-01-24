package com.example.university.service;


import com.example.university.pojo.dto.Teacher_StudentResponseDTO;
import com.example.university.pojo.entity.Student;
import com.example.university.pojo.entity.Teacher;
import com.example.university.pojo.entity.Teacher_Student;
import org.springframework.stereotype.Service;

@Service
public interface TeacherStudentService {

    // get
    Teacher_StudentResponseDTO getAllTeacher_Student();
    Teacher_StudentResponseDTO findByTeacher(String tid);
    Teacher_StudentResponseDTO findByStudent(String sid);
    Teacher_StudentResponseDTO.Teacher_StudentDTO findByTeacherAndStudent(String tid, String sid);

    // post
    Teacher_Student insertTeacher_Student(Teacher teacher, Student student);
    // put
    Teacher_Student updateTeacher_Student(String id, Teacher teacher, Student student);
    void deleteTeacher_Student(String id);
}
