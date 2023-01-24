package com.example.university.service.impl;


import com.example.university.exception.ResourceAlreadyExistException;
import com.example.university.exception.ResourceNotFoundException;
import com.example.university.pojo.dto.Teacher_StudentResponseDTO;
import com.example.university.pojo.dto.Teacher_StudentResponseDTO.*;
import com.example.university.pojo.entity.Student;
import com.example.university.pojo.entity.Teacher;
import com.example.university.pojo.entity.Teacher_Student;
import com.example.university.repository.StudentRepository;
import com.example.university.repository.TeacherRepository;
import com.example.university.repository.TeacherStudentRepository;
import com.example.university.service.TeacherStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherStudentServiceImpl implements TeacherStudentService {
    private final TeacherStudentRepository teacherStudentRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherStudentServiceImpl(TeacherStudentRepository teacherStudentRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.teacherStudentRepository = teacherStudentRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    @Transactional
    public Teacher_StudentResponseDTO getAllTeacher_Student() {
        Collection<Teacher_Student> teacherStudents = teacherStudentRepository.findAll();
        List<Teacher_StudentDTO> teacherStudentDTOS = teacherStudents
                .stream()
                .map(ts -> new Teacher_StudentDTO(ts))
                .collect(Collectors.toList());

        return new Teacher_StudentResponseDTO(teacherStudentDTOS);
    }



    @Override
    @Transactional
    public Teacher_StudentResponseDTO findByTeacher(String tid) {
        List<Teacher_Student> teacherStudents = teacherStudentRepository.findByTeacher(tid);
        if(teacherStudents.isEmpty())
            throw new ResourceNotFoundException("Teacher " + tid + " not exist in Junction Table!");

        List<Teacher_StudentDTO> teacherStudentDTOS = teacherStudents
                .stream()
                .map(ts -> new Teacher_StudentDTO(ts))
                .collect(Collectors.toList());

        return new Teacher_StudentResponseDTO(teacherStudentDTOS);
    }

    @Override
    @Transactional
    public Teacher_StudentResponseDTO findByStudent(String sid) {
        List<Teacher_Student> teacherStudents = teacherStudentRepository.findByStudent(sid);
        if(teacherStudents.isEmpty())
            throw new ResourceNotFoundException("Student " + sid + " not exist in Junction Table!");
        List<Teacher_StudentDTO> teacherStudentDTOS = teacherStudents
                .stream()
                .map(ts -> new Teacher_StudentDTO(ts))
                .collect(Collectors.toList());

        return new Teacher_StudentResponseDTO(teacherStudentDTOS);
    }

    @Override
    @Transactional
    public Teacher_StudentDTO findByTeacherAndStudent(String tid, String sid) {
        Teacher_Student teacherStudent = teacherStudentRepository.findByTeacherAndStudent(tid, sid);
        if(teacherStudent == null)
            throw new ResourceNotFoundException("This relationship not exist in Junction Table!");
        return new Teacher_StudentDTO(teacherStudent);
    }

    @Override
    @Transactional
    public Teacher_Student insertTeacher_Student(Teacher teacher, Student student) {
        Teacher_Student teacherStudent = teacherStudentRepository.findByTeacherAndStudent(teacher.getId(), student.getId());
        if(teacherStudent != null)
            throw new ResourceAlreadyExistException("This relationship already exists in Junction Table!");

        Teacher t = teacherRepository.findById(teacher.getId()).get();
        Student s = studentRepository.findById(student.getId()).get();

        Teacher_Student teacher_student = new Teacher_Student(t, s);
        teacherStudentRepository.save(teacher_student);
        return teacher_student;
    }

    @Override
    @Transactional
    public Teacher_Student updateTeacher_Student(String id, Teacher teacher, Student student) {

        Optional<Teacher_Student> optional = teacherStudentRepository.findById(id);
        if(optional == null)
            throw new ResourceNotFoundException("This Junction not found!");
        else {
            Teacher_Student teacherStudent = optional.get();
            teacherStudent.setTeacher(teacher);
            teacherStudent.setStudent(student);

            teacherStudentRepository.save(teacherStudent);
            return teacherStudent;
        }
    }

    @Override
    @Transactional
    public void deleteTeacher_Student(String id) {
        teacherStudentRepository.deleteById(id);
    }
}
