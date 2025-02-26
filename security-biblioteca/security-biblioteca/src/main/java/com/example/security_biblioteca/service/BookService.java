package com.example.security_biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.security_biblioteca.model.BookModel;
import com.example.security_biblioteca.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public BookModel addBook(BookModel book) {
        return bookRepository.save(book);
    }

    public List<BookModel> getAllBooks() {
        return bookRepository.findAll();
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

}
