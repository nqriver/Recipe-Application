package com.springframework.recipeapp.services;

import com.springframework.recipeapp.commands.IngredientCommand;
import com.springframework.recipeapp.converters.IngredientToIngredientCommand;
import com.springframework.recipeapp.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final RecipeRepository recipeRepository;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand, RecipeRepository recipeRepository) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        return recipeRepository.findById(recipeId)
                .orElseThrow(() -> new IllegalArgumentException("Given recipe does not exist. Recipe ID: " + recipeId))
                .getIngredients()
                .stream()
                .filter(ingredient -> ingredientId.equals(ingredient.getId()))
                .map(ingredientToIngredientCommand::convert)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Given ingredient does not exist. Ingredient ID: " + ingredientId));
    }
}
