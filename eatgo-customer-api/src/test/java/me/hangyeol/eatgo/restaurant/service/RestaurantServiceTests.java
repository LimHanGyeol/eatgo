package me.hangyeol.eatgo.restaurant.service;

import me.hangyeol.eatgo.global.exception.RestaurantNotFoundException;
import me.hangyeol.eatgo.menu.MenuItem;
import me.hangyeol.eatgo.menu.MenuItemRepository;
import me.hangyeol.eatgo.restaurant.Restaurant;
import me.hangyeol.eatgo.restaurant.RestaurantRepository;
import me.hangyeol.eatgo.review.Review;
import me.hangyeol.eatgo.review.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class RestaurantServiceTests {

    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;
    @Mock
    private MenuItemRepository menuItemRepository;
    @Mock
    private ReviewRepository reviewRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        mockRestaurantRepository();
        mockMenuItemRepository();
        mockReviewRepository();

        restaurantService = new RestaurantService(
                restaurantRepository, menuItemRepository, reviewRepository);
    }

    private void mockRestaurantRepository() {
        List<Restaurant> restaurantList = new ArrayList<>();
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();

        restaurantList.add(restaurant);

        given(restaurantRepository.findAll())
                .willReturn(restaurantList);

        given(restaurantRepository.findById(1004L))
                .willReturn(Optional.of(restaurant));
    }

    private void mockMenuItemRepository() {
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(MenuItem.builder()
                .name("Kimchi")
                .build());

        given(menuItemRepository.findAllByRestaurantId(1004L)).willReturn(menuItems);
    }

    private void mockReviewRepository() {
        List<Review> reviews = new ArrayList<>();
        reviews.add(Review.builder()
                .name("BeRyong")
                .score(1)
                .description("Bad")
                .build());

        given(reviewRepository.findAllByRestaurantId(1004L)).willReturn(reviews);
    }

    @Test
    @DisplayName("전체 레스토랑 가게 정보 가져오기")
    public void getRestaurants() {
        List<Restaurant> restaurants = restaurantService.getRestaurants();

        Restaurant restaurant = restaurants.get(0);
        assertThat(restaurant.getId()).isEqualTo(1004L);
    }

    @Test
    @DisplayName("특정 레스토랑 가게 정보 가져오기")
    public void getRestaurantWithExisted() {
        Restaurant restaurant = restaurantService.getRestaurant(1004L);

        verify(menuItemRepository).findAllByRestaurantId(eq(1004L));
        verify(reviewRepository).findAllByRestaurantId(eq(1004L));

        assertThat(restaurant.getId()).isEqualTo(1004L);

        MenuItem menuItem = restaurant.getMenuItems().get(0);
        assertThat(menuItem.getName()).isEqualTo("Kimchi");

        Review review = restaurant.getReviews().get(0);
        assertThat(review.getDescription()).isEqualTo("Bad");
    }

    @Test
    @DisplayName("잘못된 RestaurantId 가 들어올 경우 NotFountException 호출")
    public void getRestaurantWithNotExisted() {
        assertThatExceptionOfType(RestaurantNotFoundException.class)
                .isThrownBy(() -> restaurantService.getRestaurant(404L))
                .withMessage("Could not find restaurant 404");
    }

}
