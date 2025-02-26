package com.example.security_biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.security_biblioteca.model.BookModel;
import com.example.security_biblioteca.service.BookService;

@Controller
@RequestMapping("/admin")
public class BookController {

     @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "admin_dashboard";
    }

    @PostMapping("/books/add")
    public String addBook(@ModelAttribute BookModel book) {
        bookService.addBook(book);
        return "redirect:/admin/books";
    }

    @PostMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/admin/books";
    }
}
