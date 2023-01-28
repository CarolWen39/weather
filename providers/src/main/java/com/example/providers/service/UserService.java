package com.example.providers.service;


import com.example.providers.pojo.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getAllUsers();
    Long insertUser(User u);
}
