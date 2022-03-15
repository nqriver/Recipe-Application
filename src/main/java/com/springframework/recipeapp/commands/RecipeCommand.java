package com.springframework.recipeapp.commands;


import com.springframework.recipeapp.domain.Difficulty;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeCommand {
    private Long id;
    private String description;
    private Integer cookTime;
    private Integer preparingTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;


    @Builder.Default
    private Set<IngredientCommand> ingredients = new HashSet<>();

    private Difficulty difficulty;
    private NoteCommand note;

    @Builder.Default
    private Set<CategoryCommand> categories = new HashSet<>();
}
