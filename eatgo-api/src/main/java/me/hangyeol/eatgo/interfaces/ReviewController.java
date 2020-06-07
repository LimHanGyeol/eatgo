package me.hangyeol.eatgo.interfaces;

import me.hangyeol.eatgo.application.ReviewService;
import me.hangyeol.eatgo.domain.Review;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/restaurants/{restaurantId}/reviews")
    public ResponseEntity<?> create(
            @PathVariable("restaurantId") Long restaurantId,
            @RequestBody @Valid Review resource)
        throws URISyntaxException {

        Review review = reviewService.addReview(resource);
        URI url = new URI("/restaurants/" + restaurantId + "/reviews/" + review.getId());
        return ResponseEntity.created(url)
                .body("{}");
    }
}
