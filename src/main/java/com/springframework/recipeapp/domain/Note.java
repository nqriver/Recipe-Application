package com.springframework.recipeapp.domain;

import lombok.*;

import javax.persistence.*;

@Data
@ToString(exclude = "recipe")
@EqualsAndHashCode(exclude = "recipe")
@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne()
    private Recipe recipe;

    @Lob
    private String content;

}
