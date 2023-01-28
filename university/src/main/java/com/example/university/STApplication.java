package com.example.university;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class STApplication {
    public static void main(String[] args) {
        SpringApplication.run(STApplication.class, args);
    }
}

/**
 *  homework:
 *     1. rest api design in readme
 *     2. impl Student + Teacher rest api : CRUD
 *     3. handle global exception @ControllerAdvice + @ExceptionHandler
 *
 *
 *  Design:
 *     Students API
 *      1) GET -- /students: get all students
 *      2) GET -- /students/{id}: find student by id
 *      3) POST -- /students: post a new student and return this new record
 *      4) PUT -- /students/{id}: update a existed student info and return this new record
 *      5) DELETE -- /students/{id}: delete a existed student and return its id
 *
 *     Teacher API
 *      same as above
 */
