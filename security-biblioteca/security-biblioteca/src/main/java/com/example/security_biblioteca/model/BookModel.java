package com.example.security_biblioteca.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="libro")
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "titulo")
    private String title;

    @Column(nullable = false, name = "autor")
    private String author;
    private String gender;
    private int year;
    private String description;
    
    private int availableCopies;

    //Cardinalidad para la tabla users
    @ManyToMany(mappedBy = "books")
    @JsonIgnore
    @Builder.Default
    private Set<UserModel> user = new HashSet<>();
}
