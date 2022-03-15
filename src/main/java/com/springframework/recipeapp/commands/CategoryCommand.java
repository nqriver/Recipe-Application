package com.springframework.recipeapp.commands;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryCommand {
    Long id;
    private String description;
}
