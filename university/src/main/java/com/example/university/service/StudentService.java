package com.example.university.service;


import com.example.university.pojo.dto.StudentResponseDTO;
import com.example.university.pojo.dto.StudentResponseDTO.*;
import com.example.university.pojo.entity.Student;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {
    StudentResponseDTO getAllStu();
    StudentDTO getStuById(String id);
    Student insertStudent(Student s);
    Student updateStudent(String id, Student s);
    void deleteStudent(String id);

}
