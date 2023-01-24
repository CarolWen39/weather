package com.example.provider.service;


import com.example.provider.exception.IllegalNullFieldException;
import com.example.provider.exception.ResourceNotFound;
import com.example.provider.pojo.entity.User;
import com.example.provider.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
