package me.hangyeol.eatgo.restaurant;

import me.hangyeol.eatgo.restaurant.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class RestaurantTests {

    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();
    }

    @Test
    @DisplayName("레스토랑 가게 객체 생성")
    public void create() {
        assertThat(restaurant.getId()).isEqualTo(1004L);
        assertThat(restaurant.getName()).isEqualTo("Bob zip");
        assertThat(restaurant.getAddress()).isEqualTo("Seoul");
    }

    @Test
    @DisplayName("가게 정보 확인")
    public void getInformation() {
        assertThat(restaurant.getInformation()).isEqualTo("Bob zip in Seoul");
    }

}
