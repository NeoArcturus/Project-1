package com.SpringBootWebApplication.FullStack.service;

import com.SpringBootWebApplication.FullStack.entity.User;
import com.SpringBootWebApplication.FullStack.exception.UserNotFoundException;
import com.SpringBootWebApplication.FullStack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User setUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User getUserById(Long userId) throws UserNotFoundException {
        User user = userRepository.findUserById(userId);
        if (user == null)
            throw new UserNotFoundException("User does not exist", new Exception());
        return userRepository.findUserById(userId);
    }
}
