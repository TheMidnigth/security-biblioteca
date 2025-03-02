package com.example.security_biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.security_biblioteca.model.BookModel;
import com.example.security_biblioteca.service.BookService;

@Controller
public class AdminController {

    @Autowired
    private BookService bookService;

    @GetMapping("/homeAdmin")
    public String gestionLibros() {
        return "GestionLibros";
    }

    @GetMapping("/listaLibros")
    public String gestionPrestamos(Model model) {
        model.addAttribute("book", bookService.listarLibros());
        return "ListaLibros";
    }

    @GetMapping("/registrarLibros")
    public String registrarLibros(Model model) {
        model.addAttribute("books", new BookModel());
        return "RegistrarLibros";
    }

    @PostMapping("/guardarLibros")
    public String guardarLibro(@Validated @ModelAttribute("books") BookModel bookModel,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            return "RegistrarLibros";
        }

        bookService.guardarLibros(bookModel);
        return "redirect:/listaLibros";
    }

    @GetMapping("/editarLibros/{id}")
    public String editarLibros(@PathVariable Long id, Model model) {
        model.addAttribute("libros", bookService.buscarLibrosPorId(id));
        return "EditarLibros";
    }

    @PostMapping("/actualizarLibros/{id}")
    public String actualizarLibros(@PathVariable Long id, @ModelAttribute BookModel bookModel) {
        BookModel libroExistente = bookService.buscarLibrosPorId(id);
        if (libroExistente != null) {
            bookModel.setId(id);
            bookService.actualizarLibro(bookModel);
        }
        return "redirect:/listaLibros";
    }

    @GetMapping("/eliminarLibros/{id}")
    public String eliminarLibros(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            bookService.eliminarLibros(id);
            redirectAttributes.addFlashAttribute("mensaje", "Libro eliminado correctamente");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/listaLibros";
    }

}
