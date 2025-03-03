package com.example.security_biblioteca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.security_biblioteca.Repository.BookRepository;
import com.example.security_biblioteca.Repository.LoanRepository;
import com.example.security_biblioteca.model.BookModel;
import com.example.security_biblioteca.model.LoanModel;

@Service
public class LoanService {
    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private BookRepository bookRepository;

    /* Metodo para listar los prestamos del usuario */
    public List<LoanModel> listarPrestamos(){
        return loanRepository.findAll();
    }

    /* Método para buscar un préstamo por ID */
    public Optional<LoanModel> buscarPrestamoPorId(Long id) {
        return loanRepository.findById(id);
    }

    /* Método para listar préstamos de un usuario específico */
    public List<LoanModel> listarPrestamosPorUsuario(Long userId) {
        return loanRepository.findByUser_Id(userId);
    }

    /* Método para guardar un préstamo si el libro está disponible */
    public LoanModel guardarLibro(LoanModel loanmodel) {
        if (loanmodel.getUser() == null || loanmodel.getUser().getId() == null) {
            throw new RuntimeException("El usuario no está definido en el préstamo.");
        }
    
        Optional<BookModel> libro = bookRepository.findById(loanmodel.getBook().getId());
    
        if (libro.isPresent() && libro.get().getStock() > 0) {
            // Obtener los préstamos activos del usuario
            List<LoanModel> prestamosExistentes = loanRepository.findByUser_Id(loanmodel.getUser().getId());
    
            // Verificar si el usuario ya tiene este mismo libro prestado
            boolean yaTieneEsteLibro = prestamosExistentes.stream()
                .anyMatch(prestamo -> prestamo.getBook().getId().equals(loanmodel.getBook().getId()));
    
            if (yaTieneEsteLibro) {
                throw new RuntimeException("El usuario ya tiene este libro prestado y no puede solicitar otro igual.");
            }
    
            // Reducir stock del libro
            BookModel book = libro.get();
            book.setStock(book.getStock() - 1);
            bookRepository.save(book);
    
            return loanRepository.save(loanmodel);
        } else {
            throw new RuntimeException("El libro no está disponible");
        }
    }
    

    
}


