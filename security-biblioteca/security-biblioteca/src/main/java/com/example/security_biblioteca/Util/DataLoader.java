package com.example.security_biblioteca.Util;

import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.security_biblioteca.model.BookModel;
import com.example.security_biblioteca.model.UserModel;
import com.example.security_biblioteca.repository.BookRepository;
import com.example.security_biblioteca.repository.UserRepository;

@Component
public class DataLoader {

    public CommandLineRunner loadData(UserRepository userRepository, BookRepository bookRepository) {
        return args -> {
            List<BookModel> books =  List.of(
                new BookModel().builder()
                    .title("El principito")
                    .author("Antoine de Saint-Exupéry")
                    .gender("Fábula")
                    .year(1943)
                    .description("El principito es una obra literaria del escritor y aviador francés Antoine de Saint-Exupéry. Es un cuento poético y filosófico que posee un trasfondo de crítica social. El autor se basó en su experiencia como piloto para escribir la historia.")
                    .build(),

                new BookModel().builder()
                    .title("El túnel")
                    .author("Ernesto Sábato")
                    .year(1948)
                    .gender("Novela")
                    .description("El túnel es una novela escrita por el escritor argentino Ernesto Sábato. Publicada en 1948, es considerada una de las obras más importantes de la literatura argentina y latinoamericana del siglo XX.")
                    .build(),
                
                new BookModel().builder()
                    .title("Cien años de soledad")
                    .author("Gabriel García Márquez")
                    .year(1967)
                    .gender("Realismo mágico")
                    .description("Cien años de soledad es una novela del escritor colombiano Gabriel García Márquez, ganador del Premio Nobel de Literatura en 1982. Es considerada una obra maestra de la literatura hispanoamericana y universal.")
                    .build()
            );
        bookRepository.saveAll(books);

        List<UserModel> users = List.of(
            new UserModel().builder()
                .username("admin")
                .password("$2a$10$zOipQ7UVtV2hwlbqttFEUekmHeCyXjCrwJjguKd5oox3qJe7aJMzq") // admin123
                .roles(Set.of("ADMIN"))
                .build(),
                
            new UserModel().builder()
                .username("user")
                .password("$2a$10$xiBL0aOMLIv3Gk1crlzYguIMvAALGq6.ydsqg5K0GyjTnjukvm9Ci") // user123
                .roles(Set.of("USER"))
                .build(),

            new UserModel().builder()
                .username("user2")
                .password("$2a$10$W0kzU/wjOfQfWFaJiunHc.LVayAF4jXhLvL8bLV74KyMJYlUfRiRq")// user321
                .roles(Set.of("USER"))
                .build()
        );
        userRepository.saveAll(users);
        };
    }
}
