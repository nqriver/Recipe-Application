package com.springframework.recipeapp.domain;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(exclude="recipe")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private BigDecimal amount;

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure unitOfMeasure;

    @ManyToOne
    @ToString.Exclude
    private Recipe recipe;

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure unitOfMeasure) {
        this.description = description;
        this.amount = amount;
        this.unitOfMeasure = unitOfMeasure;
    }
}
