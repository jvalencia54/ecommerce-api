package com.ecommerce.demo.login.service;

import com.ecommerce.demo.login.repository.UserRepository;
import com.ecommerce.demo.login.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String username, String rawPassword, String role) {
        String encodedPassword = passwordEncoder.encode(rawPassword);
        User user = new User(username, encodedPassword, role);
        return userRepository.save(user);
    }
}
