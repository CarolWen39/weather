package com.example.search.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SearchServiceImpl implements SearchService{
    private final RestTemplate restTemplate;

    @Value("${user.rest.studentUrl}")
    private String allStudentsURL;
    @Value("${user.rest.teacherUrl}")
    private String allTeachersURL;
    @Value("${user.rest.teacherStudentUrl}")
    private String allTeacherStudentsURL;
    @Value("${user.rest.teacherStudentByTUrl}")
    private String TeacherStudentsByTIdURL;
    @Value("${user.rest.teacherStudentBySUrl}")
    private String TeacherStudentsBySIdURL;
    @Value("${user.rest.teacherStudentByTSUrl}")
    private String TeacherStudentsByTSIdURL;
    @Value("${user.rest.employeeUrl}")
    private String allUsersURL;
    @Autowired
    public SearchServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<?> allStudents() {
        return restTemplate.getForObject(
                allStudentsURL, List.class);

    }
    public List<?> studentById(String id) {
        return restTemplate.getForObject(
                allStudentsURL+"/"+id, List.class);
    }
    public List<?> allTeachers() {
        return restTemplate.getForObject(
                allTeachersURL, List.class);
    }
    public List<?> teacherById(String id) {
        return restTemplate.getForObject(
                allTeachersURL+"/"+id, List.class);
    }
    public List<?> allTeacherStudents() {
        return restTemplate.getForObject(
                allTeacherStudentsURL, List.class);
    }

    public List<?> teacherStudentByTeacher(String tid) {
        Map<String, String> vars = new HashMap<>();
        vars.put("teacher", tid);
        return restTemplate.getForObject(
                TeacherStudentsByTIdURL, List.class, vars);
    }
    public List<?> teacherStudentByStudent(String sid) {
        Map<String, String> vars = new HashMap<>();
        vars.put("student", sid);
        return restTemplate.getForObject(
                TeacherStudentsBySIdURL, List.class, vars);
    }

    public List<?> teacherStudentsById(String tid, String sid) {
        Map<String, String> vars = new HashMap<>();
        vars.put("teacher", tid);
        vars.put("student", sid);
        return restTemplate.getForObject(
                TeacherStudentsByTSIdURL, List.class, vars);
    }
    public List<?> allUsers() {
        return restTemplate.getForObject(
                allUsersURL, List.class);
    }

    public List<?> userById(Long id) {
        return restTemplate.getForObject(
                allUsersURL+"/"+id, List.class);
    }

}
