package com.springframework.recipeapp.services;

import com.springframework.recipeapp.commands.IngredientCommand;
import com.springframework.recipeapp.converters.IngredientToIngredientCommand;
import com.springframework.recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.springframework.recipeapp.domain.Ingredient;
import com.springframework.recipeapp.domain.Recipe;
import com.springframework.recipeapp.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IngredientServiceImplTest {

    public static Long RECIPE_ID = 1L;

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private IngredientToIngredientCommand ingredientConverter;

    @InjectMocks
    private IngredientServiceImpl ingredientService;


    @BeforeEach
    void setUp() {
    }

    @Test
    void findByRecipeIdAndIngredientId() {
        //given
        Recipe someRecipe = Recipe.builder().id(RECIPE_ID).build();

        Ingredient ing1 = Ingredient.builder().id(1L).recipe(someRecipe).build();
        Ingredient ing2 = Ingredient.builder().id(2L).recipe(someRecipe).build();
        Ingredient ing3 = Ingredient.builder().id(3L).recipe(someRecipe).build();

        someRecipe.getIngredients().addAll(Set.of(ing1, ing2, ing3));

        var recipeOptional = Optional.of(someRecipe);

        //when
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        //then
        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(RECIPE_ID, 3L);

        assertEquals(Long.valueOf(3L), ingredientCommand.getId());
        assertEquals(RECIPE_ID, ingredientCommand.getRecipeId());
        verify(recipeRepository, times(1)).findById(anyLong());
    }
}