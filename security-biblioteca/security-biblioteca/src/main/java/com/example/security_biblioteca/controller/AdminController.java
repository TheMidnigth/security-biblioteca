package com.example.security_biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.security_biblioteca.model.BookModel;
import com.example.security_biblioteca.service.BookService;

@Controller
public class AdminController {

    @Autowired
    private BookService bookService;

    // Se obtienen los libros de la base de datos y se envían a la vista
    @GetMapping("/admin/home")
    public String Admin(Model model){

        List<BookModel> books = bookService.getBooks();
        model.addAttribute("books", books);

        return "Libros";
    }

    @PostMapping("/admin/home")
    public String addBook(@RequestParam String title, @RequestParam String author){
        bookService.addBook(title, author);
        return "redirect:/admin/home";
    }

    @PostMapping("/delete")
    public String deleteBook(@RequestParam Long id){
        bookService.deleteBook(id);
        return "redirect:/admin/home";
    }

    @PostMapping("/update")
    public String updateBook(@RequestParam Long id, @RequestParam String title, @RequestParam String author){
        bookService.updateBook(id, title, author);
        return "redirect:/admin/home";
    }

    @GetMapping("/admin/list")
    public String list(Model model){
        List<BookModel> books = bookService.getBooks();
        model.addAttribute("books", books);
        return "list";
    }
    
}
