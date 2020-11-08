package me.hangyeol.eatgo.category;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CategoryTests {

    @Test
    @DisplayName("카테고리 객체 생성")
    public void create() {
        Category category = Category.builder()
                .name("Korean Food")
                .build();
        assertThat(category.getName()).isEqualTo("Korean Food");
    }

}
