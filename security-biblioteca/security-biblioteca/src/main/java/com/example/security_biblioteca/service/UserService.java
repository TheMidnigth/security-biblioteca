package com.example.security_biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.security_biblioteca.model.UserModel;
import com.example.security_biblioteca.repository.UserRepository;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    
    private PasswordEncoder passwordEncoder;



    public void registerUser(UserModel user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(user);
}

    
    public UserModel findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public boolean checkPassword(String username, String rawPassword) {
        UserModel user = findByUsername(username);
        if (user != null) {
            return passwordEncoder.matches(rawPassword, user.getPassword());
        }
        return false;
    }

}
