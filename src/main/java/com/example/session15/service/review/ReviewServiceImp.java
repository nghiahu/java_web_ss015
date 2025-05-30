package com.example.session15.service.review;

import com.example.session15.model.Review;
import com.example.session15.repsitory.review.ReviewRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImp implements ReviewService {

    @Autowired
    private ReviewRepositoryImp reviewRepositoryImp;


    @Override
    public boolean saveReview(Review review) {
        return reviewRepositoryImp.saveReview(review);
    }

    @Override
    public List<Review> getReviewsByProduct(int product_id) {
        return reviewRepositoryImp.getReviewsByProduct(product_id);
    }
}
