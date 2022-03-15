package com.springframework.recipeapp.converters;

import com.springframework.recipeapp.commands.RecipeCommand;
import com.springframework.recipeapp.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {
    private final CategoryToCategoryCommand categoryConverter;
    private final IngredientToIngredientCommand ingredientConverter;
    private final NoteToNoteCommand noteConverter;

    public RecipeToRecipeCommand(CategoryToCategoryCommand categoryConverter,
                                 IngredientToIngredientCommand ingredientConverter,
                                 NoteToNoteCommand noteConverter) {
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
        this.noteConverter = noteConverter;
    }

    @Nullable
    @Synchronized
    @Override
    public RecipeCommand convert(Recipe source) {
        if (source == null) {
            return null;
        }

        RecipeCommand recipeCommand = new RecipeCommand();

        recipeCommand.setId(source.getId());
        recipeCommand.setCookTime(source.getCookTime());
        recipeCommand.setPreparingTime(source.getPreparingTime());
        recipeCommand.setDescription(source.getDescription());
        recipeCommand.setDifficulty(source.getDifficulty());
        recipeCommand.setDirections(source.getDirections());
        recipeCommand.setServings(source.getServings());
        recipeCommand.setSource(source.getSource());
        recipeCommand.setUrl(source.getUrl());
        recipeCommand.setNotes(noteConverter.convert(source.getNote()));

        setCategories(source, recipeCommand);
        setIngredients(source, recipeCommand);
        return recipeCommand;
    }

    private void setIngredients(Recipe source, RecipeCommand recipeCommand) {
        recipeCommand.getIngredients()
                .addAll(
                        source.getIngredients()
                                .stream()
                                .map(ingredientConverter::convert)
                                .toList()
                );
    }

    private void setCategories(Recipe source, RecipeCommand recipeCommand) {
        recipeCommand.getCategories()
                .addAll(
                        source.getCategories()
                                .stream()
                                .map(categoryConverter::convert)
                                .toList()
                );
    }
}
