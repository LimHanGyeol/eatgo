package me.hangyeol.eatgo.category.controller;

import me.hangyeol.eatgo.category.Category;
import me.hangyeol.eatgo.category.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
class CategoryControllerTests {

    private Category category;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        category = Category.builder()
                .name("Korean Food")
                .build();
    }

    @Test
    @DisplayName("/categories api 호출로 전체 음식 분야 카테고리 가져오기")
    public void list() throws Exception {
        List<Category> categories = new ArrayList<>();
        categories.add(category);

        given(categoryService.getCategories())
                .willReturn(categories);

        mvc.perform(get("/categories"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Korean Food")));
    }

    @Test
    @DisplayName("/categories api 호출로 음식 분야 카테고리 추가")
    public void create() throws Exception {
        given(categoryService.addCategory("Korean Food"))
                .willReturn(category);

        mvc.perform(post("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Korean Food\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().string("{}"));

        verify(categoryService).addCategory("Korean Food");
    }

}
