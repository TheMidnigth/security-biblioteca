package com.example.security_biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.security_biblioteca.model.BookModel;
import com.example.security_biblioteca.model.UserModel;
import com.example.security_biblioteca.repository.BookRepository;
import com.example.security_biblioteca.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;



@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public List<BookModel> getBooks() {
        return bookRepository.findAll();
    }

    public BookModel addBook (String title, String author) {
        BookModel newBook = BookModel.builder()
                .title(title)
                .author(author)
                .build();
        return bookRepository.save(newBook);
    }

    public BookModel updateBook(Long id, String title, String author) {
        BookModel book = bookRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("libro aviles id: " + id));
        book.setTitle(title);
        book.setAuthor(author);
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        BookModel book = bookRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("El libro con id: " + id + " no existe o ya fue eliminado"));

        for (UserModel user : book.getUser()) {
            user.getBooks().remove(book);
            userRepository.save(user);
        }

        bookRepository.delete(book);
    }

}
