package com.springframework.recipeapp.converters;

import com.springframework.recipeapp.commands.CategoryCommand;
import com.springframework.recipeapp.commands.IngredientCommand;
import com.springframework.recipeapp.commands.NoteCommand;
import com.springframework.recipeapp.commands.RecipeCommand;
import com.springframework.recipeapp.domain.Difficulty;
import com.springframework.recipeapp.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RecipeCommandToRecipeTest {

    public static final Long RECIPE_ID = 1L;
    public static final Integer COOK_TIME = 5;
    public static final Integer PREP_TIME = 7;
    public static final String DESCRIPTION = "My Recipe";
    public static final String DIRECTIONS = "Directions";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final Integer SERVINGS = 3;
    public static final String SOURCE = "Source";
    public static final String URL = "Some URL";
    public static final Long CAT_ID_1 = 1L;
    public static final Long CAT_ID_2 = 2L;
    public static final Long INGREDIENT_ID_1 = 3L;
    public static final Long INGREDIENT_ID_2 = 4L;
    public static final Long NOTES_ID = 9L;


    private RecipeCommandToRecipe converter;

    @BeforeEach
    void setUp() {
        converter = new RecipeCommandToRecipe(
                new CategoryCommandToCategory(),
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new NoteCommandToNote()
        );
    }

    @Test
    void testNullObjectConversion() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new RecipeCommand()));
    }


    @Test
    void convert() {
        //given
        RecipeCommand recipeCommand = RecipeCommand
                .builder()
                .id(RECIPE_ID)
                .cookTime(COOK_TIME)
                .preparingTime(PREP_TIME)
                .description(DESCRIPTION)
                .directions(DIRECTIONS)
                .difficulty(DIFFICULTY)
                .servings(SERVINGS)
                .source(SOURCE)
                .url(URL)
                .notes(NoteCommand.builder().id(NOTES_ID).build())
                .ingredients(
                        Set.of(
                                IngredientCommand.builder().id(INGREDIENT_ID_1).build(),
                                IngredientCommand.builder().id(INGREDIENT_ID_2).build()
                        ))
                .categories(
                        Set.of(
                                CategoryCommand.builder().id(CAT_ID_1).build(),
                                CategoryCommand.builder().id(CAT_ID_2).build()
                        ))
                .build();

        //when
        Recipe recipe = converter.convert(recipeCommand);

        //then
        assertNotNull(recipe);
        assertEquals(RECIPE_ID, recipe.getId());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(PREP_TIME, recipe.getPreparingTime());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(NOTES_ID, recipe.getNote().getId());
        assertEquals(2, recipe.getCategories().size());
        assertEquals(2, recipe.getIngredients().size());
    }
}