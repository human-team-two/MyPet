package com.example.team_pro_ex.Service.review;

import com.example.team_pro_ex.Entity.review.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewService {

    List<Review> getReviewList(Review review);

    void insertReview(Review review);

    Review getReview(Review review);

    Review findById(Long seq);

    void updateReview(Review review);

    void removeReview(Review review);

    List<Review> searchReviewAll(String keyword);

    Page<Review> findAll(Pageable pageable, String keyword);

    Page<Review> findByTitle(Pageable pageable, String keyword);
    Page<Review> findByContent(Pageable pageable, String keyword);
    Page<Review> findByWriter(Pageable pageable, String keyword);



}
