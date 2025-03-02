package com.example.security_biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.security_biblioteca.Repository.BookRepository;

import com.example.security_biblioteca.model.BookModel;


@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    /** Método para listar todos los libros */
    public List<BookModel> listarLibros() {
        return bookRepository.findAll();
    }


    /** Método para guardar un libro con validaciones */
    public BookModel guardarLibros(BookModel bookModel) {
        if (bookModel.getTitle() == null || bookModel.getTitle().isEmpty()) {
            throw new IllegalArgumentException("El título del libro no puede estar vacío");
        }
        if (bookModel.getAuthor() == null || bookModel.getAuthor().isEmpty()) {
            throw new IllegalArgumentException("El autor del libro no puede estar vacío");
        }
        return bookRepository.save(bookModel);
    }

    /** Método para buscar un libro por ID con manejo de errores */
    public BookModel buscarLibrosPorId(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado con ID: " + id));
    }

    /** Método para actualizar un libro, verificando que exista */
    public BookModel actualizarLibro(BookModel bookModel) {
        if (!bookRepository.existsById(bookModel.getId())) {
            throw new RuntimeException("No se puede actualizar. Libro no encontrado con ID: " + bookModel.getId());
        }
        return bookRepository.save(bookModel);
    }

    /** Método para eliminar un libro verificando su existencia */
    public void eliminarLibros(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. Libro no encontrado con ID: " + id);
        }
        bookRepository.deleteById(id);
    }

}
