package com.example.security_biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.security_biblioteca.model.UserModel;
import com.example.security_biblioteca.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /** Guardar usuario */
    public UserModel guardarUsuario(UserModel user) {
        return userRepository.save(user);
    }

}
