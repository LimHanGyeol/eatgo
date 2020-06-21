package me.hangyeol.eatgo.interfaces;

import me.hangyeol.eatgo.application.CategoryService;
import me.hangyeol.eatgo.application.RegionService;
import me.hangyeol.eatgo.domain.Category;
import me.hangyeol.eatgo.domain.Region;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
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
        categories.add(Category.builder().name("Korean Food").build());
        return categories;
    }

    @PostMapping("/categories")
    public ResponseEntity<?> create(@RequestBody Category resource) throws URISyntaxException {
        String name = resource.getName();
        Category category = categoryService.addCategory(name);
        URI location = new URI("/categories/" + category.getId());
        return ResponseEntity.created(location).body("{}");
    }




}
