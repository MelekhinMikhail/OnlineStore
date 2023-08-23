package com.example.onlinestore.dao.impl;

import com.example.onlinestore.dao.ReviewDao;
import com.example.onlinestore.entity.Review;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReviewDaoImpl implements ReviewDao {

    private final SessionFactory sessionFactory;

    @Transactional(readOnly = true)
    @Override
    public List<Review> findAllReviews() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select r from Review r", Review.class).getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public Review findReviewById(long id) {
        Session session = sessionFactory.getCurrentSession();

        return session.get(Review.class, id);
    }

    @Transactional
    @Override
    public void saveReview(Review review) {
        Session session = sessionFactory.getCurrentSession();

        session.save(review);
    }

    @Transactional
    @Override
    public void updateReview(long id, Review updatedReview) {
        Session session = sessionFactory.getCurrentSession();
        Review reviewToBeUpdated = session.get(Review.class, id);

        reviewToBeUpdated.setContent(updatedReview.getContent());
        reviewToBeUpdated.setRating(updatedReview.getRating());
        reviewToBeUpdated.setDateOfCreation(updatedReview.getDateOfCreation());
        reviewToBeUpdated.setUser(updatedReview.getUser());
        reviewToBeUpdated.setProduct(updatedReview.getProduct());
    }

    @Transactional
    @Override
    public void deleteReview(long id) {
        Session session = sessionFactory.getCurrentSession();

        session.remove(session.get(Review.class, id));
    }
}
