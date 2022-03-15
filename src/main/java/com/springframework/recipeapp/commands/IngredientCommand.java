package com.springframework.recipeapp.commands;


import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IngredientCommand {
    Long id;
    BigDecimal amount;
    String description;
    UnitOfMeasureCommand unitOfMeasure;
}
