package me.hangyeol.eatgo.category.service;

import me.hangyeol.eatgo.category.Category;
import me.hangyeol.eatgo.category.CategoryRepository;
import me.hangyeol.eatgo.category.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class CategoryServiceTests {

    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryService(categoryRepository);
    }

    private List<Category> initMockCategories() {
        List<Category> mockCategories = new ArrayList<>();
        mockCategories.add(Category.builder().name("Korean Food").build());
        return mockCategories;
    }

    @Test
    @DisplayName("전체 음식 분야 카테고리 가져오기")
    public void getCategories() {
        List<Category> mockCategories = initMockCategories();

        given(categoryRepository.findAll()).willReturn(mockCategories);

        List<Category> categories = categoryService.getCategories();

        Category category = categories.get(0);
        assertThat(category.getName(), is("Korean Food"));
    }

}
