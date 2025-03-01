package com.example.security_biblioteca.model;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="usuario")
public class UserModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    //Cardinalidad para la tabla roles
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "roles",
        joinColumns = @JoinColumn(name = "user_id"))

    @Column(name = "rol")
    @Setter
    private Set<String> roles;

    //Cardinalidad para la tabla Usuarios
    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })

    //Tabla intermedia para la relación muchos a muchos
    @JoinTable(name = "Usuarios_books",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            foreignKey = @ForeignKey(name = "FK_user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseForeignKey = @ForeignKey(name = "FK_book_id"))
        @Builder.Default
        private Set<BookModel> books = new HashSet<>();
}