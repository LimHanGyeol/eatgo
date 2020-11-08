package me.hangyeol.eatgo.category.service;

import me.hangyeol.eatgo.category.Category;
import me.hangyeol.eatgo.category.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class CategoryServiceTests {

    @SpyBean
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
        mockCategories.add(Category.builder()
                .name("Korean Food")
                .build());
        return mockCategories;
    }

    @Test
    @DisplayName("전체 카테고리 정보 가져오기")
    public void getCategories() {
        List<Category> mockCategories = initMockCategories();

        given(categoryRepository.findAll()).willReturn(mockCategories);

        List<Category> categories = categoryService.getCategories();

        Category category = categories.get(0);
        assertThat(category.getName()).isEqualTo("Korean Food");
    }

    @Test
    @DisplayName("카테고리 추가")
    public void addCategory() {
        Category category = categoryService.addCategory("Korean Food");

        verify(categoryRepository).save(any());

        assertThat(category.getName()).isEqualTo("Korean Food");
    }

}
