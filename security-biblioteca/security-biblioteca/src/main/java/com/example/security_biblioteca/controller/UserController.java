package com.example.security_biblioteca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/")
public class UserController {

    @GetMapping("/index")
    public String login() {
        return "index.html";
    }

}
