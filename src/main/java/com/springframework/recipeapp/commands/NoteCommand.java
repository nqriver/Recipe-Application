package com.springframework.recipeapp.commands;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoteCommand {
    Long id;
    String content;
}
