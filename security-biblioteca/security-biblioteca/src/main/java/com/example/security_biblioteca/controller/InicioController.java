package com.example.security_biblioteca.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class InicioController {

    @GetMapping("/login")
    public String login() {
        System.out.println("Mostrando página de login...");
        return "login";
    }

    @GetMapping("/registro")
    public String registro() {
        System.out.println("Mostrando página de registro...");
        return "registro";
    }

    @GetMapping("/ListaUsuarioPrestamos")
    public String listaUsuarioPrestamos() {
        return "ListaUsuarioPrestamos";
    }

}
