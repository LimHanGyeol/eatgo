package me.hangyeol.eatgo.category.controller;

import me.hangyeol.eatgo.category.service.CategoryService;
import me.hangyeol.eatgo.category.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public List<Category> list() {
        List<Category> categories = categoryService.getCategories();
        categories.add(Category.builder()
                .name("Korean Food")
                .build());
        return categories;
    }
}
