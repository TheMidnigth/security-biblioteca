package com.example.security_biblioteca.config;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.security_biblioteca.model.UserModel;
import com.example.security_biblioteca.Repository.UserRepository;

@Service
public class CustomerUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomerUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Intentando autenticar al usuario: ".concat(username));

        UserModel user = userRepository.findByUsername(username).orElseThrow(() -> {
            System.out.println("Usuario no encontrado en la base de datos ".concat(username));
            return new UsernameNotFoundException("Usuario no encontrado");
        });
        System.out.println("Usuario encontrado ".concat(user.getUsername()));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                        .collect(Collectors.toSet()));
    }

}
