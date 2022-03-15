package com.springframework.recipeapp.converters;

import com.springframework.recipeapp.domain.Ingredient;
import com.springframework.recipeapp.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientToIngredientCommandTest {
    public static final Long ID_VALUE = 1L;
    public static final BigDecimal amount = BigDecimal.valueOf(12);
    public static final String DESCRIPTION = "bean";
    public static final Long UOM_ID = 3L;


    private IngredientToIngredientCommand converter;


    @BeforeEach
    void setUp() {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    void testNullObjectConversion() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObjectConversion() {
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    void convert() {
        //given
        Ingredient ingredient = Ingredient.builder()
                .id(ID_VALUE)
                .description(DESCRIPTION)
                .unitOfMeasure(UnitOfMeasure.builder().id(UOM_ID).build())
                .build();

        //when
        var ingredientCommand = converter.convert(ingredient);

        //then
        assertNotNull(ingredientCommand);
        assertEquals(ID_VALUE, ingredientCommand.getId());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
        assertEquals(UOM_ID, ingredientCommand.getUnitOfMeasureCommand().getId());
    }
}