package me.hangyeol.eatgo.interfaces;

import me.hangyeol.eatgo.application.RestaurantService;
import me.hangyeol.eatgo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public List<Restaurant> list() {
        List<Restaurant> restaurantList = restaurantService.getRestaurants();

        return restaurantList;
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id) {
        Restaurant restaurant = restaurantService.getRestaurant(id);

        return restaurant;
    }


}
