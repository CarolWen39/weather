
package com.example.university.controller;



import com.example.university.pojo.dto.StudentResponseDTO;
import com.example.university.pojo.dto.StudentResponseDTO.*;
import com.example.university.pojo.entity.Student;
import com.example.university.repository.StudentRepository;
import com.example.university.service.StudentService;
import com.example.university.service.impl.StudentServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RefreshScope
@EnableEurekaClient
@RequestMapping("/university/students")
public class StudentController {
    private final StudentServiceImpl service;
//    @Value("${server.port}")
//    private int randomServerPort;
//    @GetMapping("/port")
//    public ResponseEntity<?> queryWeatherByCity() {
//        return new ResponseEntity<>("university service + " + randomServerPort, HttpStatus.OK);
//    }
    @Autowired
    public StudentController(StudentServiceImpl service, StudentRepository studentRepository) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<StudentResponseDTO> getAllStudents(@RequestParam(required = false) String name) {
        StudentResponseDTO studentList = service.getAllStu();
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable String id) {

        return new ResponseEntity<>(service.getStuById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody JsonNode object) {
        Student student = new Student(object.get("name").asText());

        return new ResponseEntity<>(new StudentDTO(service.insertStudent(student)), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable String id, @RequestBody JsonNode object) {
        Student student = new Student(object.get("name").asText());
        return new ResponseEntity<>(new StudentDTO(service.updateStudent(id, student)), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteStudent(@PathVariable String id) {
        service.deleteStudent(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }


}