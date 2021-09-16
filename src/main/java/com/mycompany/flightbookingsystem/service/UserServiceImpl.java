package com.mycompany.flightbookingsystem.service;

import com.mycompany.flightbookingsystem.model.User;
import com.mycompany.flightbookingsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        userRepository.save(user);
        return user;
    }
}
