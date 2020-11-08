package me.hangyeol.eatgo.restaurant.service;

import me.hangyeol.eatgo.global.exception.RestaurantNotFoundException;
import me.hangyeol.eatgo.restaurant.Restaurant;
import me.hangyeol.eatgo.restaurant.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class RestaurantServiceTests {

    @SpyBean
    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockRestaurantRepository();
        restaurantService = new RestaurantService(restaurantRepository);
    }

    private Restaurant newInstanceOfRestaurant() {
        return Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();
    }

    private void mockRestaurantRepository() {
        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(newInstanceOfRestaurant());

        given(restaurantRepository.findAll()).willReturn(restaurantList);

        given(restaurantRepository.findById(1004L))
                .willReturn(Optional.ofNullable(restaurantList.get(0)));
    }

    @Test
    @DisplayName("레스토링 가게 전체 정보 가져오기")
    public void getRestaurants() {
        List<Restaurant> restaurants = restaurantService.getRestaurants();

        Restaurant restaurant = restaurants.get(0);
        assertThat(restaurant.getId()).isEqualTo(1004L);
    }

    @Test
    @DisplayName("특정 레스토랑 가게 정보 가져오기")
    public void getRestaurantWithExisted() {
        Restaurant restaurant = restaurantService.getRestaurant(1004L);
        assertThat(restaurant.getId()).isEqualTo(1004L);
    }

    @Test
    @DisplayName("레스토랑 가게가 없을 경우 Exception 발생")
    public void getRestaurantWithNotExisted() {
        assertThatExceptionOfType(RestaurantNotFoundException.class)
                .isThrownBy(() -> restaurantService.getRestaurant(404L));
    }

    @Test
    @DisplayName("가게 추가")
    public void addRestaurant() {
        given(restaurantRepository.save(any()))
                .will(invocation -> {
                    Restaurant restaurant = invocation.getArgument(0);
                    restaurant.setId(1234L);
                    return restaurant;
                });

        Restaurant restaurant = Restaurant.builder()
                .name("BeRyong")
                .address("Busan")
                .build();

        Restaurant created = restaurantService.addRestaurant(restaurant);

        assertThat(created.getId()).isEqualTo(1234L);
    }

    @Test
    @DisplayName("가게 정보 업데이트")
    public void updateRestaurant() {
        Optional<Restaurant> maybeRestaurant = Optional.ofNullable(newInstanceOfRestaurant());

        given(restaurantRepository.findById(1004L))
                .willReturn(maybeRestaurant);

        restaurantService.updateRestaurant(1004L, "Sool zip", "Busan");

        Restaurant restaurant = maybeRestaurant.orElseGet(this::newInstanceOfRestaurant);

        assertThat(restaurant.getName()).isEqualTo("Sool zip");
        assertThat(restaurant.getAddress()).isEqualTo("Busan");
    }
}
