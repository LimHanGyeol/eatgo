package me.hangyeol.eatgo.application;

import me.hangyeol.eatgo.domain.Review;
import me.hangyeol.eatgo.domain.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }
    public List<Review> getReviews() {
        return reviewRepository.findAll();
    }

}
