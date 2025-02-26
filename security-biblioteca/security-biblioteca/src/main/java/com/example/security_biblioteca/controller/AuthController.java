package com.example.security_biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.security_biblioteca.model.UserModel;
import com.example.security_biblioteca.service.UserService;

import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.security.crypto.password.PasswordEncoder;


@Controller
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
public String login(@RequestParam String username, @RequestParam String password, Model model, HttpServletRequest request) {
    UserModel user = userService.findByUsername(username);
    if (user != null) {
        System.out.println("Stored password: " + user.getPassword());
        System.out.println("Entered password: " + password);
        boolean isPasswordMatch = passwordEncoder.matches(password, user.getPassword());
        System.out.println("Password match: " + isPasswordMatch);
        if (isPasswordMatch) {
            model.addAttribute("user", user);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            return "redirect:/user/loans";
        }
    }
    return "login.html";
}
    

    @GetMapping("/register")
    public String register() {
        return "registro";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserModel user) {
        userService.registerUser (user);
        return "redirect:/login";
    }

    @GetMapping("/check-password")
public String checkPassword(@RequestParam String username, @RequestParam String password, Model model) {
    boolean isPasswordCorrect = userService.checkPassword(username, password);
    model.addAttribute("isPasswordCorrect", isPasswordCorrect);
    return "check-password-result.html";
}

}
