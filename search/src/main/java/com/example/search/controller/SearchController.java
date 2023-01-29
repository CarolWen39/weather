package com.example.search.controller;


import com.example.search.service.SearchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

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
    public CompletableFuture<?> getAllStudents() {
        return searchService.allStudents().thenApply(ResponseEntity::ok);
    }
//    @GetMapping("/students")
//    public ResponseEntity<?> getAllStudents() {
//        CompletableFuture<?> students1 = searchService.allStudents();
//        CompletableFuture<?> students2 = searchService.allStudents();
//        CompletableFuture<?> students3 = searchService.allStudents();
//        CompletableFuture.allOf(students1, students2, students3).join();
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }

    @GetMapping("/students/{id}")
    public CompletableFuture<?> getStudentById(@PathVariable String id) {
        return searchService.studentById(id).thenApply(ResponseEntity::ok);

    }

    @GetMapping("/teachers")
    public CompletableFuture<?> getAllTeachers() {
        return searchService.allTeachers().thenApply(ResponseEntity::ok);
    }

    @GetMapping("/teachers/{id}")
    public CompletableFuture<?> getTeacherById(@PathVariable String id) {
        return searchService.teacherById(id).thenApply(ResponseEntity::ok);
    }

    @GetMapping("/teacher_students")
    public CompletableFuture<?> getAllTeacherStudents() {
        return searchService.allTeacherStudents().thenApply(ResponseEntity::ok);
    }

    @GetMapping(path = "/teacher_students",params = "teacher")
    public CompletableFuture<?> getByTeacherId(@RequestParam String teacher) {
        return searchService.teacherStudentByTeacher(teacher).thenApply(ResponseEntity::ok);
    }
    @GetMapping(path = "/teacher_students",params = "student")
    public CompletableFuture<?> getByStudentId(@RequestParam String student) {
        return searchService.teacherStudentByStudent(student).thenApply(ResponseEntity::ok);
    }
    @GetMapping(path = "/teacher_students",params = {"teacher", "student"})
    public CompletableFuture<?> getByTeacherIdAndStudentId(@RequestParam String teacher, @RequestParam String student) {
        return searchService.teacherStudentsById(teacher, student).thenApply(ResponseEntity::ok);
    }
    @GetMapping("/employees")
    public CompletableFuture<?> getAllEmployees() {
        return searchService.allUsers().thenApply(ResponseEntity::ok);
    }

    @GetMapping("/employees/{id}")
    public CompletableFuture<?> getAllEmployees(@PathVariable Long id) {
        return searchService.userById(id).thenApply(ResponseEntity::ok);
    }
}
