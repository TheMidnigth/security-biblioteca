package com.example.security_biblioteca.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.security_biblioteca.model.LoanModel;
import com.example.security_biblioteca.model.UserModel;
import com.example.security_biblioteca.service.LoanService;
import com.example.security_biblioteca.service.UserService;

@Controller
@RequestMapping("/user")
public class LoanController {
    @Autowired
    private LoanService loanService;

    @Autowired
    private UserService userService;

    @GetMapping("/loans")
    public String viewLoans(Model model, Principal principal) {
        UserModel user = userService.findByUsername(principal.getName());
        model.addAttribute("loans", loanService.getActiveLoansByUser(user));
        return "user_dashboard.html";
    }

    @PostMapping("/loans/request")
    public String requestLoan(@ModelAttribute LoanModel loan) {
        loanService.createLoan(loan);
        return "redirect:/user/loans";
    }

}
