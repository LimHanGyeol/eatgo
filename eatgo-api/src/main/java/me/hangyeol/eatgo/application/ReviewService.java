package me.hangyeol.eatgo.application;

import me.hangyeol.eatgo.domain.Review;
import me.hangyeol.eatgo.domain.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }
}
