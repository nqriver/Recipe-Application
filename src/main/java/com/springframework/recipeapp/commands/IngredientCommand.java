package com.springframework.recipeapp.commands;


import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IngredientCommand {
    private Long id;
    private Long recipeId;
    private BigDecimal amount;
    private String description;
    private UnitOfMeasureCommand unitOfMeasure;
}
