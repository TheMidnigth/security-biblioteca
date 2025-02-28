package com.example.security_biblioteca.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.security_biblioteca.model.BookModel;
import com.example.security_biblioteca.service.BookService;
import com.example.security_biblioteca.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @GetMapping("/user/home")
    public String User(Model model) {
        List<BookModel> books = bookService.getBooks();
        model.addAttribute("books", books);
        return "user";
    }

    @PostMapping("/register")
    public String RegisterBook(@RequestParam("bookid") Long bookid) { {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String user = authentication.getName();
        userService.registerbook(user, bookid);
        return "redirect:/user/home";
    }
}

}
