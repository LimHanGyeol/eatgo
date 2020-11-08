package me.hangyeol.eatgo.restaurant.controller;

import me.hangyeol.eatgo.restaurant.service.RestaurantService;
import me.hangyeol.eatgo.restaurant.Restaurant;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class RestaurantController {

    private RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/")
    public String main() {
        return "Restaurant, Service";
    }

    @GetMapping("/restaurants")
    public List<Restaurant> list() {
        return restaurantService.getRestaurants();
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id) {
        return restaurantService.getRestaurant(id);
    }

}
