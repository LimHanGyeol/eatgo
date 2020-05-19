package me.hangyeol.eatgo.application;

import me.hangyeol.eatgo.domain.MenuItem;
import me.hangyeol.eatgo.domain.MenuItemRepository;
import me.hangyeol.eatgo.domain.Restaurant;
import me.hangyeol.eatgo.domain.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    RestaurantRepository restaurantRepository;
    MenuItemRepository menuItemRepository;

    public RestaurantService(RestaurantRepository restaurantRepository,
                             MenuItemRepository menuItemRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
    }

    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurantList = restaurantRepository.findAll();

        return restaurantList;
    }

    public Restaurant getRestaurant(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id);

        List<MenuItem> menuItemList = menuItemRepository.findAllByRestaurantId(id);
        restaurant.setMenuItems(menuItemList);

        return restaurant;
    }

}
