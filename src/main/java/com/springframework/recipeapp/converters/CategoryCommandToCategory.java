package com.springframework.recipeapp.converters;

import com.springframework.recipeapp.commands.CategoryCommand;
import com.springframework.recipeapp.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {

    @Override
    @Synchronized
    @Nullable
    public Category convert(CategoryCommand source) {
        if (Objects.isNull(source)) {
            return null;
        }

        final Category category = new Category();
        category.setId(source.getId());
        category.setDescription(source.getDescription());
        return category;
    }

}
