package com.example.search.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

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

    Logger logger = LoggerFactory.getLogger(SearchServiceImpl.class);
    @Autowired
    public SearchServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Async
    public CompletableFuture<?> allStudents() {
        long start = System.currentTimeMillis();
        logger.info("get student lists by "+Thread.currentThread().getName());
        List<?> response = restTemplate.getForObject(
                allStudentsURL, List.class);
        logger.info("get response from university/students " + response.size() + " "+Thread.currentThread().getName());
        long end = System.currentTimeMillis();
        logger.info("Total time {}", end-start);
        return CompletableFuture.completedFuture(response);
    }
    public CompletableFuture<?> studentById(String id) {
        return CompletableFuture.completedFuture(restTemplate.getForObject(
                allStudentsURL+"/"+id, List.class));
    }
    public CompletableFuture<?> allTeachers() {
        long start = System.currentTimeMillis();
        logger.info("get teacher lists by "+Thread.currentThread().getName());
        List<?> response = restTemplate.getForObject(
                allTeachersURL, List.class);
        logger.info("get response from university/teachers " + response.size() + " "+Thread.currentThread().getName());
        long end = System.currentTimeMillis();
        logger.info("Total time {}", end-start);
        return CompletableFuture.completedFuture(response);
    }
    public CompletableFuture<?> teacherById(String id) {
        return CompletableFuture.completedFuture(restTemplate.getForObject(
                allTeachersURL+"/"+id, List.class));
    }
    public CompletableFuture<?> allTeacherStudents() {
        return CompletableFuture.completedFuture(restTemplate.getForObject(
                allTeacherStudentsURL, List.class));
    }

    public CompletableFuture<?> teacherStudentByTeacher(String tid) {
        Map<String, String> vars = new HashMap<>();
        vars.put("teacher", tid);
        return CompletableFuture.completedFuture(restTemplate.getForObject(
                TeacherStudentsByTIdURL, List.class, vars));
    }
    public CompletableFuture<?> teacherStudentByStudent(String sid) {
        Map<String, String> vars = new HashMap<>();
        vars.put("student", sid);
        return CompletableFuture.completedFuture(restTemplate.getForObject(
                TeacherStudentsBySIdURL, List.class, vars));
    }

    public CompletableFuture<?> teacherStudentsById(String tid, String sid) {
        Map<String, String> vars = new HashMap<>();
        vars.put("teacher", tid);
        vars.put("student", sid);
        return CompletableFuture.completedFuture(restTemplate.getForObject(
                TeacherStudentsByTSIdURL, List.class, vars));
    }
    public CompletableFuture<?> allUsers() {
        return CompletableFuture.completedFuture(restTemplate.getForObject(
                allUsersURL, List.class));
    }

    public CompletableFuture<?> userById(Long id) {
        return CompletableFuture.completedFuture(restTemplate.getForObject(
                allUsersURL+"/"+id, List.class));
    }

}
