package me.hangyeol.eatgo.review.controller;

import me.hangyeol.eatgo.review.service.ReviewService;
import me.hangyeol.eatgo.review.Review;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public List<Review> list() {
        return reviewService.getReviews();
    }
}
