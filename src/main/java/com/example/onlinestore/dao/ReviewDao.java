package com.example.onlinestore.dao;

import com.example.onlinestore.entity.Review;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ReviewDao {

    List<Review> findAllReviews();

    Review findReviewById(long id);

    void saveReview(Review review);

    void updateReview(long id, Review updatedReview);

    void deleteReview(long id);
}
