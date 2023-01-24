package com.example.university.service.impl;


import com.example.university.exception.ResourceNotFoundException;
import com.example.university.pojo.dto.TeacherResponseDTO;
import com.example.university.pojo.dto.TeacherResponseDTO.*;
import com.example.university.pojo.entity.Teacher;
import com.example.university.repository.TeacherRepository;
import com.example.university.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }


    @Override
    public TeacherResponseDTO getAllTea() {
        Collection<Teacher> teacherCollection = teacherRepository.findAll();
        List<TeacherDTO> teacherDTOS = teacherCollection
                .stream()
                .map(t -> new TeacherDTO(t))
                .collect(Collectors.toList());

        return new TeacherResponseDTO(teacherDTOS);
    }

    @Override
    @Transactional
    public TeacherDTO getTeaById(String id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if(!teacher.isPresent())
            throw new ResourceNotFoundException("Teacher " + id + " not found!");
        return new TeacherDTO(teacher.get());
    }

    @Override
    @Transactional
    public Teacher insertTeacher(Teacher teacher) {
//        Optional<Teacher> optional = teacherRepository.findById(t.getId());
//        if(optional.isPresent())
//            throw new ResourceAlreadyExistException("Student " + t.getId() + " already exists!");
//        Teacher teacher = new Teacher(t.getName());

        teacherRepository.save(teacher);
        return teacher;
    }

    @Override
    @Transactional
    public Teacher updateTeacher(String id, Teacher t) {
        Optional<Teacher> optional = teacherRepository.findById(id);
        if(!optional.isPresent())
            throw new ResourceNotFoundException("Teacher " + id + " not found!");
        else {
            Teacher existingTeacher = optional.get();

            existingTeacher.setName(t.getName());

            teacherRepository.save(existingTeacher);
            return existingTeacher;
        }
    }

    @Override
    @Transactional
    public void deleteTeacher(String id) {
        teacherRepository.deleteById(id);
    }
}
