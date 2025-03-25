package org.example.antraproject1.service;

import org.example.antraproject1.pojo.Role;
import org.example.antraproject1.pojo.User;
import org.example.antraproject1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(String username, String rawPassword, Role role) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("User already exists!");
        }
        String encodedPassword = passwordEncoder.encode(rawPassword);

        User user = new User(username, encodedPassword, role);
        return userRepository.save(user);
    }
}
