package me.hangyeol.eatgo.interfaces;

import me.hangyeol.eatgo.domain.Restaurant;
import me.hangyeol.eatgo.domain.RestaurantRepository;
import me.hangyeol.eatgo.domain.RestaurantRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping("/restaurants")
    public List<Restaurant> list() {
        List<Restaurant> restaurantList = restaurantRepository.findAll();

        return restaurantList;
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id) {
        Restaurant restaurant = restaurantRepository.findById(id);

        return restaurant;
    }


}
