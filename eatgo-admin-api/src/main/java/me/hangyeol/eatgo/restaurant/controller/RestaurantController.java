package me.hangyeol.eatgo.restaurant.controller;

import me.hangyeol.eatgo.restaurant.service.RestaurantService;
import me.hangyeol.eatgo.restaurant.Restaurant;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin
@RestController
public class RestaurantController {

    private RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/")
    public String hello() {
        return "Restaurant. Service";
    }

    @GetMapping("/restaurants")
    public List<Restaurant> list() {
        return restaurantService.getRestaurants();
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id) {
        return restaurantService.getRestaurant(id);
    }

    @PostMapping(value = "/restaurants")
    public ResponseEntity<?> create(@Valid @RequestBody Restaurant resource)
            throws URISyntaxException {
        Restaurant restaurant = restaurantService.addRestaurant(
                Restaurant.builder()
                        .name(resource.getName())
                        .address(resource.getAddress())
                        .build());

        URI location = new URI("/restaurants/" + restaurant.getId());
        return ResponseEntity.created(location).body("{}");
    }

    @PatchMapping("/restaurants/{id}")
    public String update(@PathVariable("id") Long id,
                         @Valid @RequestBody Restaurant resource) {
        String name = resource.getName();
        String address = resource.getAddress();

        restaurantService.updateRestaurant(id, name, address);
        return "{}";
    }
}
