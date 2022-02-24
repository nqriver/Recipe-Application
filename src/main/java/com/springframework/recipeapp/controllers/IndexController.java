package com.springframework.recipeapp.controllers;

import com.springframework.recipeapp.domain.Category;
import com.springframework.recipeapp.domain.UnitOfMeasure;
import com.springframework.recipeapp.repositories.CategoryRepository;
import com.springframework.recipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage() {
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");

        System.out.println("Cat Id is : " + categoryOptional.get().getId());
        System.out.println("UoM Id is : " + unitOfMeasureOptional.get().getId());
        return "index";
    }
}
