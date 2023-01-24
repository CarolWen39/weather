package com.example.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}


/**
 *  homework:
 *      25 minutes
 *      1. post endpoint => insert user info into database
 *          {
 *              "provider" : {
 *                  "first_name": xx,
 *                  "last_name": xx,
 *                  "middle_name":xx,
 *                  "dob":xx,
 *              }
 *          }
 *          first_name / last_name / dob : not null
 *
 *          get => display data
 *
 *      2. endpoint: /userinfo + post
 *      3. controller + service + repository
 *      4. 2 unit test cases
 *      5. database : H2
 */