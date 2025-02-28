package com.example.security_biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.security_biblioteca.model.BookModel;
import com.example.security_biblioteca.service.BookService;



@Controller
public class InicioController {

    @Autowired
    private BookService bookService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Se obtienen los libros de la base de datos y se envían a la vista
    @GetMapping("/")
    public String GestionLibros(Model model) {
        List<BookModel> books = bookService.getBooks();
        model.addAttribute("books", books);
        
        return "GestionLibros";
    }
}
