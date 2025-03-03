package com.example.security_biblioteca.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.security_biblioteca.repository.UserRepository;
import com.example.security_biblioteca.model.LoanModel;
import com.example.security_biblioteca.model.UserModel;
import com.example.security_biblioteca.service.BookService;
import com.example.security_biblioteca.service.LoanService;

@Controller
public class UserController {

    @Autowired
    private LoanService loanService;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserRepository userRepository;

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
public String guardarLibro(@ModelAttribute LoanModel loanmodel, Model model, Principal principal) {
    try {
        // Obtener el usuario autenticado desde Spring Security
        String username = principal.getName();
        Optional<UserModel> optionalUser = userRepository.findByUsername(username);

        // Verificar si el usuario existe
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("El usuario no está registrado en el sistema.");
        }

        UserModel user = optionalUser.get();

        // Verificar si el libro no es nulo
        if (loanmodel.getBook() == null || loanmodel.getBook().getId() == null) {
            throw new RuntimeException("Debe seleccionar un libro válido para prestar.");
        }

        // Asignar el usuario al préstamo
        loanmodel.setUser(user);

        // Guardar el préstamo
        loanService.guardarLibro(loanmodel);
        return listarLibrosUsuario(model);
    } catch (RuntimeException e) {
        model.addAttribute("error", e.getMessage());
        return "error";
    }
}

}
