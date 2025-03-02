package com.example.security_biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.security_biblioteca.model.LoanModel;
import com.example.security_biblioteca.service.BookService;
import com.example.security_biblioteca.service.LoanService;

@Controller
public class UserController {

    @Autowired
    private LoanService loanService;

    @Autowired
    private BookService bookService;

    @GetMapping("/homeUser")
    public String homeUser() {
        return "HomeUser";
    }

    @GetMapping("/ListaPrestamos")
    public String listarLibrosUsuario(Model model) {
        model.addAttribute("book", bookService.listarLibros());
        return "PrestamosLibros";
    }

    @PostMapping("/prestarLibros")
    public String guardarLibro(@ModelAttribute LoanModel loanmodel, Model model) {
        try {
            loanService.guardarLibro(loanmodel); 
            return listarLibrosUsuario(model); 
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

}
