package com.example.providers.service;


import com.example.providers.exception.IllegalNullFieldException;
import com.example.providers.exception.ResourceNotFound;
import com.example.providers.pojo.entity.User;
import com.example.providers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        if(users == null)
            throw new ResourceNotFound("Users not found!");
        return users;
    }

    @Override
    public List<User> getUserById(Long id) {
        List<User> user = new ArrayList<>();
        if(this.userRepository.findById(id).get() == null)
            throw new ResourceNotFound("User " + id + " not exists!");
        user.add(this.userRepository.findById(id).get());
        return user;
    }

    @Override
    @Transactional
    public Long insertUser(User u) {
        if(u.getFirst_name() == null)
            throw  new IllegalNullFieldException("First Name requires Not Null");
        else if(u.getLast_name() == null)
            throw new IllegalNullFieldException("Last Name requires Not Null");
        else if(u.getDob() == null)
            throw new IllegalNullFieldException("DOB requires Not Null");

        this.userRepository.save(u);
        return u.getId();
    }
}
