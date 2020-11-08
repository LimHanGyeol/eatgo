package me.hangyeol.eatgo.review.service;

import me.hangyeol.eatgo.review.Review;
import me.hangyeol.eatgo.review.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review addReview(Long restaurantId, Review review) {
        review.setRestaurantId(restaurantId);
        return reviewRepository.save(review);
    }
}
