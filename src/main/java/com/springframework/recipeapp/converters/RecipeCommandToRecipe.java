package com.springframework.recipeapp.converters;

import com.springframework.recipeapp.commands.RecipeCommand;
import com.springframework.recipeapp.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {
    private final CategoryCommandToCategory categoryConverter;
    private final IngredientCommandToIngredient ingredientConverter;
    private final NoteCommandToNote noteConverter;

    public RecipeCommandToRecipe(CategoryCommandToCategory categoryConverter,
                                 IngredientCommandToIngredient ingredientConverter,
                                 NoteCommandToNote noteConverter) {
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
        this.noteConverter = noteConverter;
    }

    @Nullable
    @Synchronized
    @Override
    public Recipe convert(RecipeCommand source) {
        if (source == null) {
            return null;
        }

        Recipe recipe = new Recipe();

        recipe.setId(source.getId());
        recipe.setCookTime(source.getCookTime());
        recipe.setPreparingTime(source.getPreparingTime());
        recipe.setDescription(source.getDescription());
        recipe.setDifficulty(source.getDifficulty());
        recipe.setDirections(source.getDirections());
        recipe.setServings(source.getServings());
        recipe.setSource(source.getSource());
        recipe.setUrl(source.getUrl());
        recipe.setNote(noteConverter.convert(source.getNote()));

        setCategories(source, recipe);
        setIngredients(source, recipe);
        return recipe;
    }

    private void setIngredients(RecipeCommand source, Recipe recipeCommand) {
        recipeCommand.getIngredients()
                .addAll(
                        source.getIngredients()
                                .stream()
                                .map(ingredientConverter::convert)
                                .toList()
                );
    }

    private void setCategories(RecipeCommand source, Recipe recipeCommand) {
        recipeCommand.getCategories()
                .addAll(
                        source.getCategories()
                                .stream()
                                .map(categoryConverter::convert)
                                .toList()
                );
    }
}
