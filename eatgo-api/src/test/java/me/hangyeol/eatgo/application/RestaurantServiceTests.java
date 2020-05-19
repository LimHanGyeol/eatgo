package me.hangyeol.eatgo.application;

import me.hangyeol.eatgo.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class RestaurantServiceTests {

    private RestaurantService restaurantService;
    private RestaurantRepository restaurantRepository;
    private MenuItemRepository menuItemRepository;

    @BeforeEach
    void setUp() {
        restaurantRepository = new RestaurantRepositoryImpl();
        menuItemRepository = new MenuItemRepositoryImpl();
        restaurantService = new RestaurantService(restaurantRepository, menuItemRepository);
    }

    @Test
    public void getRestaurants() {
        List<Restaurant> restaurantList = restaurantService.getRestaurants();

        Restaurant restaurant = restaurantList.get(0);
        assertThat(restaurant.getId(), is(1004L));
    }

    @Test
    public void getRestaurant() {
        Restaurant restaurant = restaurantService.getRestaurant(1004L);

        MenuItem menuItem = restaurant.getMenuItems().get(0);

        assertThat(menuItem.getName(), is("Kimchi"));
    }

}