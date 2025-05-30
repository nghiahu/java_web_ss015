package com.example.session15.repsitory.review;

import com.example.session15.config.ConnectionDB;
import com.example.session15.model.Review;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReviewRepositoryImp implements ReviewRepository {
    @Override
    public boolean saveReview(Review review) {
        Connection conn = null;
        CallableStatement callSt = null;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call saveReview(?,?,?,?)}");
            callSt.setInt(1, review.getProductId());
            callSt.setInt(2, review.getUserId());
            callSt.setInt(3, review.getRating());
            callSt.setString(4, review.getComment());
            callSt.execute();
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public List<Review> getReviewsByProduct(int IdProduct) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Review> reviews = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call getReviewByProduct(?)}");
            callSt.setInt(1, IdProduct);
            ResultSet resultSet = callSt.executeQuery();
            while (resultSet.next()) {
                Review review = new Review();
                review.setId(resultSet.getInt("id"));
                review.setProductId(resultSet.getInt("idProduct"));
                review.setUserId(resultSet.getInt("idUser"));
                review.setRating(resultSet.getInt("rating"));
                review.setComment(resultSet.getString("comment"));
                reviews.add(review);
            }
            return reviews;
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return reviews;
    }
}
