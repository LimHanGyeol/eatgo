package me.hangyeol.eatgo.review;

import me.hangyeol.eatgo.review.Review;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReviewTest {

    @Test
    @DisplayName("리뷰 객체 생성")
    void create() {
        Review review = Review.builder()
                .id(1L)
                .name("LimHanGyeol")
                .score(5)
                .description("만족합니다.")
                .restaurantId(1004L)
                .build();

        assertThat(review.getId()).isEqualTo(1L);
        assertThat(review.getName()).isEqualTo("LimHanGyeol");
        assertThat(review.getScore()).isEqualTo(5);
        assertThat(review.getDescription()).isEqualTo("만족합니다.");
        assertThat(review.getRestaurantId()).isEqualTo(1004L);
    }
}
