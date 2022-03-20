package com.springframework.recipeapp.converters;

import com.springframework.recipeapp.commands.IngredientCommand;
import com.springframework.recipeapp.domain.Ingredient;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    private final UnitOfMeasureToUnitOfMeasureCommand uomToUomcConverter;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uomToUomcConverter) {
        this.uomToUomcConverter = uomToUomcConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient source) {
        log.debug("I am in Ingredient converter");
        if (source == null) {
            return null;
        }

        IngredientCommand ingredientCommand = new IngredientCommand();
        if (source.getRecipe() != null) {
            ingredientCommand.setRecipeId(source.getRecipe().getId());
        }
        ingredientCommand.setId(source.getId());
        ingredientCommand.setDescription(source.getDescription());
        ingredientCommand.setAmount(source.getAmount());
        if (source.getUnitOfMeasure() != null) {
            ingredientCommand.setUnitOfMeasure(uomToUomcConverter.convert(source.getUnitOfMeasure()));
        }
        log.debug("Successfully converted from Ingredient to IngredientCommand [ID: " + source.getId() + "]");
        return ingredientCommand;
    }
}
