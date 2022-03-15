package com.springframework.recipeapp.commands;


import com.springframework.recipeapp.domain.Recipe;
import com.springframework.recipeapp.domain.UnitOfMeasure;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {
    Long id;
    BigDecimal amount;
    String description;
    UnitOfMeasureCommand unitOfMeasureCommand;
}
