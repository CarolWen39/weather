package com.example.university.service.impl;


import com.example.university.exception.ResourceNotFoundException;
import com.example.university.pojo.dto.StudentResponseDTO;
import com.example.university.pojo.dto.StudentResponseDTO.*;
import com.example.university.pojo.entity.Student;
import com.example.university.repository.StudentRepository;
import com.example.university.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    @Transactional
    public StudentResponseDTO getAllStu() {
        Collection<Student> studentCollection = studentRepository.findAll();
        List<StudentDTO> studentDTOS = studentCollection
                .stream()
                .map(s -> new StudentDTO(s))
                .collect(Collectors.toList());

        return new StudentResponseDTO(studentDTOS);
    }

    @Override
    @Transactional
    public StudentResponseDTO getStuById(String id) {
        Optional<Student> student = studentRepository.findById(id);
        if(!student.isPresent())
            throw new ResourceNotFoundException("Student " + id + " not found!");
        List<StudentDTO> studentDto = new ArrayList<>();
        studentDto.add(new StudentDTO(student.get()));
        return new StudentResponseDTO(studentDto);
    }

    @Override
    @Transactional
    public Student insertStudent(Student s) {
//        Optional<Student> optional = studentRepository.findById(s.getId());
//        if(optional.isPresent())
//            throw new ResourceAlreadyExistException("Student " + s.getId() + " already exists!");
//        Student stu = new Student(s.getName());
        studentRepository.save(s);
        return s;
    }

    @Override
    @Transactional
    public Student updateStudent(String id, Student s) {
        Optional<Student> optional = studentRepository.findById(id);
        if(!optional.isPresent())
            throw new ResourceNotFoundException("Student " + id + " not found!");
        else {
            Student existingStudent = optional.get();

            existingStudent.setName(s.getName());

            studentRepository.save(existingStudent);
            return existingStudent;
        }
    }

    @Override
    @Transactional
    public void deleteStudent(String id) {
        studentRepository.deleteById(id);
    }
}
