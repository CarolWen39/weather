package com.example.university.controller;


import com.example.university.pojo.dto.TeacherResponseDTO;
import com.example.university.pojo.dto.TeacherResponseDTO.*;
import com.example.university.pojo.entity.Teacher;
import com.example.university.service.impl.TeacherServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RefreshScope
@EnableEurekaClient
@RequestMapping("/university")
public class TeacherController {
    private final TeacherServiceImpl service;

    @Autowired
    public TeacherController(TeacherServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/teachers")
    public ResponseEntity<?> getTea(@RequestParam(required = false) String name) {
        return new ResponseEntity<>(service.getAllTea().getTeacherList(), HttpStatus.OK);
    }

    @GetMapping("/teachers/{id}")
    public ResponseEntity<?> getTeaById(@PathVariable String id) {
        return new ResponseEntity<>(service.getTeaById(id).getTeacherList(), HttpStatus.OK);
    }

    @PostMapping("/teachers")
    public ResponseEntity<TeacherDTO> createTeacher(@RequestBody JsonNode object) {
        Teacher teacher = new Teacher(object.get("name").asText());
        return new ResponseEntity<>(new TeacherDTO(service.insertTeacher(teacher)), HttpStatus.OK);
    }

    @PutMapping("/teachers/{id}")
    public ResponseEntity<TeacherDTO> updateTeacher(@PathVariable String id, @RequestBody JsonNode object) {
        Teacher teacher = new Teacher(object.get("name").asText());
        return new ResponseEntity<>(new TeacherDTO(service.updateTeacher(id, teacher)), HttpStatus.OK);
    }

    @DeleteMapping("/teachers/{id}")
    public ResponseEntity<Boolean> deleteTeacher(@PathVariable String id) {
        service.deleteTeacher(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
