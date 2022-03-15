package com.springframework.recipeapp.commands;


import com.springframework.recipeapp.domain.Recipe;
import com.springframework.recipeapp.domain.UnitOfMeasure;
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
    UnitOfMeasureCommand unitOfMeasureCommand;
}
