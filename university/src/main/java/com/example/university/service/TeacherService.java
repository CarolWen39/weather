package com.example.university.service;


import com.example.university.pojo.dto.TeacherResponseDTO;
import com.example.university.pojo.entity.Teacher;
import org.springframework.stereotype.Service;

@Service
public interface TeacherService {
    TeacherResponseDTO getAllTea();
    TeacherResponseDTO getTeaById(String id);
    Teacher insertTeacher(Teacher t);

    Teacher updateTeacher(String id, Teacher t);

    void deleteTeacher(String id);
}
