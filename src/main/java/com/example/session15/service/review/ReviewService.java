package com.example.session15.service.review;

import com.example.session15.model.Review;

import java.util.List;

public interface ReviewService {
    boolean saveReview(Review review);
    List<Review> getReviewsByProduct(int product_id);
}
