package com.example.security_biblioteca.Util;

import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.security_biblioteca.model.BookModel;
import com.example.security_biblioteca.model.UserModel;
import com.example.security_biblioteca.Repository.BookRepository;
import com.example.security_biblioteca.Repository.UserRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public DataLoader(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) { // Solo insertar si la tabla está vacía
            List<UserModel> users = List.of(
                UserModel.builder()
                    .username("admin")
                    .password("$2a$10$zOipQ7UVtV2hwlbqttFEUekmHeCyXjCrwJjguKd5oox3qJe7aJMzq") // admin123
                    .roles(Set.of("ADMIN"))
                    .build(),
    
                UserModel.builder()
                    .username("user")
                    .password("$2a$10$xiBL0aOMLIv3Gk1crlzYguIMvAALGq6.ydsqg5K0GyjTnjukvm9Ci") // user123
                    .roles(Set.of("USUARIO"))
                    .build(),
    
                UserModel.builder()
                    .username("user2")
                    .password("$2a$10$W0kzU/wjOfQfWFaJiunHc.LVayAF4jXhLvL8bLV74KyMJYlUfRiRq") // user321
                    .roles(Set.of("USUARIO"))
                    .build(),
                
                    UserModel.builder()
                    .username("keiner")
                    .password("$2a$10$XOm5GgF9JwcdtdkYvMiNreXcK3DKIFOND.sR7eTuSAkLwBpY3Bh1q") // keiner123
                    .roles(Set.of("USUARIO"))
                    .build()
            );
            userRepository.saveAll(users);
        } else {
            System.out.println("Los usuarios ya existen, no se insertarán nuevamente.");
        }
    
        if (bookRepository.count() == 0) { // Solo insertar si la tabla está vacía
            List<BookModel> books = List.of(
                BookModel.builder()
                    .title("Cien Años De Soledad")
                    .author("Gabriel Garcia Marquez")
                    .genre("Realismo Magico")
                    .publicationYear(1967)
                    .pageCount(400)
                    .stock(20)
                    .build(),
    
                BookModel.builder()
                    .title("El principito")
                    .author("Antoine de Saint-Exupéry")
                    .genre("Fábula")
                    .publicationYear(1943)
                    .pageCount(200)
                    .stock(10)
                    .build(),
    
                BookModel.builder()
                    .title("El Tunel")
                    .author("Ernesto Sabato")
                    .genre("Novela")
                    .publicationYear(1948)
                    .pageCount(300)
                    .stock(30)
                    .build()
            );
            bookRepository.saveAll(books);
        } else {
            System.out.println("Los libros ya existen, no se insertarán nuevamente.");
        }
    }
}