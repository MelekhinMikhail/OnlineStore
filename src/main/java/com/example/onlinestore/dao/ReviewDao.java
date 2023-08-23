package com.example.onlinestore.dao;

import com.example.onlinestore.entity.Review;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ReviewDao {
    @Transactional(readOnly = true)
    List<Review> findAllReviews();

    @Transactional(readOnly = true)
    Review findReviewById(long id);

    @Transactional
    void saveReview(Review review);

    @Transactional
    void updateReview(long id, Review updatedReview);

    @Transactional
    void deleteReview(long id);
}
