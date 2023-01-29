package com.example.search.controller;

import com.example.search.service.SearchService;
import com.example.search.service.SearchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RefreshScope
@RequestMapping("/search")
public class SearchController {
    private final SearchServiceImpl searchService;

    @Value("${server.port}")
    private int randomServerPort;

    @Autowired
    public SearchController(SearchServiceImpl service) {
        this.searchService = service;
    }

    @GetMapping("/port")
    public ResponseEntity<?> checkPort() {
        return new ResponseEntity<>("search service + " + randomServerPort, HttpStatus.OK);
    }

    @GetMapping("/students")
    public ResponseEntity<?> getAllStudents() {
        return new ResponseEntity<>(searchService.allStudents(), HttpStatus.OK);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable String id) {
        return new ResponseEntity<>(searchService.studentById(id), HttpStatus.OK);
    }

    @GetMapping("/teachers")
    public ResponseEntity<?> getAllTeachers() {
        return new ResponseEntity<>(searchService.allTeachers(), HttpStatus.OK);
    }

    @GetMapping("/teachers/{id}")
    public ResponseEntity<?> getTeacherById(@PathVariable String id) {
        return new ResponseEntity<>(searchService.teacherById(id), HttpStatus.OK);
    }

    @GetMapping("/teacher_students")
    public ResponseEntity<?> getAllTeacherStudents() {
        return new ResponseEntity<>(searchService.allTeacherStudents(), HttpStatus.OK);
    }

    @GetMapping(path = "/teacher_students",params = "teacher")
    public ResponseEntity<?> getByTeacherId(@RequestParam String teacher) {
        return new ResponseEntity<>(searchService.teacherStudentByTeacher(teacher), HttpStatus.OK);
    }
    @GetMapping(path = "/teacher_students",params = "student")
    public ResponseEntity<?> getByStudentId(@RequestParam String student) {
        return new ResponseEntity<>(searchService.teacherStudentByStudent(student), HttpStatus.OK);
    }
    @GetMapping(path = "/teacher_students",params = {"teacher", "student"})
    public ResponseEntity<?> getByTeacherIdAndStudentId(@RequestParam String teacher, @RequestParam String student) {
        return new ResponseEntity<>(searchService.teacherStudentsById(teacher, student), HttpStatus.OK);
    }
    @GetMapping("/employees")
    public ResponseEntity<?> getAllEmployees() {
        return new ResponseEntity<>(searchService.allUsers(), HttpStatus.OK);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<?> getAllEmployees(@PathVariable Long id) {
        return new ResponseEntity<>(searchService.userById(id), HttpStatus.OK);
    }
}
