package com.example.security_biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.security_biblioteca.model.BookModel;
import com.example.security_biblioteca.model.UserModel;
import com.example.security_biblioteca.repository.BookRepository;
import com.example.security_biblioteca.repository.UserRepository;


@Service
public class UserService {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private BookRepository bookRepository;

        public void registerbook(String username,Long bookId) {
            UserModel user = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado" + username));

            BookModel books = bookRepository.findById(bookId)
            .orElseThrow(() -> new RuntimeException("Libro no encontrado" + bookId));
            
            user.getBooks().add(books);
            userRepository.save(user);

        }

}
