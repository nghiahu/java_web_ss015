package com.example.session15.repsitory.review;

import com.example.session15.model.Review;

import java.util.List;

public interface ReviewRepository {
    boolean saveReview(Review review);
    List<Review> getReviewsByProduct(int IdProduct);
}
