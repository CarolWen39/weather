package com.example.university.controller;


import com.example.university.pojo.dto.Teacher_StudentResponseDTO;
import com.example.university.pojo.dto.Teacher_StudentResponseDTO.*;
import com.example.university.pojo.entity.Student;
import com.example.university.pojo.entity.Teacher;
import com.example.university.service.impl.TeacherStudentServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@EnableEurekaClient
@RefreshScope
@RequestMapping("/university")
public class TeacherStudentController {
    private final TeacherStudentServiceImpl teacherStudentService;

    public TeacherStudentController(TeacherStudentServiceImpl teacherStudentService) {
        this.teacherStudentService = teacherStudentService;
    }
    @GetMapping("/teacher_students")
    public ResponseEntity<Teacher_StudentResponseDTO> getAllJunction() {
        return new ResponseEntity<>(teacherStudentService.getAllTeacher_Student(), HttpStatus.OK);
    }
    @GetMapping(path = "/teacher_students",params = "teacher")
    public ResponseEntity<Teacher_StudentResponseDTO> getByTeacherId(@RequestParam String teacher) {
        return new ResponseEntity<>(teacherStudentService.findByTeacher(teacher), HttpStatus.OK);
    }
    @GetMapping(path = "/teacher_students",params = "student")
    public ResponseEntity<Teacher_StudentResponseDTO> getByStudentId(@RequestParam String student) {
        return new ResponseEntity<>(teacherStudentService.findByStudent(student), HttpStatus.OK);
    }
    @GetMapping(path = "/teacher_students",params = {"teacher", "student"})
    public ResponseEntity<Teacher_StudentDTO> getByTeacherIdAndStudentId(@RequestParam String teacher, @RequestParam String student) {
        return new ResponseEntity<>(teacherStudentService.findByTeacherAndStudent(teacher, student), HttpStatus.OK);
    }

    @PostMapping("/teacher_students")
    public ResponseEntity<Teacher_StudentDTO> createJunction(@RequestBody ObjectNode teacherStudentObj) {
        JsonNode teacherObj = teacherStudentObj.get("teacher");
        JsonNode studentObj = teacherStudentObj.get("student");

        ObjectMapper mapper = new ObjectMapper();
        Teacher teacher = mapper.convertValue(teacherObj, Teacher.class);
        Student student = mapper.convertValue(studentObj, Student.class);
        return new ResponseEntity<>(new Teacher_StudentDTO(teacherStudentService.insertTeacher_Student(teacher, student)), HttpStatus.OK);
    }

    @PutMapping("/teacher_students/{id}")
    public ResponseEntity<Teacher_StudentDTO> updateJunction(@PathVariable String id, @RequestBody ObjectNode teacherStudentObj) {
        JsonNode teacherObj = teacherStudentObj.get("teacher");
        JsonNode studentObj = teacherStudentObj.get("student");

        ObjectMapper mapper = new ObjectMapper();
        Teacher teacher = mapper.convertValue(teacherObj, Teacher.class);
        Student student = mapper.convertValue(studentObj, Student.class);

        return new ResponseEntity<>(new Teacher_StudentDTO(teacherStudentService.updateTeacher_Student(id, teacher, student)), HttpStatus.OK);
    }

    @DeleteMapping("/teacher_students/{id}")
    public ResponseEntity<Boolean> deleteJunction(@PathVariable String id) {
        teacherStudentService.deleteTeacher_Student(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
